package pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision;

import java.util.Date;

public class AdmSistemaTransmisionFormdDTO {
    private Integer idSisTrans;
    private String codSisTrans;
    private String nomSisTrans;
    private String estado;

    public Integer getIdSisTrans() {
        return idSisTrans;
    }

    public void setIdSisTrans(Integer idSisTrans) {
        this.idSisTrans = idSisTrans;
    }

    public String getCodSisTrans() {
        return codSisTrans;
    }

    public void setCodSisTrans(String codSisTrans) {
        this.codSisTrans = codSisTrans;
    }

    public String getNomSisTrans() {
        return nomSisTrans;
    }

    public void setNomSisTrans(String nomSisTrans) {
        this.nomSisTrans = nomSisTrans;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public AdmSistemaTransmisionFormdDTO(Integer idSisTrans, String codSisTrans, String nomSisTrans, String estado) {
        this.idSisTrans = idSisTrans;
        this.codSisTrans = codSisTrans;
        this.nomSisTrans = nomSisTrans;
        this.estado = estado;
    }

    public AdmSistemaTransmisionFormdDTO() {}
}
