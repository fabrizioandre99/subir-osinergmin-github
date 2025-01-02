package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTarifaService;

import java.util.List;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {

    @Autowired
    private AdmTarifaService admTarifaService;

    @GetMapping("/detalleTarifa")
    public ResponseEntity<TarifaDTO> detalleTarifa(@RequestParam Integer idTarifa) {
        try{
            return ResponseEntity.succeed(admTarifaService.detalleTarifa(idTarifa));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearTarifa(@RequestBody TarifaFormDTO formDTO) {
        try {
            MessageDTO messageDTO = admTarifaService.insertarTarifa(formDTO);
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

    @PostMapping("/actualizar")
    public  ResponseEntity<String> actualizarTarifa(@RequestBody TarifaFormDTO formDTO) {
        try {
            MessageDTO messageDTO = admTarifaService.actualizarTarifa(formDTO);
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

    @PostMapping("/actualizar-selectivo")
    public MessageDTO actualizarTarifaSelective(@RequestBody TarifaDTO tarifaDTO) {
        try {
            return admTarifaService.actualizarTarifaSelective(tarifaDTO);
        } catch (Exception e) {
            return new MessageDTO("ERROR", "No se pudo actualizar la tarifa de manera selectiva: " + e.getMessage());
        }
    }

    @PostMapping("/eliminar")
    public  ResponseEntity<String> eliminarPorId(@RequestParam Integer idTarifa) {
        try {
            MessageDTO messageDTO = admTarifaService.eliminarPorId(idTarifa);
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

    @GetMapping("/listar")
    public ResponseEntity<List<TarifaDTO>> listarTarifas() {
        try {
            return ResponseEntity.succeed(admTarifaService.listarTarifas());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<TarifaDTO>> listarTarifasFiltro(@RequestBody TarifaSearchDTO searchDTO) {
        try {
            return ResponseEntity.succeed(admTarifaService.listarTarifasFiltro(searchDTO));
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/ListarTiposTarifa")
    public ResponseEntity<List<TarifaResultDTO>> listarTiposTarifaConcat() {
        return ResponseEntity.succeed(admTarifaService.listarTiposTarifaConcat());
    }
}
