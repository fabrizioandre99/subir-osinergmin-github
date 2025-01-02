package pe.gob.osinergmin.prie.admin.backend.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AdmTarifa {

    private Integer idTarifa;
    private String codTarifa;
    private String nomTarifa;
    private Integer secuencia;
    private String tipoMedicion;
    private String estado;
    private String adCodUsuario;
    private Date adFecha;

    public Integer getIdTarifa() {return idTarifa;}

    public void setIdTarifa(Integer idTarifa) {this.idTarifa = idTarifa;}

    public String getCodTarifa() {return codTarifa;}

    public void setCodTarifa(String codTarifa) {this.codTarifa = codTarifa;}

    public String getNomTarifa() {return nomTarifa;}

    public void setNomTarifa(String nomTarifa) {this.nomTarifa = nomTarifa;}

    public Integer getSecuencia() {return secuencia;}

    public void setSecuencia(Integer secuencia) {this.secuencia = secuencia;}

    public String getTipoMedicion() {return tipoMedicion;}

    public void setTipoMedicion(String tipoMedicion) {this.tipoMedicion = tipoMedicion;}

    public String getEstado() {return estado;}

    public void setEstado(String estado) {this.estado = estado;}

    public String getAdCodUsuario() {return adCodUsuario;}

    public void setAdCodUsuario(String adCodUsuario) {this.adCodUsuario = adCodUsuario;}

    public Date getAdFecha() {return adFecha;}

    public void setAdFecha(Date adFecha) {this.adFecha = adFecha;}

}