package pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class AdmSistemaTransmisionSearchDTO extends PaginacionDTO {
    private String codSisTrans;
    private String nomSisTrans;

    public String getCodSisTrans() {
        return codSisTrans;
    }

    public void setCodSisTrans(String codSisTrans) {
        this.codSisTrans = codSisTrans;
    }

    public String getNomSisTrans() {
        return nomSisTrans;
    }

    public void setNomSisTrans(String nomSisTrans) {
        this.nomSisTrans = nomSisTrans;
    }

    public AdmSistemaTransmisionSearchDTO(String codSisTrans, String nomSisTrans) {
        this.codSisTrans = codSisTrans;
        this.nomSisTrans = nomSisTrans;
    }

    public AdmSistemaTransmisionSearchDTO() {
    }
}
