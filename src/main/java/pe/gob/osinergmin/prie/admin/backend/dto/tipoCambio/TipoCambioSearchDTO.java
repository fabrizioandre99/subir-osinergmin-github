package pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio;

import com.fasterxml.jackson.annotation.JsonFormat;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;
import java.util.Date;

public class TipoCambioSearchDTO extends PaginacionDTO {

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public TipoCambioSearchDTO(String fecha) {
        this.fecha = fecha;
    }

    public TipoCambioSearchDTO() {}
}
