package com.example.paulomello.banca_valdir.models;

import java.io.Serializable;

public class Venda implements Serializable {

    //VENDAS PARA CLIENTES

    private long id;
    private long id_cliente;
    private String status;
    private String valor;
    private String data_venda;

    public Venda(long id,long id_cliente,String status,String valor,String data_venda){
        this.id = id;
        this.id_cliente = id_cliente;
        this.status = status;
        this.valor = valor;
        this.data_venda = data_venda;
    }
    public Venda(long id_cliente, String status, String valor, String data_venda){
        this(0,id_cliente,status,valor,data_venda);
    }

    public Venda(){
        this(0,0,"","","");
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
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