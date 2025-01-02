package pe.gob.osinergmin.prie.admin.backend.dto.cuenca;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmCuencaSearchDTO extends PaginacionDTO {
    private String codCuenca;
    private String nomCuenca;
    private String codCoes;
    private String codCuencaAnt;
    private String estadoRegistro;

    public AdmCuencaSearchDTO(String codCuenca, String nomCuenca, String codCoes, String codCuencaAnt, String estadoRegistro) {
        this.codCuenca = codCuenca;
        this.nomCuenca = nomCuenca;
        this.codCoes = codCoes;
        this.codCuencaAnt = codCuencaAnt;
        this.estadoRegistro = estadoRegistro;
    }

    public AdmCuencaSearchDTO() {
    }
}
