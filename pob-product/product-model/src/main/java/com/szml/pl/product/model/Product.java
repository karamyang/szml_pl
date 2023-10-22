package com.szml.pl.product.model;


public class Product {

  private long id;
  private String productName;
  private String type;
  private long categoryId;
  private String rightId;
  private String exchangeType;
  private double cash;
  private long integral;
  private String mixed;
  private long stock;
  private long restriction;
  private String imgUrl;
  private String description;
  private String detail;
  private String blackListId;
  private String whiteListId;
  private java.sql.Timestamp onlineTime;
  private java.sql.Timestamp lineTime;
  private long status;
  private long createUserId;
  private long manageUserId;
  private String notShipments;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
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


  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
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


  public double getCash() {
    return cash;
  }

  public void setCash(double cash) {
    this.cash = cash;
  }


  public long getIntegral() {
    return integral;
  }

  public void setIntegral(long integral) {
    this.integral = integral;
  }


  public String getMixed() {
    return mixed;
  }

  public void setMixed(String mixed) {
    this.mixed = mixed;
  }


  public long getStock() {
    return stock;
  }

  public void setStock(long stock) {
    this.stock = stock;
  }


  public long getRestriction() {
    return restriction;
  }

  public void setRestriction(long restriction) {
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


  public java.sql.Timestamp getOnlineTime() {
    return onlineTime;
  }

  public void setOnlineTime(java.sql.Timestamp onlineTime) {
    this.onlineTime = onlineTime;
  }


  public java.sql.Timestamp getLineTime() {
    return lineTime;
  }

  public void setLineTime(java.sql.Timestamp lineTime) {
    this.lineTime = lineTime;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(long createUserId) {
    this.createUserId = createUserId;
  }


  public long getManageUserId() {
    return manageUserId;
  }

  public void setManageUserId(long manageUserId) {
    this.manageUserId = manageUserId;
  }


  public String getNotShipments() {
    return notShipments;
  }

  public void setNotShipments(String notShipments) {
    this.notShipments = notShipments;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
