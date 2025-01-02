package pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre;

import lombok.Data;

@Data
public class AdmUsuarioLibreDTO {
    private String codUsuarioLibre;
    private String razonSocial;
    private String direccion;
    private String telefono;
    private String nombreRepLegal;
    private String cargoRepLeg;
    private String telefonoRepLeg;
    private String correoRepLeg;
    private String esGranCliente;
    private String codCoes;
    private String codUsuarioLibreAnt;
}
