package pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa;

public class TipoEmpresaDTO {
    private String codTipoEmpresa;
    private String dscTipoEmpresa;
    private String estado;

    public String getCodTipoEmpresa() {
        return codTipoEmpresa;
    }

    public void setCodTipoEmpresa(String codTipoEmpresa) {
        this.codTipoEmpresa = codTipoEmpresa;
    }

    public String getDscTipoEmpresa() {
        return dscTipoEmpresa;
    }

    public void setDscTipoEmpresa(String dscTipoEmpresa) {
        this.dscTipoEmpresa = dscTipoEmpresa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoEmpresaDTO(String codTipoEmpresa, String dscTipoEmpresa, String estado) {
        this.codTipoEmpresa = codTipoEmpresa;
        this.dscTipoEmpresa = dscTipoEmpresa;
        this.estado = estado;
    }

    public TipoEmpresaDTO() {}
}
