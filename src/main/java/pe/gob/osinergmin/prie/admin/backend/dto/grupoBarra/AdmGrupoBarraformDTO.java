package pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra;

import pe.gob.osinergmin.prie.admin.backend.dto.barra.CodBarraDTO;

import java.util.List;

public class AdmGrupoBarraformDTO {
    private String codBarraGrupo;
    private String estado;
    private List<CodBarraDTO> codBarras;

    public String getCodBarraGrupo() {
        return codBarraGrupo;
    }

    public void setCodBarraGrupo(String codBarraGrupo) {
        this.codBarraGrupo = codBarraGrupo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<CodBarraDTO> getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(List<CodBarraDTO> codBarras) {
        this.codBarras = codBarras;
    }
}
