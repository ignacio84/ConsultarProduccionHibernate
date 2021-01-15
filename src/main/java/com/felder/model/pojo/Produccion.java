package com.felder.model.pojo;

public class Produccion {

    private String fecha;
    private String hora;
    private Integer folio;
    private String codigoProducto;
    private String carga;
    private String descripcion;
    private Integer totalCajas;
    private String peso;
    private String responsable;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTotalCajas() {
        return totalCajas;
    }

    public void setTotalCajas(Integer totalCajas) {
        this.totalCajas = totalCajas;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    @Override
    public String toString() {
        return "Produccion{" + "fecha=" + fecha + ", hora=" + hora + ", folio=" + folio + ", codigoProducto=" + codigoProducto + ", carga=" + carga + ", descripcion=" + descripcion + ", totalCajas=" + totalCajas + ", peso=" + peso + ", responsable=" + responsable + '}';
    }

    
}
