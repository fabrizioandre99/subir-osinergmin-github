package pe.gob.osinergmin.prie.admin.backend.domain;

/**
 * Tabla de registro de parámetros.
 */
public class CfgParametro {
    private String codGrupo;
    private String codParametro;
    private String descParametro;
    private String valParametro;

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getCodParametro() {
        return codParametro;
    }

    public void setCodParametro(String codParametro) {
        this.codParametro = codParametro;
    }

    public String getDescParametro() {
        return descParametro;
    }

    public void setDescParametro(String descParametro) {
        this.descParametro = descParametro;
    }

    public String getValParametro() {
        return valParametro;
    }

    public void setValParametro(String valParametro) {
        this.valParametro = valParametro;
    }
}