package pe.gob.osinergmin.prie.admin.backend.dto.ubigeo;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class UbigeoSearchDTO extends PaginacionDTO {
    private String codUbigeo;
    private String nomUbigeo;

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

    public UbigeoSearchDTO(String codUbigeo, String nomUbigeo) {
        this.codUbigeo = codUbigeo;
        this.nomUbigeo = nomUbigeo;
    }

    public UbigeoSearchDTO() {
    }
}
