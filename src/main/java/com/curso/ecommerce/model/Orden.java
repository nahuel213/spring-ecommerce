package com.curso.ecommerce.model;

import java.util.Date;

public class Orden {
    private Integer id;
    private String numero;
    private Date fechaCreacion;
    private String fechaRecibida;
    private double total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(String fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Orden() {
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaRecibida='" + fechaRecibida + '\'' +
                ", total=" + total +
                '}';
    }
}
