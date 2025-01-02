package pe.gob.osinergmin.prie.admin.backend.dto.tarifa;

public class TarifaResultDTO {
    private String codigTarifa;
    private String descripcion;

    public String getCodigTarifa() {
        return codigTarifa;
    }

    public void setCodigTarifa(String codigTarifa) {
        this.codigTarifa = codigTarifa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TarifaResultDTO(String codigTarifa, String descripcion) {
        this.codigTarifa = codigTarifa;
        this.descripcion = descripcion;
    }
    public TarifaResultDTO() {}
}
