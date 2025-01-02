package pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TipoCambioResultDTO {
    private String moneda;
    private String fuente;
    private BigDecimal valCompra;
    private BigDecimal valVenta;

    private String fecha;
}
