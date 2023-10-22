package com.szml.pl.product.model;


public class ProductRecord {

  private long id;
  private long productId;
  private long adminId;
  private long record;
  private java.sql.Timestamp createTime;
  private String recordDescription;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public long getAdminId() {
    return adminId;
  }

  public void setAdminId(long adminId) {
    this.adminId = adminId;
  }


  public long getRecord() {
    return record;
  }

  public void setRecord(long record) {
    this.record = record;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getRecordDescription() {
    return recordDescription;
  }

  public void setRecordDescription(String recordDescription) {
    this.recordDescription = recordDescription;
  }

}
