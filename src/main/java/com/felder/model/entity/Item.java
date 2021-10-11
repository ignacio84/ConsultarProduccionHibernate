
package com.felder.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = " SELECT i FROM Item i "),
})
@Table(name = "cat_produc")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cons")
    private Integer id;

    @Column(name = "prod_codprod")
    private String claveProducto;

    @Column(name = "prod_descprod")
    private String descripcion;

    @Column(name = "prod_est")
    private String estatus;

    @Column(name = "prod_tipomov")
    private String tipoMovimiento;

    @Column(name = "prod_fecmov")
    private LocalDateTime fechaAlta;
    
    @Column(name = "prod_idusrmov")
    private Integer usuarioIdMovimiento;

    @Column(name = "prod_limitcajas")
    private Integer limiteCajas;

    @Column(name = "prod_tipo")
    private String tipoProducto;

    public Item() {
    }

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

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Integer getUsuarioIdMovimiento() {
        return usuarioIdMovimiento;
    }

    public void setUsuarioIdMovimiento(Integer usuarioIdMovimiento) {
        this.usuarioIdMovimiento = usuarioIdMovimiento;
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

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", claveProducto=" + claveProducto + ", descripcion=" + descripcion + ", estatus=" + estatus + ", tipoMovimiento=" + tipoMovimiento + ", fechaAlta=" + fechaAlta + ", usuarioIdMovimiento=" + usuarioIdMovimiento + ", limiteCajas=" + limiteCajas + ", tipoProducto=" + tipoProducto + '}';
    }
}
