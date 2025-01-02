package pe.gob.osinergmin.prie.admin.backend.dto.transRotacion;


public class TransRotacionDTO {
    private Integer idTransfRotacion;
    private String usuarioCreacion;
    private String codSubestacion;
    private Integer idTransformador;
    private String codSubestacionDestino;
    private String fecMovimiento;
    private String situacion;
    private String estado;

    public Integer getIdTransfRotacion() {
        return idTransfRotacion;
    }

    public void setIdTransfRotacion(Integer idTransfRotacion) {
        this.idTransfRotacion = idTransfRotacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getCodSubestacion() {
        return codSubestacion;
    }

    public void setCodSubestacion(String codSubestacion) {
        this.codSubestacion = codSubestacion;
    }

    public Integer getIdTransformador() {
        return idTransformador;
    }

    public void setIdTransformador(Integer idTransformador) {
        this.idTransformador = idTransformador;
    }

    public String getCodSubestacionDestino() {
        return codSubestacionDestino;
    }

    public void setCodSubestacionDestino(String codSubestacionDestino) {
        this.codSubestacionDestino = codSubestacionDestino;
    }

    public String getFecMovimiento() {
        return fecMovimiento;
    }

    public void setFecMovimiento(String fecMovimiento) {
        this.fecMovimiento = fecMovimiento;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TransRotacionDTO(Integer idTransfRotacion, String usuarioCreacion, String codSubestacion, Integer idTransformador, String codSubestacionDestino, String fecMovimiento, String situacion, String estado) {
        this.idTransfRotacion = idTransfRotacion;
        this.usuarioCreacion = usuarioCreacion;
        this.codSubestacion = codSubestacion;
        this.idTransformador = idTransformador;
        this.codSubestacionDestino = codSubestacionDestino;
        this.fecMovimiento = fecMovimiento;
        this.situacion = situacion;
        this.estado = estado;
    }

    public TransRotacionDTO() {
    }
}
