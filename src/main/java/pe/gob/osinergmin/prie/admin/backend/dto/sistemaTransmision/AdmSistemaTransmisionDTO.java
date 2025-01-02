package pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision;

public class AdmSistemaTransmisionDTO {
    private Integer idSisTrans;
    private String codSisTrans;
    private String nomSisTrans;

    public Integer getIdSisTrans() {
        return idSisTrans;
    }

    public void setIdSisTrans(Integer idSisTrans) {
        this.idSisTrans = idSisTrans;
    }

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

    public AdmSistemaTransmisionDTO(Integer idSisTrans, String codSisTrans, String nomSisTrans) {
        this.idSisTrans = idSisTrans;
        this.codSisTrans = codSisTrans;
        this.nomSisTrans = nomSisTrans;
    }

    public AdmSistemaTransmisionDTO() {
    }
}
