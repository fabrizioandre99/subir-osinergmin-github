package pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaElectricoDet.SistemaElectricoDetResultDTO;

import java.util.Date;
import java.util.List;

@Data
public class AdmSistemaElectricoDTO {
    private String codSisElec;
    private String nomSisElec;
    private String codEmpresa;
    private String desEmpresa;
    private String tipSisElec;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fecIntercon;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fecBaja;

    private Integer areaDemanda;
    private String desAreaDemanda;
    private String codSectorTipico;
    private String esSisElec;
    private List<SistemaElectricoDetResultDTO> sistemaElectricoDets;
}
