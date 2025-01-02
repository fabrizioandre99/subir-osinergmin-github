package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmGrupoGeneracion {
    private String codGrupoGeneracion;

    private String nomGrupoGeneracion;

    private Date fecAlta;

    private Date fecBaja;

    private String codTipoCombustible;

    private String codCentralGeneracion;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    private String codCoes;

    private String codGrupoGeneracionAnt;

    public String getCodGrupoGeneracion() {
        return codGrupoGeneracion;
    }

    public void setCodGrupoGeneracion(String codGrupoGeneracion) {
        this.codGrupoGeneracion = codGrupoGeneracion;
    }

    public String getNomGrupoGeneracion() {
        return nomGrupoGeneracion;
    }

    public void setNomGrupoGeneracion(String nomGrupoGeneracion) {
        this.nomGrupoGeneracion = nomGrupoGeneracion;
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

    public String getCodTipoCombustible() {
        return codTipoCombustible;
    }

    public void setCodTipoCombustible(String codTipoCombustible) {
        this.codTipoCombustible = codTipoCombustible;
    }

    public String getCodCentralGeneracion() {
        return codCentralGeneracion;
    }

    public void setCodCentralGeneracion(String codCentralGeneracion) {
        this.codCentralGeneracion = codCentralGeneracion;
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

    public String getCodCoes() {
        return codCoes;
    }

    public void setCodCoes(String codCoes) {
        this.codCoes = codCoes;
    }

    public String getCodGrupoGeneracionAnt() {
        return codGrupoGeneracionAnt;
    }

    public void setCodGrupoGeneracionAnt(String codGrupoGeneracionAnt) {
        this.codGrupoGeneracionAnt = codGrupoGeneracionAnt;
    }
}