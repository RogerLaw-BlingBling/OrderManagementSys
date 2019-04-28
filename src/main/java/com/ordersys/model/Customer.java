package com.ordersys.model;

import javax.persistence.*;

@Entity
public class Customer {
    private Integer id;
    //客户名
    private String customerName;
    private String address;
    //项目负责人
    private String contactPerson;
    private String mobilePhone;
    private String email;
    //开户银行
    private String bankName;
    //对公账户
    private String bankAccountNum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(columnDefinition = "VARCHAR(32)")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(columnDefinition = "VARCHAR(32)")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(columnDefinition = "VARCHAR(32)")
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Column(columnDefinition = "VARCHAR(18)")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Column(columnDefinition = "VARCHAR(64)")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(columnDefinition = "VARCHAR(32)")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(columnDefinition = "VARCHAR(32)")
    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }
}
