package pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

import java.util.Date;

public class DiaNoLaborableSearchDTO extends PaginacionDTO {
    private String fecha;
    private String motivo;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public DiaNoLaborableSearchDTO(String fecha, String motivo) {
        this.fecha = fecha;
        this.motivo = motivo;
    }
    public DiaNoLaborableSearchDTO() {}
}
