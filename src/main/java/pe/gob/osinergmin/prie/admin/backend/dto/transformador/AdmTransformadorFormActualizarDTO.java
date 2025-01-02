package pe.gob.osinergmin.prie.admin.backend.dto.transformador;

import lombok.Data;
import lombok.experimental.Accessors;
import pe.gob.osinergmin.prie.admin.backend.dto.devanado.DevanadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transRotacion.TransRotacionDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class AdmTransformadorFormActualizarDTO {
    private Integer idTransformador;
    private Integer idTipoSisTrans;
    private String codEquipo;
    private String codSubestacion;
    private String codEmpresa;
    private String numSerie;
    private Short annioPes;
    private Short mesPes;
    private Short diaPes;
    private BigDecimal potOnanPri;
    private BigDecimal potOnanSec;
    private BigDecimal potOnanTer;
    private BigDecimal potOnafPri;
    private BigDecimal potOnafSec;
    private BigDecimal potOnafTer;
    private String grupoConexion;
    private Short taps;
    private BigDecimal vccPs;
    private BigDecimal vccPt;
    private BigDecimal vccSt;
    private BigDecimal pcuPs;
    private BigDecimal pcuPt;
    private BigDecimal pcuSt;
    private BigDecimal pfe;
    private String tipoRegulacion;
    private BigDecimal pesoTotal;
    private BigDecimal pesoCobre;
    private String fecAlta;
    private String fecBaja;
    private String estado;
    private String estadoRegistro;
    private List<DevanadoDTO> devanado;
    private List<TransRotacionDTO> transRotacion;
    private BigDecimal tPrimario;
    private BigDecimal tSecundario;
    private BigDecimal tTerciario;

    public Integer getIdTransformador() {
        return idTransformador;
    }

    public void setIdTransformador(Integer idTransformador) {
        this.idTransformador = idTransformador;
    }

    public Integer getIdTipoSisTrans() {
        return idTipoSisTrans;
    }

    public void setIdTipoSisTrans(Integer idTipoSisTrans) {
        this.idTipoSisTrans = idTipoSisTrans;
    }

    public String getCodEquipo() {
        return codEquipo;
    }

    public void setCodEquipo(String codEquipo) {
        this.codEquipo = codEquipo;
    }

    public String getCodSubestacion() {
        return codSubestacion;
    }

    public void setCodSubestacion(String codSubestacion) {
        this.codSubestacion = codSubestacion;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public Short getAnnioPes() {
        return annioPes;
    }

    public void setAnnioPes(Short annioPes) {
        this.annioPes = annioPes;
    }

    public Short getMesPes() {
        return mesPes;
    }

    public void setMesPes(Short mesPes) {
        this.mesPes = mesPes;
    }

    public Short getDiaPes() {
        return diaPes;
    }

    public void setDiaPes(Short diaPes) {
        this.diaPes = diaPes;
    }

    public BigDecimal getPotOnanPri() {
        return potOnanPri;
    }

    public void setPotOnanPri(BigDecimal potOnanPri) {
        this.potOnanPri = potOnanPri;
    }

    public BigDecimal getPotOnanSec() {
        return potOnanSec;
    }

    public void setPotOnanSec(BigDecimal potOnanSec) {
        this.potOnanSec = potOnanSec;
    }

    public BigDecimal getPotOnanTer() {
        return potOnanTer;
    }

    public void setPotOnanTer(BigDecimal potOnanTer) {
        this.potOnanTer = potOnanTer;
    }

    public BigDecimal getPotOnafPri() {
        return potOnafPri;
    }

    public void setPotOnafPri(BigDecimal potOnafPri) {
        this.potOnafPri = potOnafPri;
    }

    public BigDecimal getPotOnafSec() {
        return potOnafSec;
    }

    public void setPotOnafSec(BigDecimal potOnafSec) {
        this.potOnafSec = potOnafSec;
    }

    public BigDecimal getPotOnafTer() {
        return potOnafTer;
    }

    public void setPotOnafTer(BigDecimal potOnafTer) {
        this.potOnafTer = potOnafTer;
    }

    public String getGrupoConexion() {
        return grupoConexion;
    }

    public void setGrupoConexion(String grupoConexion) {
        this.grupoConexion = grupoConexion;
    }

    public Short getTaps() {
        return taps;
    }

    public void setTaps(Short taps) {
        this.taps = taps;
    }

    public BigDecimal getVccPs() {
        return vccPs;
    }

    public void setVccPs(BigDecimal vccPs) {
        this.vccPs = vccPs;
    }

    public BigDecimal getVccPt() {
        return vccPt;
    }

    public void setVccPt(BigDecimal vccPt) {
        this.vccPt = vccPt;
    }

    public BigDecimal getVccSt() {
        return vccSt;
    }

    public void setVccSt(BigDecimal vccSt) {
        this.vccSt = vccSt;
    }

    public BigDecimal getPcuPs() {
        return pcuPs;
    }

    public void setPcuPs(BigDecimal pcuPs) {
        this.pcuPs = pcuPs;
    }

    public BigDecimal getPcuPt() {
        return pcuPt;
    }

    public void setPcuPt(BigDecimal pcuPt) {
        this.pcuPt = pcuPt;
    }

    public BigDecimal getPcuSt() {
        return pcuSt;
    }

    public void setPcuSt(BigDecimal pcuSt) {
        this.pcuSt = pcuSt;
    }

    public BigDecimal getPfe() {
        return pfe;
    }

    public void setPfe(BigDecimal pfe) {
        this.pfe = pfe;
    }

    public String getTipoRegulacion() {
        return tipoRegulacion;
    }

    public void setTipoRegulacion(String tipoRegulacion) {
        this.tipoRegulacion = tipoRegulacion;
    }

    public BigDecimal getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(BigDecimal pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public BigDecimal getPesoCobre() {
        return pesoCobre;
    }

    public void setPesoCobre(BigDecimal pesoCobre) {
        this.pesoCobre = pesoCobre;
    }

    public String getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(String fecAlta) {
        this.fecAlta = fecAlta;
    }

    public String getFecBaja() {
        return fecBaja;
    }

    public void setFecBaja(String fecBaja) {
        this.fecBaja = fecBaja;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public List<DevanadoDTO> getDevanado() {
        return devanado;
    }

    public void setDevanado(List<DevanadoDTO> devanado) {
        this.devanado = devanado;
    }

    public List<TransRotacionDTO> getTransRotacion() {
        return transRotacion;
    }

    public void setTransRotacion(List<TransRotacionDTO> transRotacion) {
        this.transRotacion = transRotacion;
    }

    public BigDecimal gettPrimario() {
        return tPrimario;
    }

    public void settPrimario(BigDecimal tPrimario) {
        this.tPrimario = tPrimario;
    }

    public BigDecimal gettSecundario() {
        return tSecundario;
    }

    public void settSecundario(BigDecimal tSecundario) {
        this.tSecundario = tSecundario;
    }

    public BigDecimal gettTerciario() {
        return tTerciario;
    }

    public void settTerciario(BigDecimal tTerciario) {
        this.tTerciario = tTerciario;
    }
}
