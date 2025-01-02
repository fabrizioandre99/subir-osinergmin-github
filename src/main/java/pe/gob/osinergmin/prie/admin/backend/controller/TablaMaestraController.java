package pe.gob.osinergmin.prie.admin.backend.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.parametro.CfgParametroSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.services.TablaMaestraService;


import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tabla")
public class TablaMaestraController {

    @Autowired
    private TablaMaestraService tablaMaestraService;


    @PostMapping("/{codGrupo}/insertar")
    public ResponseEntity<String> insertarRegistro(@RequestBody TablaMaestraFormdDTO tablaMaestraFormdDTO, @PathVariable String codGrupo){
        try {
            MessageDTO messageDTO = tablaMaestraService.insertarRegistro(tablaMaestraFormdDTO,codGrupo);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/{codGrupo}/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody TablaMaestraFormdDTO tablaMaestraFormdDTO, @PathVariable String codGrupo){
        try {
            MessageDTO messageDTO = tablaMaestraService.updateRegistro(tablaMaestraFormdDTO,codGrupo);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
    @PostMapping("/{codGrupo}/eliminar")
    public  ResponseEntity<String> deleteRegistro(@PathVariable String codGrupo,@RequestParam String codParametro) {
        try {
            MessageDTO messageDTO = tablaMaestraService.deleteRegistro(codGrupo,codParametro);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @GetMapping("/{codGrupo}/Listar")
    public ResponseEntity<List<TablaMaestraDTO>> listarRegistros(@PathVariable String codGrupo ) {
        return ResponseEntity.succeed(tablaMaestraService.getLstTablaMaestra(codGrupo));
    }

    @PostMapping("/{codGrupo}/filtrar")
    public ResponseEntity<PageInfo<TablaMaestraDTO>> filtrar(@RequestBody CfgParametroSearchDTO cfgParametroSearchDTO, @PathVariable String codGrupo ) {
        return ResponseEntity.succeed(tablaMaestraService.filtrar(cfgParametroSearchDTO,codGrupo));
    }

    @PostMapping("/insertarTabla")
    public ResponseEntity<String> insetarTabla(@RequestBody TablaFormDTO tablaFormDTO){
        try {
            MessageDTO messageDTO = tablaMaestraService.insetarTabla(tablaFormDTO);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/actualizarTabla")
    public ResponseEntity<String> actualizarTabla(@RequestBody TablaFormDTO tablaFormDTO){
        try {
            MessageDTO messageDTO = tablaMaestraService.actualizarTabla(tablaFormDTO);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
    @PostMapping("/eliminarTabla")
    public  ResponseEntity<String> eliminarTabla(@RequestParam String codTabla) {
        try {
            MessageDTO messageDTO = tablaMaestraService.eliminarTabla(codTabla);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

}
