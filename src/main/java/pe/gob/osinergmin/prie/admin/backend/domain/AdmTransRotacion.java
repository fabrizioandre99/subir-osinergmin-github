package pe.gob.osinergmin.prie.admin.backend.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AdmTransRotacion {
    private Integer idTransfRotacion;

    private String usuarioCreacion;

    private String codSubestacion;

    private Integer idTransformador;

    private String codSubestacionDestino;

    private Date fecMovimiento;

    private String terminalCreacion;

    private Date fechaCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    private String usuarioEliminacion;

    private String terminalEliminacion;

    private Date fechaEliminacion;

    private String situacion;

    private String estado;

}