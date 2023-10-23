package com.szml.pl.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private Long id;
  private String productName;
  private String type;
  private Long categoryId;
  private String rightId;
  private String exchangeType;
  private Double cash;
  private Long integral;
  private String mixed;
  private Long stock;
  private Long restriction;
  private String imgUrl;
  private String description;
  private String detail;
  private String blackListId;
  private String whiteListId;
  private Timestamp onlineTime;
  private Timestamp lineTime;
  private Integer status;//商品类型 1:草稿 2:未审核 3:审核中 4:审核通过 5:上线 6:下线 审核中仅管理员可见
  private Long createUserId;
  private Long manageUserId;
  private String notShipments;
  private Timestamp createTime;
  private Timestamp updateTime;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }


  public String getRightId() {
    return rightId;
  }

  public void setRightId(String rightId) {
    this.rightId = rightId;
  }


  public String getExchangeType() {
    return exchangeType;
  }

  public void setExchangeType(String exchangeType) {
    this.exchangeType = exchangeType;
  }


  public Double getCash() {
    return cash;
  }

  public void setCash(Double cash) {
    this.cash = cash;
  }


  public Long getIntegral() {
    return integral;
  }

  public void setIntegral(Long integral) {
    this.integral = integral;
  }


  public String getMixed() {
    return mixed;
  }

  public void setMixed(String mixed) {
    this.mixed = mixed;
  }


  public Long getStock() {
    return stock;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }


  public Long getRestriction() {
    return restriction;
  }

  public void setRestriction(Long restriction) {
    this.restriction = restriction;
  }


  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public String getBlackListId() {
    return blackListId;
  }

  public void setBlackListId(String blackListId) {
    this.blackListId = blackListId;
  }


  public String getWhiteListId() {
    return whiteListId;
  }

  public void setWhiteListId(String whiteListId) {
    this.whiteListId = whiteListId;
  }


  public Timestamp getOnlineTime() {
    return onlineTime;
  }

  public void setOnlineTime(Timestamp onlineTime) {
    this.onlineTime = onlineTime;
  }


  public Timestamp getLineTime() {
    return lineTime;
  }

  public void setLineTime(Timestamp lineTime) {
    this.lineTime = lineTime;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = Math.toIntExact(status);
  }


  public Long getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(Long createUserId) {
    this.createUserId = createUserId;
  }


  public Long getManageUserId() {
    return manageUserId;
  }

  public void setManageUserId(Long manageUserId) {
    this.manageUserId = manageUserId;
  }


  public String getNotShipments() {
    return notShipments;
  }

  public void setNotShipments(String notShipments) {
    this.notShipments = notShipments;
  }


  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }


  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
