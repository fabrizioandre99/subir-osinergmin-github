package pe.gob.osinergmin.prie.admin.backend.dto.barraSisElectrico;

import lombok.Data;

import java.util.Date;

@Data
public class BarraSistElectricoDTO {
    private String codSisElec;
    private String codBarra;
    private Date fecAlta;
    private Date fecBaja;
    private String adCodUsuario;
    private Date adFecha;
    private String estado;
}
