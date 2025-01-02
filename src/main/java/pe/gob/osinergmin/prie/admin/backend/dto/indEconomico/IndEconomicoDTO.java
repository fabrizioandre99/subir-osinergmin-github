package pe.gob.osinergmin.prie.admin.backend.dto.indEconomico;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IndEconomicoDTO {

    private String annioMes;

    private String codIndicador;

    private BigDecimal valIndicador;

    private String estado;

    public IndEconomicoDTO(String number, String ind1234567, BigDecimal bigDecimal, String number1) {
    }

    public IndEconomicoDTO() {

    }
}
