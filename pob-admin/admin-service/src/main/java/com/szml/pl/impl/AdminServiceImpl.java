package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.AdminDao;
import com.szml.pl.entity.Admin;
import com.szml.pl.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {
}
