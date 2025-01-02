package pe.gob.osinergmin.prie.admin.backend.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AdmUit {
    private String anioMes;

    private Integer valor;

    private String adCodUsuario;

    private Date adFecha;

    public String getAnioMes() {
        return anioMes;
    }

    public void setAnioMes(String anioMes) {
        this.anioMes = anioMes;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
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

    public AdmUit(String anioMes, Integer valor, String adCodUsuario, Date adFecha) {
        this.anioMes = anioMes;
        this.valor = valor;
        this.adCodUsuario = adCodUsuario;
        this.adFecha = adFecha;
    }
    public AdmUit() {}
}