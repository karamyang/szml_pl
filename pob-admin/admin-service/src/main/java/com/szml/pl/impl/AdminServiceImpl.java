package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.AdminDao;
import com.szml.pl.dto.LoginReq;
import com.szml.pl.entity.Admin;
import com.szml.pl.service.AdminService;
import com.szml.pl.utils.JwtTokenUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Resource
    private JavaMailSender mailSender;
    @Resource
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String RESET_PASSWORD_CODE_PREFIX = "reset_password:code:";

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String ,Object> map=new HashMap<>();
        if(StringUtils.isBlank(username)){
            map.put("usernameError","账号不能为空!");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("passwordError","密码不能为空!");
            return map;
        }
        Admin admin=adminDao.selectAdminByUsername(username);
        if(admin==null){
            map.put("adminError","账号不存在!");
            return map;
        }
        map.put("success","登录成功");
        return map;
    }

    @Override
    public Map<String, Object> register(Admin admin) {
        Map<String,Object> map=new HashMap<>();

        if(admin==null){
            throw new IllegalArgumentException("参数不能为空!");
        }
        if(StringUtils.isBlank(admin.getUsername())){
            map.put("usernameError","账号不能为空!");
            return map;
        }
        if(StringUtils.isBlank(admin.getPassword())){
            map.put("passwordError","密码不能为空!");
            return map;
        }
        if(StringUtils.isBlank(admin.getEmail())){
            map.put("emailError","邮箱不能为空!");
            return map;
        }
        //验证账号是否已存在
        Admin admin1=adminDao.selectAdminByUsername(admin.getUsername());
        if(admin1!=null){
            map.put("usernameMsg","该账号已存在!");
            return map;
        }
        //验证邮箱是否已存在
        admin1=adminDao.selectAdminByEmail(admin.getEmail());
        if(admin1!=null){
            map.put("emailMsg","该邮箱已被注册!");
            return map;
        }

        //验证手机是否已存在
        admin1=adminDao.selectAdminByTelephone(admin.getTelephone());
        if(admin1!=null){
            map.put("emailMsg","该手机已被注册!");
            return map;
        }
        admin.setCreateTime(new Timestamp(System.currentTimeMillis()));

        //注册用户
        adminDao.insertAdmin(admin);

        return map;
    }

    @Override
    public void sendVerificationCodeToEmail(String emailAddress) {
        // 1. 生成验证码
        String verificationCode = UUID.randomUUID().toString().replaceAll("-","").substring(0, 4);

        // 2. 将验证码存储到 Redis
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(RESET_PASSWORD_CODE_PREFIX + emailAddress, verificationCode, 10, TimeUnit.MINUTES);

        // 3. 发送邮件
        String emailSubject = "重置密码";//标题
        String emailContent = "您的验证码为: " + verificationCode;//内容

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(emailAddress);
        message.setSubject(emailSubject);
        message.setText(emailContent);
        mailSender.send(message);
    }

    @Override
    public boolean checkVerificationCode(String emailAddress,String VerificationCode) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String storedCode = ops.get(RESET_PASSWORD_CODE_PREFIX + emailAddress);
        return VerificationCode.equals(storedCode);
    }

    @Override
    public Map<String, Object> forgetPassword(String password, String emailAddress) {
        Map<String, Object> map = new HashMap<>();

        // 空值处理
        if (StringUtils.isBlank(emailAddress)) {
            map.put("emailMsg", "邮箱不能为空!");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }

        // 验证邮箱
        Admin admin = adminDao.selectAdminByEmail(emailAddress);
        if (admin == null) {
            map.put("emailMsg", "该邮箱尚未注册!");
            return map;
        }

        // 重置密码
        adminDao.updatePassword(admin.getId(), password);
        //更新修改时间
        adminDao.updateTime(admin.getId(),new Timestamp(System.currentTimeMillis()));

        return map;
    }

    @Override
    public String login(LoginReq loginReq) {
        String token = null;
        try {
            //返回一个userDetails对象
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginReq.getUsername());
            if (!passwordEncoder.matches(loginReq.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public List<String> getPermissions(String username) {
        return adminDao.getPermissions(username);
    }

}
