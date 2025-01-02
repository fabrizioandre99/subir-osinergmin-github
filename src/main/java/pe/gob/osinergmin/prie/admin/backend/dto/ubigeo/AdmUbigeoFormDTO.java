package pe.gob.osinergmin.prie.admin.backend.dto.ubigeo;

import java.util.Date;

public class AdmUbigeoFormDTO {
    private String codUbigeo;
    private String nomUbigeo;
    private String estado;
    private Date adFecha;

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public String getNomUbigeo() {
        return nomUbigeo;
    }

    public void setNomUbigeo(String nomUbigeo) {
        this.nomUbigeo = nomUbigeo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getAdFecha() {
        return adFecha;
    }

    public void setAdFecha(Date adFecha) {
        this.adFecha = adFecha;
    }

    public AdmUbigeoFormDTO(String codUbigeo, String nomUbigeo, String estado, Date adFecha) {
        this.codUbigeo = codUbigeo;
        this.nomUbigeo = nomUbigeo;
        this.estado = estado;
        this.adFecha = adFecha;
    }

    public AdmUbigeoFormDTO() {}
}
