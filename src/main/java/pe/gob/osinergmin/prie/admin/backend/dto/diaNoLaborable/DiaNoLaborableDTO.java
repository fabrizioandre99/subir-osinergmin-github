package pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DiaNoLaborableDTO {
    private String fecha;
    private String tipo;
    private String motivo;
    private String estado;

}
