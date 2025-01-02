package pe.gob.osinergmin.prie.admin.backend.dto.region;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class AdmRegionSearchDTO extends PaginacionDTO {
    private String codRegion;
    private String nomRegion;

    public String getCodRegion() {
        return codRegion;
    }

    public void setCodRegion(String codRegion) {
        this.codRegion = codRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public AdmRegionSearchDTO(String codRegion, String nomRegion) {
        this.codRegion = codRegion;
        this.nomRegion = nomRegion;
    }

    public AdmRegionSearchDTO() {
    }
}
