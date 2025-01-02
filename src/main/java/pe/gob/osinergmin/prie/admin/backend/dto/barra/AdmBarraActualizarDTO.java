package pe.gob.osinergmin.prie.admin.backend.dto.barra;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.barraSisElectrico.BarraSistElectricoDTO;

import java.util.List;

@Data
public class AdmBarraActualizarDTO {
    private String codBarra;
    private String nomBarra;
    private Double tension;
    private String codBrg;
    private Double areaDemanda;
    private String codSubestacion;
    private String estado;
    private String enRedDistribucion;
    private String codBarraCt;
    private List<BarraSistElectricoDTO> barraSistElectricoDTOList;
}
