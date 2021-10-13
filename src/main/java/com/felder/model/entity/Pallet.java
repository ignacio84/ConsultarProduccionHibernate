package com.felder.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name = "Pallet.findByBatch", query = " SELECT p FROM Pallet p WHERE p.lote=:lote "),
    @NamedQuery(name = "Pallet.findByItemAndBatch", query = " SELECT p FROM Pallet p where p.codigoProducto = :codigoProducto  AND  p.lote = :lote "),})
@Table(name = "ingr_enc")
public class Pallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "in_en_folio")
    private Integer folio;

    @Column(name = "in_en_numcarga")
    private String lote;

    @Column(name = "in_en_pesotar")
    private String taraTarima;

    @Column(name = "in_en_totpesoacum")
    private String peso;

    @Column(name = "in_en_numtardia")
    private String numeroTarima;

    @Column(name = "in_en_nomemp")
    private String responsable;

    @Column(name = "in_en_codprod")
    private String codigoProducto;

    @Column(name = "in_en_numcajasproc")
    private Integer numeroCajas;

    @Column(name = "int_en_temp")
    private String temperatura;

    @Column(name = "in_en_fecproc", columnDefinition="DATETIME")
    private LocalDateTime fecha;

    @Column(name = "in_en_taracaja")
    private String taraCaja;

    @Transient
    private LocalDateTime from;

    @Transient
    private LocalDateTime to;

    @OneToMany(mappedBy = "folio", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PalletLine> lines = new ArrayList<>();

    public Integer getFolio() {
        return folio;
    }

    public Pallet(Integer folio, String lote, String taraTarima, String peso, String numeroTarima, String responsable, String codigoProducto, Integer numeroCajas, String temperatura, LocalDateTime fecha, String taraCaja, LocalDateTime from, LocalDateTime to) {
        this.folio = folio;
        this.lote = lote;
        this.taraTarima = taraTarima;
        this.peso = peso;
        this.numeroTarima = numeroTarima;
        this.responsable = responsable;
        this.codigoProducto = codigoProducto;
        this.numeroCajas = numeroCajas;
        this.temperatura = temperatura;
        this.fecha = fecha;
        this.taraCaja = taraCaja;
        this.from = from;
        this.to = to;
    }

    public Pallet(Integer folio) {
        this.folio = folio;
    }

    public Pallet(String codigoProducto, String batch, LocalDateTime from, LocalDateTime to) {
        this.codigoProducto = codigoProducto;
        this.lote = batch;
        this.from = from;
        this.to = to;
    }

    public Pallet() {
        super();
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getTaraTarima() {
        return taraTarima;
    }

    public void setTaraTarima(String taraTarima) {
        this.taraTarima = taraTarima;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getNumeroTarima() {
        return numeroTarima;
    }

    public void setNumeroTarima(String numeroTarima) {
        this.numeroTarima = numeroTarima;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getNumeroCajas() {
        return numeroCajas;
    }

    public void setNumeroCajas(Integer numeroCajas) {
        this.numeroCajas = numeroCajas;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public String getTaraCaja() {
        return taraCaja;
    }

    public void setTaraCaja(String taraCaja) {
        this.taraCaja = taraCaja;
    }

    public List<PalletLine> getLines() {
        return lines;
    }

    public void setLines(List<PalletLine> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Pallet{" + "folio=" + folio + ", lote=" + lote + ", taraTarima=" + taraTarima + ", peso=" + peso + ", numeroTarima=" + numeroTarima + ", responsable=" + responsable + ", codigoProducto=" + codigoProducto + ", numeroCajas=" + numeroCajas + ", temperatura=" + temperatura + ", fecha=" + fecha + ", taraCaja=" + taraCaja + ", from=" + from + ", to=" + to + ", lines=" + lines + '}';
    }
}
