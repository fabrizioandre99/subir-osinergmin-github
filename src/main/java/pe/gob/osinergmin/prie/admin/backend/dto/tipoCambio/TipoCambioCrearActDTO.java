package pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio;

import java.math.BigDecimal;
import java.util.Date;

public class TipoCambioCrearActDTO {

    private String moneda;
    private String fuente;
    private String fecha;
    private BigDecimal valCompra;
    private BigDecimal valVenta;
    private Date adFecha;
    private String adCodUsuario;

    public String getAdCodUsuario() {
        return adCodUsuario;
    }

    public void setAdCodUsuario(String adCodUsuario) {
        this.adCodUsuario = adCodUsuario;
    }

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public Date getAdFecha() {
        return adFecha;
    }

    public void setAdFecha(Date adFecha) {
        this.adFecha = adFecha;
    }
}
