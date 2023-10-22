package com.szml.pl.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

  private Long id;
  private String categoryName;
  private Long parentId;
  private Long categoryLevel;
  private Integer status;
  private String imgUrl;
  private Long leaf;
  private Long priority;
  private Timestamp createTime;
  private Timestamp updateTime;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }


  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }


  public Long getCategoryLevel() {
    return categoryLevel;
  }

  public void setCategoryLevel(Long categoryLevel) {
    this.categoryLevel = categoryLevel;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }


  public Long getLeaf() {
    return leaf;
  }

  public void setLeaf(Long leaf) {
    this.leaf = leaf;
  }


  public Long getPriority() {
    return priority;
  }

  public void setPriority(Long priority) {
    this.priority = priority;
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
