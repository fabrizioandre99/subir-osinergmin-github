package pe.gob.osinergmin.prie.admin.backend.dto.subestacion;

public class AdmSubestacionFormDTO {
    private String codSubestacion;
    private String nomSubestacion;
    private String codUbigeo;
    private String estado;
    private Integer idSisTrans;

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

    public Integer getIdSisTrans() {
        return idSisTrans;
    }

    public void setIdSisTrans(Integer idSisTrans) {
        this.idSisTrans = idSisTrans;
    }

    public AdmSubestacionFormDTO(String codSubestacion, String nomSubestacion, String codUbigeo, String estado) {
        this.codSubestacion = codSubestacion;
        this.nomSubestacion = nomSubestacion;
        this.codUbigeo = codUbigeo;
        this.estado = estado;
    }

    public AdmSubestacionFormDTO() {
    }
}
