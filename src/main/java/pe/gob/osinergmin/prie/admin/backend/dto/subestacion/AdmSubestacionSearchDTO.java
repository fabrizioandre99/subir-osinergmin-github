package pe.gob.osinergmin.prie.admin.backend.dto.subestacion;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class AdmSubestacionSearchDTO extends PaginacionDTO {
    private String codSubestacion;
    private String nomSubestacion;
    private String nomDepartamento;
    private String nomProvincia;
    private String nomDistrito;

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

    public String getNomDepartamento() {
        return nomDepartamento;
    }

    public void setNomDepartamento(String nomDepartamento) {
        this.nomDepartamento = nomDepartamento;
    }

    public String getNomProvincia() {
        return nomProvincia;
    }

    public void setNomProvincia(String nomProvincia) {
        this.nomProvincia = nomProvincia;
    }

    public String getNomDistrito() {
        return nomDistrito;
    }

    public void setNomDistrito(String nomDistrito) {
        this.nomDistrito = nomDistrito;
    }
}
