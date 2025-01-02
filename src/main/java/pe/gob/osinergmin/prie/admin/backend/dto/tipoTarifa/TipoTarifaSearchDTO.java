package pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class TipoTarifaSearchDTO extends PaginacionDTO {
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoTarifaSearchDTO(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoTarifaSearchDTO() {
    }
}
