package pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class AdmGrupoBarraSearchDTO extends PaginacionDTO {
    private String codigo;
    private String codigosBarra;
    private String estado;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigosBarra() {
        return codigosBarra;
    }

    public void setCodigosBarra(String codigosBarra) {
        this.codigosBarra = codigosBarra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
