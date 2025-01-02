package pe.gob.osinergmin.prie.admin.backend.domain;

import java.math.BigDecimal;
import java.util.Date;

public class IndEconomico {
    private String annioMes;

    private String codIndicador;

    private BigDecimal valIndicador;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    public String getAnnioMes() {
        return annioMes;
    }

    public void setAnnioMes(String annioMes) {
        this.annioMes = annioMes;
    }

    public String getCodIndicador() {
        return codIndicador;
    }

    public void setCodIndicador(String codIndicador) {
        this.codIndicador = codIndicador;
    }

    public BigDecimal getValIndicador() {
        return valIndicador;
    }

    public void setValIndicador(BigDecimal valIndicador) {
        this.valIndicador = valIndicador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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