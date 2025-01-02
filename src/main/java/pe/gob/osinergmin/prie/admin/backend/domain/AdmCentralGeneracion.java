package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmCentralGeneracion {
    private String codCentralGeneracion;

    private String nomCentralGeneracion;

    private String codTipoCentral;

    private String codEmpresa;

    private String codUbigeo;

    private String estado;

    private String tipo;

    private String adCodUsuario;

    private Date adFecha;

    private String codCoes;

    private String codCentralGeneracionAnt;

    public String getCodCentralGeneracion() {
        return codCentralGeneracion;
    }

    public void setCodCentralGeneracion(String codCentralGeneracion) {
        this.codCentralGeneracion = codCentralGeneracion;
    }

    public String getNomCentralGeneracion() {
        return nomCentralGeneracion;
    }

    public void setNomCentralGeneracion(String nomCentralGeneracion) {
        this.nomCentralGeneracion = nomCentralGeneracion;
    }

    public String getCodTipoCentral() {
        return codTipoCentral;
    }

    public void setCodTipoCentral(String codTipoCentral) {
        this.codTipoCentral = codTipoCentral;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getCodCoes() {
        return codCoes;
    }

    public void setCodCoes(String codCoes) {
        this.codCoes = codCoes;
    }

    public String getCodCentralGeneracionAnt() {
        return codCentralGeneracionAnt;
    }

    public void setCodCentralGeneracionAnt(String codCentralGeneracionAnt) {
        this.codCentralGeneracionAnt = codCentralGeneracionAnt;
    }
}