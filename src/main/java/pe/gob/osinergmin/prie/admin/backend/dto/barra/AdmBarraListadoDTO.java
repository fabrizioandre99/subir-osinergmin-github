package pe.gob.osinergmin.prie.admin.backend.dto.barra;

import lombok.Data;

@Data
public class AdmBarraListadoDTO {
    private String codBarra;
    private String nomBarra;
    private Double tension;
    private String codBrg;
    private Double areaDemanda;
    private String desAreaDemanda;
    private String codSubestacion;
    private String nomSubestacion;
    private String estado;
    private String enRedDistribucion;
    private String codBarraCt;
}
