package pe.gob.osinergmin.prie.admin.backend.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Tabla de registro de detalle de Sistema Eléctrico
 */
public class AdmSistemaElectricoDet {
    private Integer idSeDet;

    private String codSisElec;

    private Date fechaAsociacion;

    private String codSisElecAnt;

    private String adCodUsuario;

    private Date adFecha;

    public Integer getIdSeDet() {
        return idSeDet;
    }

    public void setIdSeDet(Integer idSeDet) {
        this.idSeDet = idSeDet;
    }

    public String getCodSisElec() {
        return codSisElec;
    }

    public void setCodSisElec(String codSisElec) {
        this.codSisElec = codSisElec;
    }

    public Date getFechaAsociacion() {
        return fechaAsociacion;
    }

    public void setFechaAsociacion(Date fechaAsociacion) {
        this.fechaAsociacion = fechaAsociacion;
    }

    public String getCodSisElecAnt() {
        return codSisElecAnt;
    }

    public void setCodSisElecAnt(String codSisElecAnt) {
        this.codSisElecAnt = codSisElecAnt;
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