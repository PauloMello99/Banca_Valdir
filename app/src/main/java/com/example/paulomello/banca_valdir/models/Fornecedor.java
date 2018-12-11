package com.example.paulomello.banca_valdir.models;

import java.io.Serializable;

public class Fornecedor implements Serializable {

    private long id;
    private String name;
    private String bthday;
    private String cadastro;
    private String account_number;
    private String account_agency;

    public Fornecedor()
    {
        this(0,"","","","","");
    }

    public Fornecedor(String nome, String cadastro, String bthday, String account_number, String account_agency){
        this(0,nome,cadastro,bthday,account_number,account_agency);
    }

    public Fornecedor(long id, String nome, String cadastro, String bthday, String account_number, String account_agency)
    {
        this.id = id;
        this.name = nome;
        this.cadastro = cadastro;
        this.bthday = bthday;
        this.account_number = account_number;
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

    public String getBthday() {
        return bthday;
    }

    public void setBthday(String bthday) {
        this.bthday = bthday;
    }

    public String getCadastro() {
        return cadastro;
    }

    public void setCadastro(String cadastro) {
        this.cadastro = cadastro;
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
}
