package pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AdmSistemaElectricoCrearDTO {
    private String codSisElec;
    private String nomSisElec;
    private String codEmpresa;
    private String tipSisElec;
    private String fecAlta;
    private String fecBaja;
    private String codSectorTipico;
    private String fecIntercon;
    private Integer areaDemanda;
    private String esSisElec;
}
