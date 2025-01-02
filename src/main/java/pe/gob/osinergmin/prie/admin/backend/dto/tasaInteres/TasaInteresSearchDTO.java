package pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres;

import com.fasterxml.jackson.annotation.JsonFormat;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

import java.util.Date;

public class TasaInteresSearchDTO extends PaginacionDTO {
    private String fecTasaEmitida;


    public String getFecTasaEmitida() {
        return fecTasaEmitida;
    }

    public void setFecTasaEmitida(String fecTasaEmitida) {
        this.fecTasaEmitida = fecTasaEmitida;
    }

    public TasaInteresSearchDTO(String fecTasaEmitida) {
        this.fecTasaEmitida = fecTasaEmitida;
    }

    public TasaInteresSearchDTO() {
    }
}
