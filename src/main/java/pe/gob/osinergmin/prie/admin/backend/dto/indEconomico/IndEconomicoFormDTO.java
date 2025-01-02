package pe.gob.osinergmin.prie.admin.backend.dto.indEconomico;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IndEconomicoFormDTO {
    private String annioMes;

    private String codIndicador;

    private BigDecimal valIndicador;

    private String estado;

    public IndEconomicoFormDTO(String annioMes, String codIndicador, BigDecimal valIndicador, String estado) {
    }

    public IndEconomicoFormDTO() {

    }
}
