package pe.gob.osinergmin.prie.admin.backend.dto.ubigeo;

public class UbigeoDTO {
    private String codUbigeo;
    private String nomUbigeo;
    private String estado;

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

    public UbigeoDTO(String codUbigeo, String nomUbigeo, String estado) {
        this.codUbigeo = codUbigeo;
        this.nomUbigeo = nomUbigeo;
        this.estado = estado;
    }

    public UbigeoDTO() {}
}
