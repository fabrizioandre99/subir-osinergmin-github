package pe.gob.osinergmin.prie.admin.backend.dto.tarifa;


public class TarifaDTO {

    private Integer idTarifa;
    private String codTarifa;
    private String nomTarifa;
    private Integer secuencia;
    private String tipoMedicion;
    private String estado;
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdTarifa() {return idTarifa;}

    public void setIdTarifa(Integer idTarifa) {this.idTarifa = idTarifa;}

    public String getCodTarifa() {return codTarifa;}

    public void setCodTarifa(String codTarifa) {this.codTarifa = codTarifa;}

    public String getNomTarifa() {return nomTarifa;}

    public void setNomTarifa(String nomTarifa) {this.nomTarifa = nomTarifa;}

    public Integer getSecuencia() {return secuencia;}

    public void setSecuencia(Integer secuencia) {this.secuencia = secuencia;}

    public String getTipoMedicion() {return tipoMedicion;}

    public void setTipoMedicion(String tipoMedicion) {this.tipoMedicion = tipoMedicion;}

    public String getEstado() {return estado;}

    public void setEstado(String estado) {this.estado = estado;}

}
