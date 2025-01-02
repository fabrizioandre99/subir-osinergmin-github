package pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TasaIntereDTO {

    private Integer tamn;

    private Integer tipmn;

    private Integer tasaAnual;

    private Integer tasaMensual;

    private Double tasaDiaria;

    private String estadoRegistro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private Date fecTasaEmitida;

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

    public Integer getTasaAnual() {
        return tasaAnual;
    }

    public void setTasaAnual(Integer tasaAnual) {
        this.tasaAnual = tasaAnual;
    }

    public Integer getTasaMensual() {
        return tasaMensual;
    }

    public void setTasaMensual(Integer tasaMensual) {
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

    public Date getFecTasaEmitida() {
        return fecTasaEmitida;
    }

    public void setFecTasaEmitida(Date fecTasaEmitida) {
        this.fecTasaEmitida = fecTasaEmitida;
    }

    public TasaIntereDTO(Integer tamn, Integer tipmn, Integer tasaAnual, Integer tasaMensual, Double tasaDiaria, String estadoRegistro, Date fecTasaEmitida) {
        this.tamn = tamn;
        this.tipmn = tipmn;
        this.tasaAnual = tasaAnual;
        this.tasaMensual = tasaMensual;
        this.tasaDiaria = tasaDiaria;
        this.estadoRegistro = estadoRegistro;
        this.fecTasaEmitida = fecTasaEmitida;
    }

    public TasaIntereDTO() {}
}
