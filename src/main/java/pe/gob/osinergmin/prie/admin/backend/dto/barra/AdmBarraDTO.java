package pe.gob.osinergmin.prie.admin.backend.dto.barra;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.barraSisElectrico.barraSistResponseDTO;

import java.util.List;

@Data
public class AdmBarraDTO {
    private String codigo;
    private String descripcion;
    private Double tension;
    private String brg;
    private String subestacion;
    private String areaDemandada;
    private String codSubestacion;
    private String nomSubestacion;
    private String estado;
    private String enRedDistribucion;
    private String codBarraCt;
    private List<barraSistResponseDTO> barraSistElectricoDTOList;
}
