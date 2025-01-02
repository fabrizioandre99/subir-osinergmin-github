package pe.gob.osinergmin.prie.admin.backend.dto.embalse;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmEmbalseSearchDTO extends PaginacionDTO {
    private String codEmbalse;
    private String nomEmbalse;
    private String codCoes;
    private String estadoRegistro;
    private String codEmbalseAnt;

    public AdmEmbalseSearchDTO(String codEmbalse, String nomEmbalse, String codCoes, String estadoRegistro, String codEmbalseAnt) {
        this.codEmbalse = codEmbalse;
        this.nomEmbalse = nomEmbalse;
        this.codCoes = codCoes;
        this.estadoRegistro = estadoRegistro;
        this.codEmbalseAnt = codEmbalseAnt;
    }

    public AdmEmbalseSearchDTO() {}
}
