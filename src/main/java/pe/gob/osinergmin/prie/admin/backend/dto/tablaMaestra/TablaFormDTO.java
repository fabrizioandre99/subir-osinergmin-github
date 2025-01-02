package pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra;

import lombok.Data;

@Data
public class TablaFormDTO {
    private String codTabla;
    private String descripcionTabla;
    private String estadoTabla;
    private String nombreCorto;
    private String flagAuditoria;
}
