package pe.gob.osinergmin.prie.admin.backend.dto.sistemaElectricoDet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SistemaElectricoDetDTO {
    private Integer idSeDet;
    private String codSisElec;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String fechaAsociacion;

    private String codSisElecAnt;
    private String adCodUsuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date adFecha;
}
