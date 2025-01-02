package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoActualizarDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoCrearDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaElectricoDet.SistemaElectricoDetResultDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSistemaElectricoService;

import java.util.List;

@RestController
@RequestMapping("/sistemaElectrico")
public class SistemaElectricoController {

    @Autowired
    private AdmSistemaElectricoService sistemaElectricoService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmSistemaElectricoCrearDTO admSistemaElectricoCrearDTO) {
        try {
            MessageDTO messageDTO = sistemaElectricoService.insert(admSistemaElectricoCrearDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmSistemaElectricoActualizarDTO admSistemaElectricoActualizarDTO) {
        try {
            MessageDTO messageDTO = sistemaElectricoService.updateByPrimaryKey(admSistemaElectricoActualizarDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codSisElec) {
        try {
            MessageDTO messageDTO = sistemaElectricoService.deleteByPrimaryKey(codSisElec);
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
    public ResponseEntity<List<AdmSistemaElectricoDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(sistemaElectricoService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmSistemaElectricoDTO>> listarFiltro(@RequestBody AdmSistemaElectricoSearchDTO admSistemaElectricoSearchDTO) {
        try {
            return ResponseEntity.succeed(sistemaElectricoService.filtrar(admSistemaElectricoSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
