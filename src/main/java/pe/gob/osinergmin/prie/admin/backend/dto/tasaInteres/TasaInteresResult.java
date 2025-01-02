package pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TasaInteresResult {
    private Integer tamn;
    private Integer tipmn;
    private Double tasaAnual;
    private Double tasaMensual;
    private Double tasaDiaria;
    private String estadoRegistro;
    private String fecTasaEmitida;

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

    public String getFecTasaEmitida() {
        return fecTasaEmitida;
    }

    public void setFecTasaEmitida(String fecTasaEmitida) {
        this.fecTasaEmitida = fecTasaEmitida;
    }

    public TasaInteresResult(Integer tamn, Integer tipmn, Double tasaAnual, Double tasaMensual, Double tasaDiaria, String estadoRegistro, String fecTasaEmitida) {
        this.tamn = tamn;
        this.tipmn = tipmn;
        this.tasaAnual = tasaAnual;
        this.tasaMensual = tasaMensual;
        this.tasaDiaria = tasaDiaria;
        this.estadoRegistro = estadoRegistro;
        this.fecTasaEmitida = fecTasaEmitida;
    }

    public TasaInteresResult() {
    }
}
