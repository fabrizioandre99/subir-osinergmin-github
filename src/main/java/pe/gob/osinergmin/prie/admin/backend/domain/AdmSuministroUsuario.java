package pe.gob.osinergmin.prie.admin.backend.domain;

import java.math.BigDecimal;

public class AdmSuministroUsuario {
    private String codSuministroUsuario;

    private String codUsuarioLibre;

    private String nombreUsuarioLibre;

    private String direccionSuministro;

    private String nroSuministroEmp;

    private String ubigeo;

    private String codActividadEconomica;

    private String codCoes;

    private String codSuministroUsuarioAnt;

    private String codciiu;

    private String codPtoSuministro;

    private String codBrg;

    private BigDecimal areaDemanda;

    private String codSisElectrico;

    public String getCodSuministroUsuario() {
        return codSuministroUsuario;
    }

    public void setCodSuministroUsuario(String codSuministroUsuario) {
        this.codSuministroUsuario = codSuministroUsuario;
    }

    public String getCodUsuarioLibre() {
        return codUsuarioLibre;
    }

    public void setCodUsuarioLibre(String codUsuarioLibre) {
        this.codUsuarioLibre = codUsuarioLibre;
    }

    public String getNombreUsuarioLibre() {
        return nombreUsuarioLibre;
    }

    public void setNombreUsuarioLibre(String nombreUsuarioLibre) {
        this.nombreUsuarioLibre = nombreUsuarioLibre;
    }

    public String getDireccionSuministro() {
        return direccionSuministro;
    }

    public void setDireccionSuministro(String direccionSuministro) {
        this.direccionSuministro = direccionSuministro;
    }

    public String getNroSuministroEmp() {
        return nroSuministroEmp;
    }

    public void setNroSuministroEmp(String nroSuministroEmp) {
        this.nroSuministroEmp = nroSuministroEmp;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getCodActividadEconomica() {
        return codActividadEconomica;
    }

    public void setCodActividadEconomica(String codActividadEconomica) {
        this.codActividadEconomica = codActividadEconomica;
    }

    public String getCodCoes() {
        return codCoes;
    }

    public void setCodCoes(String codCoes) {
        this.codCoes = codCoes;
    }

    public String getCodSuministroUsuarioAnt() {
        return codSuministroUsuarioAnt;
    }

    public void setCodSuministroUsuarioAnt(String codSuministroUsuarioAnt) {
        this.codSuministroUsuarioAnt = codSuministroUsuarioAnt;
    }

    public String getCodciiu() {
        return codciiu;
    }

    public void setCodciiu(String codciiu) {
        this.codciiu = codciiu;
    }

    public String getCodPtoSuministro() {
        return codPtoSuministro;
    }

    public void setCodPtoSuministro(String codPtoSuministro) {
        this.codPtoSuministro = codPtoSuministro;
    }

    public String getCodBrg() {
        return codBrg;
    }

    public void setCodBrg(String codBrg) {
        this.codBrg = codBrg;
    }

    public BigDecimal getAreaDemanda() {
        return areaDemanda;
    }

    public void setAreaDemanda(BigDecimal areaDemanda) {
        this.areaDemanda = areaDemanda;
    }

    public String getCodSisElectrico() {
        return codSisElectrico;
    }

    public void setCodSisElectrico(String codSisElectrico) {
        this.codSisElectrico = codSisElectrico;
    }
}