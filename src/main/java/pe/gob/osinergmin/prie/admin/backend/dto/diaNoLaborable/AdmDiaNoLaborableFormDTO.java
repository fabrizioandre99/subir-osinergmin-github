package pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable;

import lombok.Data;

import java.util.Date;

@Data
public class AdmDiaNoLaborableFormDTO {
    private String fecha;
    private String tipo;
    private String motivo;
    private String estado;

}
