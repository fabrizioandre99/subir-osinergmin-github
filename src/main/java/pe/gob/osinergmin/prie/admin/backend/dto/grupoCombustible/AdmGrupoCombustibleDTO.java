package pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible;

public class AdmGrupoCombustibleDTO {
    private String codGrupoCombustible;
    private String nomGrupoCombustible;
    private String estado;

    public String getCodGrupoCombustible() {
        return codGrupoCombustible;
    }

    public void setCodGrupoCombustible(String codGrupoCombustible) {
        this.codGrupoCombustible = codGrupoCombustible;
    }

    public String getNomGrupoCombustible() {
        return nomGrupoCombustible;
    }

    public void setNomGrupoCombustible(String nomGrupoCombustible) {
        this.nomGrupoCombustible = nomGrupoCombustible;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
