
package com.felder.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ingr_det")
public class PalletLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "in_de_codorg")
    private String codigoBarras;
    
    @Column(name="in_de_folio")
    private Integer folio;
          
    @Column(name="in_de_descprod")
    private String descripcion;
    
    @Column(name="in_de_peso")
    private String peso;
    

    public PalletLine() {
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    
    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "PalletLine{" + "codigoBarras=" + codigoBarras + ", folio=" + folio + ", descripcion=" + descripcion + ", peso=" + peso + '}';
    }
}
