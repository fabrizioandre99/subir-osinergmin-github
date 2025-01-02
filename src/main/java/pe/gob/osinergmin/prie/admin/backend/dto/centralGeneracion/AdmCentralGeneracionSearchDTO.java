package pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmCentralGeneracionSearchDTO extends PaginacionDTO {
    private String codCentralGeneracion;
    private String nomCentralGeneracion;
    private String codTipoCentral;
    private String estado;

    public AdmCentralGeneracionSearchDTO(String codCentralGeneracion, String nomCentralGeneracion, String codTipoCentral, String estado) {
        this.codCentralGeneracion = codCentralGeneracion;
        this.nomCentralGeneracion = nomCentralGeneracion;
        this.codTipoCentral = codTipoCentral;
        this.estado = estado;
    }

    public AdmCentralGeneracionSearchDTO() {
    }
}
