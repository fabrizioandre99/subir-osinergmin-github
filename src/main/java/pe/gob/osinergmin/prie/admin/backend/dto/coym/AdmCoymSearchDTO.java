package pe.gob.osinergmin.prie.admin.backend.dto.coym;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class AdmCoymSearchDTO extends PaginacionDTO {
    private String codCoym;
    private String nomCoym;

    public String getCodCoym() {
        return codCoym;
    }

    public void setCodCoym(String codCoym) {
        this.codCoym = codCoym;
    }

    public String getNomCoym() {
        return nomCoym;
    }

    public void setNomCoym(String nomCoym) {
        this.nomCoym = nomCoym;
    }
}
