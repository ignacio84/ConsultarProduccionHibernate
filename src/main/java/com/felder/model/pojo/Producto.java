package com.felder.model.pojo;

import java.util.Date;

public class Producto {

    private Integer id;

    private String claveProducto;

    private String descripcion;

    private String estatus;

    private String tipoMovimiento;

    private Date fechaAlta;
    
    private Integer usuarioIdMovimiento;

    private Integer limiteCajas;

    private String tipoProducto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClaveProducto() {
        return claveProducto;
    }

    public void setClaveProducto(String claveProducto) {
        this.claveProducto = claveProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Integer getLimiteCajas() {
        return limiteCajas;
    }

    public void setLimiteCajas(Integer limiteCajas) {
        this.limiteCajas = limiteCajas;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Integer getUsuarioIdMovimiento() {
        return usuarioIdMovimiento;
    }

    public void setUsuarioIdMovimiento(Integer usuarioIdMovimiento) {
        this.usuarioIdMovimiento = usuarioIdMovimiento;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", claveProducto=" + claveProducto + ", descripcion=" + descripcion + ", estatus=" + estatus + ", tipoMovimiento=" + tipoMovimiento + ", fechaAlta=" + fechaAlta + ", usuarioIdMovimiento=" + usuarioIdMovimiento + ", limiteCajas=" + limiteCajas + ", tipoProducto=" + tipoProducto + '}';
    }
}
