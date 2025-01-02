package pe.gob.osinergmin.prie.admin.backend.dto.empresa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdmEmpresaDTO {
    private String codEmpresa;
    private String codUbigeo;
    private String direccion;
    private String dscCortaEmpresa;
    private String dscEmpresa;
    private String ruc;
    private String estado;
    private List<ProcesoAsociadoDTO> procesosAsociados;


    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ProcesoAsociadoDTO> getProcesosAsociados() {
        return procesosAsociados;
    }

    public void setProcesosAsociados(List<ProcesoAsociadoDTO> procesosAsociados) {
        this.procesosAsociados = procesosAsociados;
    }

    public void setProcesosAsociadosFromString(String json) {
        if (json != null && !json.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<ProcesoAsociadoDTO> list = mapper.readValue(json, new TypeReference<List<ProcesoAsociadoDTO>>() {});
                // Filter out elements where all fields are null
                this.procesosAsociados = list.stream()
                        .filter(proceso -> !allFieldsNull(proceso))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
                this.procesosAsociados = Collections.emptyList();
            }
        } else {
            this.procesosAsociados = Collections.emptyList();
        }
    }

    private boolean allFieldsNull(ProcesoAsociadoDTO proceso) {
        return proceso.getCodProcSupervision() == null &&
                proceso.getCodTipoEmpresa() == null &&
                proceso.getCodFuncionProcSuperv() == null &&
                proceso.getIdProcEmpresa() == null;
    }

}
