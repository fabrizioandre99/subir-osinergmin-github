package pe.gob.osinergmin.prie.admin.backend.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AdmSistemaElectrico {
    private String codSisElec;

    private String nomSisElec;

    private String codEmpresa;

    private String tipSisElec;

    private Date fecAlta;

    private Date fecBaja;

    private String codSectorTipico;

    private Date fecIntercon;

    private Integer areaDemanda;

    private String adCodUsuario;

    private Date adFecha;

    private String ipCreacion;

    private String usActualizacion;

    private String ipActualizacion;

    private Date feActualizacion;

    private String esSisElec;

    public String getCodSisElec() {
        return codSisElec;
    }

    public void setCodSisElec(String codSisElec) {
        this.codSisElec = codSisElec;
    }

    public String getNomSisElec() {
        return nomSisElec;
    }

    public void setNomSisElec(String nomSisElec) {
        this.nomSisElec = nomSisElec;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getTipSisElec() {
        return tipSisElec;
    }

    public void setTipSisElec(String tipSisElec) {
        this.tipSisElec = tipSisElec;
    }

    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    public Date getFecBaja() {
        return fecBaja;
    }

    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    public String getCodSectorTipico() {
        return codSectorTipico;
    }

    public void setCodSectorTipico(String codSectorTipico) {
        this.codSectorTipico = codSectorTipico;
    }

    public Date getFecIntercon() {
        return fecIntercon;
    }

    public void setFecIntercon(Date fecIntercon) {
        this.fecIntercon = fecIntercon;
    }

    public Integer getAreaDemanda() {
        return areaDemanda;
    }

    public void setAreaDemanda(Integer areaDemanda) {
        this.areaDemanda = areaDemanda;
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

    public String getIpCreacion() {
        return ipCreacion;
    }

    public void setIpCreacion(String ipCreacion) {
        this.ipCreacion = ipCreacion;
    }

    public String getUsActualizacion() {
        return usActualizacion;
    }

    public void setUsActualizacion(String usActualizacion) {
        this.usActualizacion = usActualizacion;
    }

    public String getIpActualizacion() {
        return ipActualizacion;
    }

    public void setIpActualizacion(String ipActualizacion) {
        this.ipActualizacion = ipActualizacion;
    }

    public Date getFeActualizacion() {
        return feActualizacion;
    }

    public void setFeActualizacion(Date feActualizacion) {
        this.feActualizacion = feActualizacion;
    }

    public String getEsSisElec() {
        return esSisElec;
    }

    public void setEsSisElec(String esSisElec) {
        this.esSisElec = esSisElec;
    }
}