package pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa;

public class TipoTarifaFormDTO {
    private String codtarifa;
    private String descripcion;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodtarifa() {
        return codtarifa;
    }

    public void setCodtarifa(String codtarifa) {
        this.codtarifa = codtarifa;
    }

    public TipoTarifaFormDTO(String codtarifa, String descripcion, String estado) {
        this.codtarifa = codtarifa;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public TipoTarifaFormDTO() {}
}
