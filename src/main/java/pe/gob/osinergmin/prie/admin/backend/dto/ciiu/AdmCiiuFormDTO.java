package pe.gob.osinergmin.prie.admin.backend.dto.ciiu;

public class AdmCiiuFormDTO {
    private String codCiiu;
    private String ciiu;
    private String codActividadEconomica;

    public String getCodCiiu() {
        return codCiiu;
    }

    public void setCodCiiu(String codCiiu) {
        this.codCiiu = codCiiu;
    }

    public String getCiiu() {
        return ciiu;
    }

    public void setCiiu(String ciiu) {
        this.ciiu = ciiu;
    }

    public String getCodActividadEconomica() {
        return codActividadEconomica;
    }

    public void setCodActividadEconomica(String codActividadEconomica) {
        this.codActividadEconomica = codActividadEconomica;
    }

    public AdmCiiuFormDTO(String codCiiu, String ciiu, String codActividadEconomica) {
        this.codCiiu = codCiiu;
        this.ciiu = ciiu;
        this.codActividadEconomica = codActividadEconomica;
    }

    public AdmCiiuFormDTO() {
    }
}
