package pe.gob.osinergmin.prie.admin.backend.dto.devanado;

import lombok.Data;

@Data
public class DevanadoDTO {
    private Integer idDevanado;
    private String codDevanado;
    private Integer idTransformador;
    private String codBarra;
    private Double areaDemanda;
    private Double potDevanado;
    private String estado;
    private Double tension;
}
