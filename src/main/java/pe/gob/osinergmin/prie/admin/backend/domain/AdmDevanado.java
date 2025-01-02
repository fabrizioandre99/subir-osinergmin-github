package pe.gob.osinergmin.prie.admin.backend.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Devanado
 */
@Data
public class AdmDevanado {
    private Integer idDevanado;

    private String codDevanado;

    private Integer idTransformador;

    private String codBarra;

    private Double areaDemanda;

    private Double potDevanado;

    private String usuarioCreacion;

    private String terminalCreacion;

    private Date fechaCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    private String usuarioEliminacion;

    private String terminalEliminacion;

    private Date fechaEliminacion;

    private String estado;

}