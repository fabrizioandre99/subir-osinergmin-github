package pe.gob.osinergmin.prie.admin.backend.dto.barra;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class AdmBarraSearchDTO extends PaginacionDTO {
    private String codigo;
    private String descripcion;
    private String tension;
    private String brg;
    private String subestacion;
    private String areaDemandada;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTension() {
        return tension;
    }

    public void setTension(String tension) {
        this.tension = tension;
    }

    public String getBrg() {
        return brg;
    }

    public void setBrg(String brg) {
        this.brg = brg;
    }

    public String getSubestacion() {
        return subestacion;
    }

    public void setSubestacion(String subestacion) {
        this.subestacion = subestacion;
    }

    public String getAreaDemandada() {
        return areaDemandada;
    }

    public void setAreaDemandada(String areaDemandada) {
        this.areaDemandada = areaDemandada;
    }
}
