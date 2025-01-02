package pe.gob.osinergmin.prie.admin.backend.dto.subestacion;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarra;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraFormDTO;

import java.util.List;

public class AdmSubestacionActualizarFormDTO {
    private String codSubestacion;
    private String nomSubestacion;
    private String codUbigeo;
    private String estado;
    private Integer idSisTrans;
    private List<AdmBarraFormDTO> barra;

    public String getCodSubestacion() {
        return codSubestacion;
    }

    public void setCodSubestacion(String codSubestacion) {
        this.codSubestacion = codSubestacion;
    }

    public String getNomSubestacion() {
        return nomSubestacion;
    }

    public void setNomSubestacion(String nomSubestacion) {
        this.nomSubestacion = nomSubestacion;
    }

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdSisTrans() {
        return idSisTrans;
    }

    public void setIdSisTrans(Integer idSisTrans) {
        this.idSisTrans = idSisTrans;
    }

    public List<AdmBarraFormDTO> getBarra() {
        return barra;
    }

    public void setBarra(List<AdmBarraFormDTO> barra) {
        this.barra = barra;
    }
}
