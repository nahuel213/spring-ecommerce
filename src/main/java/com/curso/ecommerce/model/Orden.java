package com.curso.ecommerce.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    private Date fechaCreacion;
    private String fechaRecibida;
    private double total;

    @ManyToOne
    private Usuario usuario;

    //Una Orden puede tener varios detalles (Si cambias esto tenes que cambiar el del otro lado, la clase par de esta)
    //Corrección ver video: https://www.youtube.com/watch?v=hHvLlz-KvNM&list=PL3vxkSlW2FvU9z7Gz_Nn3E69HjEvv55_G&index=42

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrden> detalle) {
        this.detalle = detalle;
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
