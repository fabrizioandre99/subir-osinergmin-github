package pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans;

public class AdmTipoSistTransFormDTO {
    private Integer idTipoSisTrans;
    private String codTipoSisTrans;
    private String tipSisTrans;
    private String estado;

    public Integer getIdTipoSisTrans() {
        return idTipoSisTrans;
    }

    public void setIdTipoSisTrans(Integer idTipoSisTrans) {
        this.idTipoSisTrans = idTipoSisTrans;
    }

    public String getCodTipoSisTrans() {
        return codTipoSisTrans;
    }

    public void setCodTipoSisTrans(String codTipoSisTrans) {
        this.codTipoSisTrans = codTipoSisTrans;
    }

    public String getTipSisTrans() {
        return tipSisTrans;
    }

    public void setTipSisTrans(String tipSisTrans) {
        this.tipSisTrans = tipSisTrans;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public AdmTipoSistTransFormDTO(Integer idTipoSisTrans, String codTipoSisTrans, String tipSisTrans, String estado) {
        this.idTipoSisTrans = idTipoSisTrans;
        this.codTipoSisTrans = codTipoSisTrans;
        this.tipSisTrans = tipSisTrans;
        this.estado = estado;
    }

    public AdmTipoSistTransFormDTO() {}
}
