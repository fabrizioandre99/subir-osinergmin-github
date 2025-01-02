package pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaElectricoDet;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaElectricoDet.SistemaElectricoDetDTO;

import java.util.Date;
import java.util.List;

@Data
public class AdmSistemaElectricoActualizarDTO {
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
    private List<SistemaElectricoDetDTO> sistemaElectricoDets;
}
