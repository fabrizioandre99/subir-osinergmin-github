package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmSubestacion {
    private String codSubestacion;

    private String nomSubestacion;

    private String codUbigeo;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    private Integer idSisTrans;

    private String ipCreacion;

    private String usActualizacion;

    private String ipActualizacion;

    private Date feActualizacion;

    public String getCodSubestacion() {
        return codSubestacion;
    }

    public void setCodSubestacion(String codSubestacion) {
        this.codSubestacion = codSubestacion;
    }

    public String getNomSubestacion() {
        return nomSubestacion;
    }

    public void setNomSubestacion(String nomSubestacion) {
        this.nomSubestacion = nomSubestacion;
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

    public Integer getIdSisTrans() {
        return idSisTrans;
    }

    public void setIdSisTrans(Integer idSisTrans) {
        this.idSisTrans = idSisTrans;
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