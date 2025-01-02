package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.*;
import pe.gob.osinergmin.prie.admin.backend.services.AdmBarraService;

import java.util.List;

@RestController
@RequestMapping("/barra")
public class BarraController {

    @Autowired
    private AdmBarraService admBarraService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmBarraFormDTO record) {
        try {
            MessageDTO messageDTO = admBarraService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmBarraActualizarDTO record) {
        try {
            MessageDTO messageDTO = admBarraService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codBarra) {
        try {
            MessageDTO messageDTO = admBarraService.deleteByPrimaryKey(codBarra);
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
    public ResponseEntity<List<AdmBarraDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admBarraService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmBarraDTO>> filtrar(@RequestBody AdmBarraSearchDTO admBarraSearchDTO) {
        try {
            return ResponseEntity.succeed(admBarraService.filtrar(admBarraSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/listarBRG")
    public ResponseEntity<List<AdmBarraResultDTO>> listarBRG() {
        try {
            List<AdmBarraResultDTO> brgList = admBarraService.listarBRG();
            return ResponseEntity.succeed(brgList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed("Error al listar BRG: " + e.getMessage());
        }
    }

    @GetMapping("/listarPorCodSubestacion")
    public ResponseEntity<List<AdmBarraDTO>> listarPorCodSubestacion(String subestacion) {
        try {
            return ResponseEntity.succeed(admBarraService.listarPorCodSubestacion(subestacion));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
