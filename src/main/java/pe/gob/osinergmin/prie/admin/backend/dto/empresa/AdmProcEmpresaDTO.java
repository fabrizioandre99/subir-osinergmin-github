package pe.gob.osinergmin.prie.admin.backend.dto.empresa;

import lombok.Data;

import java.util.Date;

@Data
public class AdmProcEmpresaDTO {
    private String codEmpresa;
    private String codTipoEmpresa;
    private String codFuncionProcSuperv;
    private String codProcSupervision;
    private String adCodUsuario;
    private Date adFecha;
    private Date fecAlta;
    private Date fecBaja;
    private Integer idProcEmpresa;

}
