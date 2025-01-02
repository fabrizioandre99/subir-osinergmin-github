package pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico;

import lombok.Data;

import java.util.Date;

@Data
public class SistemaElectricoInsertDTO {
    private String codSisElec;
    private String nomSisElec;
    private String codEmpresa;
    private String tipSisElec;
    private String fecAlta;
    private String fecBaja;
    private String codSectorTipico;
    private String fecIntercon;
    private Integer areaDemanda;
    private String adCodUsuario;
    private Date adFecha;
    private String ipCreacion;
    private String usActualizacion;
    private String ipActualizacion;
    private Date feActualizacion;
    private String esSisElec;
}
