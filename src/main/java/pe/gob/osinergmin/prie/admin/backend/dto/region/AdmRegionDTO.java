package pe.gob.osinergmin.prie.admin.backend.dto.region;

public class AdmRegionDTO {
    private String codRegion;
    private String nomRegion;
    private String estadoRegistro;

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

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public AdmRegionDTO(String codRegion, String nomRegion, String estadoRegistro) {
        this.codRegion = codRegion;
        this.nomRegion = nomRegion;
        this.estadoRegistro = estadoRegistro;
    }

    public AdmRegionDTO() {}
}
