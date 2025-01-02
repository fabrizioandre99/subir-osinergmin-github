package pe.gob.osinergmin.prie.admin.backend.dto.empresa;

import java.util.Date;
import java.util.List;

public class AdmEmpresaFormDTO {
    private String codEmpresa;
    private String dscCortaEmpresa;
    private String dscEmpresa;
    private String ruc;
    private String direccion;
    private String codUbigeo;
    private String estado;
    private String adCodUsuario;
    private Date adFecha;
    private String ipCreacion;
    private List<AdmProcEmpresaDTO> procesos;  // Lista de procesos

    // Getters y Setters

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getDscCortaEmpresa() {
        return dscCortaEmpresa;
    }

    public void setDscCortaEmpresa(String dscCortaEmpresa) {
        this.dscCortaEmpresa = dscCortaEmpresa;
    }

    public String getDscEmpresa() {
        return dscEmpresa;
    }

    public void setDscEmpresa(String dscEmpresa) {
        this.dscEmpresa = dscEmpresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getAdCodUsuario() {
        return adCodUsuario;
    }

    public void setAdCodUsuario(String adCodUsuario) {
        this.adCodUsuario = adCodUsuario;
    }

    public Date getAdFecha() {
        return adFecha;
    }

    public void setAdFecha(Date adFecha) {
        this.adFecha = adFecha;
    }

    public String getIpCreacion() {
        return ipCreacion;
    }

    public void setIpCreacion(String ipCreacion) {
        this.ipCreacion = ipCreacion;
    }

    public List<AdmProcEmpresaDTO> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<AdmProcEmpresaDTO> procesos) {
        this.procesos = procesos;
    }
}
