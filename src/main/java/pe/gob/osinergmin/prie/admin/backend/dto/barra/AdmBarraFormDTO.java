package pe.gob.osinergmin.prie.admin.backend.dto.barra;

import java.math.BigDecimal;

public class AdmBarraFormDTO {
    private String codBarra;
    private String nomBarra;
    private Double tension;
    private String codBrg;
    private Double areaDemanda;
    private String codSubestacion;
    private String estado;
    private String enRedDistribucion;
    private String codBarraCt;

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getNomBarra() {
        return nomBarra;
    }

    public void setNomBarra(String nomBarra) {
        this.nomBarra = nomBarra;
    }

    public Double getTension() {
        return tension;
    }

    public void setTension(Double tension) {
        this.tension = tension;
    }

    public String getCodBrg() {
        return codBrg;
    }

    public void setCodBrg(String codBrg) {
        this.codBrg = codBrg;
    }

    public Double getAreaDemanda() {
        return areaDemanda;
    }

    public void setAreaDemanda(Double areaDemanda) {
        this.areaDemanda = areaDemanda;
    }

    public String getCodSubestacion() {
        return codSubestacion;
    }

    public void setCodSubestacion(String codSubestacion) {
        this.codSubestacion = codSubestacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEnRedDistribucion() {
        return enRedDistribucion;
    }

    public void setEnRedDistribucion(String enRedDistribucion) {
        this.enRedDistribucion = enRedDistribucion;
    }

    public String getCodBarraCt() {
        return codBarraCt;
    }

    public void setCodBarraCt(String codBarraCt) {
        this.codBarraCt = codBarraCt;
    }

    public AdmBarraFormDTO(String codBarra, String nomBarra, Double tension, String codBrg, Double areaDemanda, String codSubestacion, String estado, String enRedDistribucion, String codBarraCt) {
        this.codBarra = codBarra;
        this.nomBarra = nomBarra;
        this.tension = tension;
        this.codBrg = codBrg;
        this.areaDemanda = areaDemanda;
        this.codSubestacion = codSubestacion;
        this.estado = estado;
        this.enRedDistribucion = enRedDistribucion;
        this.codBarraCt = codBarraCt;
    }

    public AdmBarraFormDTO() {
    }
}
