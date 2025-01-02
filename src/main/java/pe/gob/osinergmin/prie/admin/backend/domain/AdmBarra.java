package pe.gob.osinergmin.prie.admin.backend.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AdmBarra {
    private String codBarra;

    private String nomBarra;

    private Double tension;

    private String codBrg;

    private Double areaDemanda;

    private String codSubestacion;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    private String enRedDistribucion;

    private String codBarraCt;

    private String codCoes;

    private String codBarraAnt;

    private String ipCreacion;

    private String usActualizacion;

    private String ipActualizacion;

    private Date feActualizacion;

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getNomBarra() {
        return nomBarra;
    }

    public void setNomBarra(String nomBarra) {
        this.nomBarra = nomBarra;
    }

    public Double getTension() {
        return tension;
    }

    public void setTension(Double tension) {
        this.tension = tension;
    }

    public String getCodBrg() {
        return codBrg;
    }

    public void setCodBrg(String codBrg) {
        this.codBrg = codBrg;
    }

    public Double getAreaDemanda() {
        return areaDemanda;
    }

    public void setAreaDemanda(Double areaDemanda) {
        this.areaDemanda = areaDemanda;
    }

    public String getCodSubestacion() {
        return codSubestacion;
    }

    public void setCodSubestacion(String codSubestacion) {
        this.codSubestacion = codSubestacion;
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

    public String getEnRedDistribucion() {
        return enRedDistribucion;
    }

    public void setEnRedDistribucion(String enRedDistribucion) {
        this.enRedDistribucion = enRedDistribucion;
    }

    public String getCodBarraCt() {
        return codBarraCt;
    }

    public void setCodBarraCt(String codBarraCt) {
        this.codBarraCt = codBarraCt;
    }

    public String getCodCoes() {
        return codCoes;
    }

    public void setCodCoes(String codCoes) {
        this.codCoes = codCoes;
    }

    public String getCodBarraAnt() {
        return codBarraAnt;
    }

    public void setCodBarraAnt(String codBarraAnt) {
        this.codBarraAnt = codBarraAnt;
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
}