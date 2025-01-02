package pe.gob.osinergmin.prie.admin.backend.dto.admUit;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class AdmUitSearchDTO extends PaginacionDTO {
    private String anioMes;

    public String getAnioMes() {
        return anioMes;
    }

    public void setAnioMes(String anioMes) {
        this.anioMes = anioMes;
    }

    public AdmUitSearchDTO(String anioMes) {
        this.anioMes = anioMes;
    }
    public AdmUitSearchDTO() {}
}
