package pe.gob.osinergmin.prie.admin.backend.dto.coym;

public class AdmCoymFormDTO {
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

    public AdmCoymFormDTO(String codCoym, String nomCoym) {
        this.codCoym = codCoym;
        this.nomCoym = nomCoym;
    }

    public AdmCoymFormDTO() {
    }
}
