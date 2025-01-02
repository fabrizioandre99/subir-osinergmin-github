package pe.gob.osinergmin.prie.admin.backend.dto.admUit;

import java.util.Date;

public class AdmUitFormDTO {
    private String anioMes;

    private Integer valor;

    private Date adFecha;

    public String getAnioMes() {
        return anioMes;
    }

    public void setAnioMes(String anioMes) {
        this.anioMes = anioMes;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Date getAdFecha() {
        return adFecha;
    }

    public void setAdFecha(Date adFecha) {
        this.adFecha = adFecha;
    }

    public AdmUitFormDTO(String anioMes, Integer valor, Date adFecha) {
        this.anioMes = anioMes;
        this.valor = valor;
        this.adFecha = adFecha;
    }
    public AdmUitFormDTO() {}
}
