package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSistemaTransmisionService;

import java.util.List;

@RestController
@RequestMapping("/sistemaTransmision")
public class SistemaTransmisionController {

    @Autowired
    private AdmSistemaTransmisionService admSistemaTransmisionService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmSistemaTransmisionFormdDTO record) {
        try {
            MessageDTO messageDTO = admSistemaTransmisionService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmSistemaTransmisionFormdDTO record) {
        try {
            MessageDTO messageDTO = admSistemaTransmisionService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam Integer idSisTrans) {
        try {
            MessageDTO messageDTO = admSistemaTransmisionService.deleteByPrimaryKey(idSisTrans);
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
    public ResponseEntity<List<AdmSistemaTransmisionDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admSistemaTransmisionService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmSistemaTransmisionDTO>> listarTarifasFiltro(@RequestBody AdmSistemaTransmisionSearchDTO admSistemaTransmisionSearchDTO) {
        try {
            return ResponseEntity.succeed(admSistemaTransmisionService.filrar(admSistemaTransmisionSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
