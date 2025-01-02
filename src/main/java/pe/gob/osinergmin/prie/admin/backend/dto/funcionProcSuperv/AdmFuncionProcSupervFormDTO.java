package pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv;

import java.util.Date;

public class AdmFuncionProcSupervFormDTO {
    private String codProcSupervision;
    private String codFuncionProcSuperv;
    private String dscFuncionProcSuperv;

    public String getCodProcSupervision() {
        return codProcSupervision;
    }

    public void setCodProcSupervision(String codProcSupervision) {
        this.codProcSupervision = codProcSupervision;
    }

    public String getCodFuncionProcSuperv() {
        return codFuncionProcSuperv;
    }

    public void setCodFuncionProcSuperv(String codFuncionProcSuperv) {
        this.codFuncionProcSuperv = codFuncionProcSuperv;
    }

    public String getDscFuncionProcSuperv() {
        return dscFuncionProcSuperv;
    }

    public void setDscFuncionProcSuperv(String dscFuncionProcSuperv) {
        this.dscFuncionProcSuperv = dscFuncionProcSuperv;
    }

    public AdmFuncionProcSupervFormDTO(String codProcSupervision, String codFuncionProcSuperv, String dscFuncionProcSuperv) {
        this.codProcSupervision = codProcSupervision;
        this.codFuncionProcSuperv = codFuncionProcSuperv;
        this.dscFuncionProcSuperv = dscFuncionProcSuperv;
    }
    public AdmFuncionProcSupervFormDTO() {}
}
