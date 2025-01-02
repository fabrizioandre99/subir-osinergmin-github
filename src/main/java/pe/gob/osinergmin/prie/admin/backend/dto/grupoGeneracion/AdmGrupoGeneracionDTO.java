package pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.util.Date;

@Data
public class AdmGrupoGeneracionDTO {
    private String codGrupoGeneracion;
    private String nomGrupoGeneracion;
    private String codTipoCombustible;
    private String nomTipoCombustible;
    private String codCentralGeneracion;
    private String nomCentralGeneracion;
    private String estado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fecAlta;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fecBaja;

    private String codCoes;
    private String codGrupoGeneracionAnt;
}
