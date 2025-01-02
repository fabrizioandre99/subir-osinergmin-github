package pe.gob.osinergmin.prie.admin.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class AdmTasaInteresSbs {
    private Date fecTasaEmitida;

    private String fecTasaEmitidaString;

    private Integer tamn;

    private Integer tipmn;

    private Double tasaAnual;

    private Double tasaMensual;

    private Double tasaDiaria;

    private String estadoRegistro;

    private String usuarioCreacion;

    private Date fechaCreacion;

    private String terminalCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    public Date getFecTasaEmitida() {
        return fecTasaEmitida;
    }

    public void setFecTasaEmitida(Date fecTasaEmitida) {
        this.fecTasaEmitida = fecTasaEmitida;
    }

    public Integer getTamn() {
        return tamn;
    }

    public void setTamn(Integer tamn) {
        this.tamn = tamn;
    }

    public Integer getTipmn() {
        return tipmn;
    }

    public void setTipmn(Integer tipmn) {
        this.tipmn = tipmn;
    }

    public Double getTasaAnual() {
        return tasaAnual;
    }

    public void setTasaAnual(Double tasaAnual) {
        this.tasaAnual = tasaAnual;
    }

    public Double getTasaMensual() {
        return tasaMensual;
    }

    public void setTasaMensual(Double tasaMensual) {
        this.tasaMensual = tasaMensual;
    }

    public Double getTasaDiaria() {
        return tasaDiaria;
    }

    public void setTasaDiaria(Double tasaDiaria) {
        this.tasaDiaria = tasaDiaria;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTerminalCreacion() {
        return terminalCreacion;
    }

    public void setTerminalCreacion(String terminalCreacion) {
        this.terminalCreacion = terminalCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getTerminalActualizacion() {
        return terminalActualizacion;
    }

    public void setTerminalActualizacion(String terminalActualizacion) {
        this.terminalActualizacion = terminalActualizacion;
    }

    public String getFecTasaEmitidaString() {
        return fecTasaEmitidaString;
    }

    public void setFecTasaEmitidaString(String fecTasaEmitidaString) {
        this.fecTasaEmitidaString = fecTasaEmitidaString;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public AdmTasaInteresSbs(Date fecTasaEmitida, String fecTasaEmitidaString, Integer tamn, Integer tipmn, Double tasaAnual, Double tasaMensual, Double tasaDiaria, String estadoRegistro, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String usuarioActualizacion, String terminalActualizacion, Date fechaActualizacion) {
        this.fecTasaEmitida = fecTasaEmitida;
        this.fecTasaEmitidaString = fecTasaEmitidaString;
        this.tamn = tamn;
        this.tipmn = tipmn;
        this.tasaAnual = tasaAnual;
        this.tasaMensual = tasaMensual;
        this.tasaDiaria = tasaDiaria;
        this.estadoRegistro = estadoRegistro;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.terminalActualizacion = terminalActualizacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public AdmTasaInteresSbs() {
    }

    @Override
    public String toString() {
        return "AdmTasaInteresSbs{" +
                "fecTasaEmitida=" + fecTasaEmitida +
                ", fecTasaEmitidaString='" + fecTasaEmitidaString + '\'' +
                ", tamn=" + tamn +
                ", tipmn=" + tipmn +
                ", tasaAnual=" + tasaAnual +
                ", tasaMensual=" + tasaMensual +
                ", tasaDiaria=" + tasaDiaria +
                ", estadoRegistro='" + estadoRegistro + '\'' +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", terminalCreacion='" + terminalCreacion + '\'' +
                ", usuarioActualizacion='" + usuarioActualizacion + '\'' +
                ", terminalActualizacion='" + terminalActualizacion + '\'' +
                ", fechaActualizacion=" + fechaActualizacion +
                '}';
    }
}