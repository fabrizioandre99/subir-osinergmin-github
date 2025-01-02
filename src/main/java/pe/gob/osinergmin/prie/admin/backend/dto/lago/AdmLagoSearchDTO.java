package pe.gob.osinergmin.prie.admin.backend.dto.lago;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmLagoSearchDTO extends PaginacionDTO {
    private String codLago;
    private String nomLago;
    private String codCoes;
    private String codLagoAnt;
    private String estadoRegistro;

    public AdmLagoSearchDTO(String codLago, String nomLago, String codCoes, String codLagoAnt, String estadoRegistro) {
        this.codLago = codLago;
        this.nomLago = nomLago;
        this.codCoes = codCoes;
        this.codLagoAnt = codLagoAnt;
        this.estadoRegistro = estadoRegistro;
    }

    public AdmLagoSearchDTO() {}
}
