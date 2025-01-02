package pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class TipoCambioFormDTO {
    private String moneda;
    private String fuente;
    private String fecha;
    private String  valCompra;
    private String  valVenta;

}
