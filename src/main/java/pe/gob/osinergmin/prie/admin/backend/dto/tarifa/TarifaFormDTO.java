package pe.gob.osinergmin.prie.admin.backend.dto.tarifa;

import lombok.Data;

@Data
public class TarifaFormDTO {

    private String  idTarifa;
    private String codigTarifa;
    private String nombretarifa;
    private Integer secuencia;
    private String tipoMedicion;
    private String estado;

}
