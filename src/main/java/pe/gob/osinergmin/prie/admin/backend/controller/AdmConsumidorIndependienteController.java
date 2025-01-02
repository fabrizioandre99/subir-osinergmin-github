package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmConsumidorIndependienteService;

import java.util.List;

@RestController
@RequestMapping("/consumidorIndependiente")
public class AdmConsumidorIndependienteController {

    @Autowired
    private AdmConsumidorIndependienteService consumidorIndependienteService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmConsumidorIndependienteFormdDTO admConsumidorIndependienteFormdDTO) {
        try {
            MessageDTO messageDTO = consumidorIndependienteService.insert(admConsumidorIndependienteFormdDTO);
            if (!messageDTO.getStatus().equals(Constantes.SUCCES)) {
                throw new Exception(messageDTO.getMessage());
            } else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmConsumidorIndependienteFormdDTO admConsumidorIndependienteFormdDTO) {
        try {
            MessageDTO messageDTO = consumidorIndependienteService.updateByPrimaryKey(admConsumidorIndependienteFormdDTO);
            if (!messageDTO.getStatus().equals(Constantes.SUCCES)) {
                throw new Exception(messageDTO.getMessage());
            } else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/eliminar")
    public ResponseEntity<String> eliminarPorId(@RequestParam String codConsumidorInde) {
        try {
            MessageDTO messageDTO = consumidorIndependienteService.deleteByPrimaryKey(codConsumidorInde);
            if (!messageDTO.getStatus().equals(Constantes.SUCCES)) {
                throw new Exception(messageDTO.getMessage());
            } else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AdmConsumidorIndependienteDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(consumidorIndependienteService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmConsumidorIndependienteDTO>> listarFiltro(@RequestBody AdmConsumidorIndependienteSearchDTO admConsumidorIndependienteSearchDTO) {
        try {
            return ResponseEntity.succeed(consumidorIndependienteService.filtrar(admConsumidorIndependienteSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
