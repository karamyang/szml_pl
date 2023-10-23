package com.szml.pl.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRecord {

  private Long id;
  private Long productId;
  private Long adminId;
  private Integer record;
  private Timestamp createTime;
  private String recordDescription;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }


  public Long getAdminId() {
    return adminId;
  }

  public void setAdminId(Long adminId) {
    this.adminId = adminId;
  }


  public Integer getRecord() {
    return record;
  }

  public void setRecord(Integer record) {
    this.record = record;
  }


  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getRecordDescription() {
    return recordDescription;
  }

  public void setRecordDescription(String recordDescription) {
    this.recordDescription = recordDescription;
  }

}
