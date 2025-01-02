package pe.gob.osinergmin.prie.admin.backend.dto.coym;

public class AdmCoymDTO {
    private String codCoym;
    private String nomCoym;
    private String estadoRegistro;

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

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public AdmCoymDTO(String codCoym, String nomCoym, String estadoRegistro) {
        this.codCoym = codCoym;
        this.nomCoym = nomCoym;
        this.estadoRegistro = estadoRegistro;
    }

    public AdmCoymDTO() {
    }
}
