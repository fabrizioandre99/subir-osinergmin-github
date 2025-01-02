package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

/**
 * Tabla de registro de CIIU
 */
public class AdmCiiu {
    private String codCiiu;

    private String ciiu;

    private String codActividadEconomica;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodCiiu() {
        return codCiiu;
    }

    public void setCodCiiu(String codCiiu) {
        this.codCiiu = codCiiu;
    }

    public String getCiiu() {
        return ciiu;
    }

    public void setCiiu(String ciiu) {
        this.ciiu = ciiu;
    }

    public String getCodActividadEconomica() {
        return codActividadEconomica;
    }

    public void setCodActividadEconomica(String codActividadEconomica) {
        this.codActividadEconomica = codActividadEconomica;
    }

    public String getAdCodUsuario() {
        return adCodUsuario;
    }

    public void setAdCodUsuario(String adCodUsuario) {
        this.adCodUsuario = adCodUsuario;
    }

    public Date getAdFecha() {
        return adFecha;
    }

    public void setAdFecha(Date adFecha) {
        this.adFecha = adFecha;
    }
}