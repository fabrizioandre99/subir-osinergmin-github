package pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral;

public class AdmTipoCentralDTO {
    private String codTipoCentral;
    private String nomTipoCentral;
    private String estado;

    public String getCodTipoCentral() {
        return codTipoCentral;
    }

    public void setCodTipoCentral(String codTipoCentral) {
        this.codTipoCentral = codTipoCentral;
    }

    public String getNomTipoCentral() {
        return nomTipoCentral;
    }

    public void setNomTipoCentral(String nomTipoCentral) {
        this.nomTipoCentral = nomTipoCentral;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
