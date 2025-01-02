package pe.gob.osinergmin.prie.admin.backend.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdmAreaDemanda {
    private Integer areaDemanda;

    private String dscAreaDemanda;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    private String noConjunto;

    private String ipCreacion;

    private String usActualizacion;

    private String ipActualizacion;

    private Date feActualizacion;


}