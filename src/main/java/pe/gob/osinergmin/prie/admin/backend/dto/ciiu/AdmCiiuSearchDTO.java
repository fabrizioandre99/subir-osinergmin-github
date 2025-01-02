package pe.gob.osinergmin.prie.admin.backend.dto.ciiu;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class AdmCiiuSearchDTO extends PaginacionDTO {
    private String codCiiu;

    private String ciiu;

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

    public AdmCiiuSearchDTO(String codCiiu, String ciiu) {
        this.codCiiu = codCiiu;
        this.ciiu = ciiu;
    }

    public AdmCiiuSearchDTO() {
    }
}
