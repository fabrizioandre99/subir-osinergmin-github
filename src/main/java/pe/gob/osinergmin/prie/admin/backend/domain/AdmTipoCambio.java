package pe.gob.osinergmin.prie.admin.backend.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AdmTipoCambio {
    private String moneda;

    private String fuente;

    private Date fecha;

    private BigDecimal valCompra;

    private BigDecimal valVenta;

    private String adCodUsuario;

    private Date adFecha;

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValCompra() {
        return valCompra;
    }

    public void setValCompra(BigDecimal valCompra) {
        this.valCompra = valCompra;
    }

    public BigDecimal getValVenta() {
        return valVenta;
    }

    public void setValVenta(BigDecimal valVenta) {
        this.valVenta = valVenta;
    }

    public String getAdCodUsuario() {
        return adCodUsuario;
    }

    public void setAdCodUsuario(String adCodUsuario) {
        this.adCodUsuario = adCodUsuario;
    }

    public Date getAdFecha() {
        return adFecha;
    }

    public void setAdFecha(Date adFecha) {
        this.adFecha = adFecha;
    }
}