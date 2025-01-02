package pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AdmSuministroUsuarioFormDTO {
    private String codSuministroUsuario;
    private String codUsuarioLibre;
    private String nombreUsuarioLibre;
    private String direccionSuministro;
    private String nroSuministroEmp;
    private String ubigeo;
    private String codActividadEconomica;
    private String codCoes;
    private String codSuministroUsuarioAnt;
    private String codciiu;
    private String codPtoSuministro;
    private String codBrg;
    private BigDecimal areaDemanda;
    private String codSisElectrico;
}
