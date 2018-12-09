package com.example.paulomello.banca_valdir.Models;

import java.io.Serializable;

public class Compra implements Serializable {

    //COMPRAS FEITAS COM FORNECEDOR

    private long id;
    private long id_fornecedor;
    private String status;
    private String valor;
    private String data_compra;

    public Compra(long id,long id_fornecedor, String status,String valor,String data_compra){
        this.id = id;
        this.id_fornecedor = id_fornecedor;
        this.status = status;
        this.valor = valor;
        this.data_compra = data_compra;
    }

    public Compra(long id_fornecedor, String status,String valor, String data_compra){
        this(0,id_fornecedor,status,valor,data_compra);
    }

    public String getData_compra() {
        return data_compra;
    }

    public void setData_compra(String data_compra) {
        this.data_compra = data_compra;
    }

    public Compra(){
        this(0,0,"","","");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(long id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
