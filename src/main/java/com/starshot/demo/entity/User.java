package com.starshot.demo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Table
@Entity(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @NotNull
  @Column
  private String fullName;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date timeIn;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date timeOut;

  @Column
  private Boolean active;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Date getTimeIn() {
    return timeIn;
  }

  public void setTimeIn(Date timeIn) {
    this.timeIn = timeIn;
  }

  public Date getTimeOut() {
    return timeOut;
  }

  public void setTimeOut(Date timeOut) {
    this.timeOut = timeOut;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
