package com.example.paulomello.banca_valdir.Models;

import java.io.Serializable;

public class Cliente implements Serializable {

    private long id;
    private String name;
    private String address;
    private String bthday;
    private String account_number;
    private String account_agency;

    public Cliente()
    {
        this(0,"","","","","");
    }

    public Cliente(String nome, String address, String bthday, String account_number, String account_agency){
        this(0,nome,address,bthday,account_number,account_agency);
    }

    public Cliente(long id, String nome, String address, String bthday, String account_number, String account_agency)
    {
        this.id = id;
        this.name = nome;
        this.address = address;
        this.bthday = bthday;
        this.account_number = account_number;
        this.account_agency = account_agency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBthday() {
        return bthday;
    }

    public void setBthday(String bthday) {
        this.bthday = bthday;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_agency() {
        return account_agency;
    }

    public void setAccount_agency(String account_agency) {
        this.account_agency = account_agency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


