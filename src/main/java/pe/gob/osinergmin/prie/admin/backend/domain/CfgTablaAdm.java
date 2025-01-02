package pe.gob.osinergmin.prie.admin.backend.domain;

/**
 * Tabla de configuración de campos de tablas maestras
 */
public class CfgTablaAdm {
    private String codTabla;
    private String descripcionTabla;
    private String estadoTabla;
    private String nombreCorto;
    private String flagAuditoria;

    public String getCodTabla() {
        return codTabla;
    }

    public void setCodTabla(String codTabla) {
        this.codTabla = codTabla;
    }

    public String getDescripcionTabla() {
        return descripcionTabla;
    }

    public void setDescripcionTabla(String descripcionTabla) {
        this.descripcionTabla = descripcionTabla;
    }

    public String getEstadoTabla() {
        return estadoTabla;
    }

    public void setEstadoTabla(String estadoTabla) {
        this.estadoTabla = estadoTabla;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getFlagAuditoria() {
        return flagAuditoria;
    }

    public void setFlagAuditoria(String flagAuditoria) {
        this.flagAuditoria = flagAuditoria;
    }
}