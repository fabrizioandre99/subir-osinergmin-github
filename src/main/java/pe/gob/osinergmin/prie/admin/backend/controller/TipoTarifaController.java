package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoTarifaService;

import java.util.List;

@RestController
@RequestMapping("/tipoTarifa")
public class TipoTarifaController {

    @Resource
    private AdmTipoTarifaService admTipoTarifaService;

    @GetMapping("/detalleTipoTarifa")
    public ResponseEntity<TipoTarifaDTO> buscarPorCodTarifa(@RequestParam String codtarifa) {
       try {
           return ResponseEntity.succeed(admTipoTarifaService.buscarPorCodTarifa(codtarifa));
       }catch (Exception e) {
           e.printStackTrace();
           return ResponseEntity.failed(e.getMessage());
       }
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearTarifa(@RequestBody TipoTarifaFormDTO formDTO) {
        try {
            MessageDTO messageDTO = admTipoTarifaService.insertarTarifa(formDTO);
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

    @PostMapping("/actualizar")
    public ResponseEntity<String> actualizarTipoTarifa(@RequestBody TipoTarifaFormDTO formDTO) {
        try {
            MessageDTO messageDTO = admTipoTarifaService.actualizarTipoTarifa(formDTO);
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

    @PostMapping("/eliminar")
    public ResponseEntity<String> eliminarPorCodTarifa(@RequestBody TipoTarifaDTO tipoTarifaDTO) {
        try {
            MessageDTO messageDTO = admTipoTarifaService.eliminarPorCodTarifa(tipoTarifaDTO.getCodtarifa());
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else{
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TipoTarifaDTO>> listarTipoTarifas() {
        try {
            return ResponseEntity.succeed(admTipoTarifaService.listarTipoTarifas());
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<TipoTarifaDTO>> listarConFiltro(@RequestBody TipoTarifaSearchDTO tipoTarifaSearchDTO) {
        try {
            return ResponseEntity.succeed(admTipoTarifaService.listarConFiltro(tipoTarifaSearchDTO));
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
