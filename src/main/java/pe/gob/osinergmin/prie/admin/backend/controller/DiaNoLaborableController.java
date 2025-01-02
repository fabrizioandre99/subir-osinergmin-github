package pe.gob.osinergmin.prie.admin.backend.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.AdmDiaNoLaborableFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmDiaNoLaborableService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/diaNoLaborable")
public class DiaNoLaborableController {

    @Autowired
    private AdmDiaNoLaborableService admDiaNoLaborableService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmDiaNoLaborableFormDTO record) {
        try {
            MessageDTO messageDTO = admDiaNoLaborableService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmDiaNoLaborableFormDTO record) {
        try {
            MessageDTO messageDTO = admDiaNoLaborableService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String fecha) {
        try {
            MessageDTO messageDTO = admDiaNoLaborableService.deleteByPrimaryKey(fecha);
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
    public ResponseEntity<List<DiaNoLaborableDTO>> enlistarTodos() {
        try {
            return ResponseEntity.succeed(admDiaNoLaborableService.enlistarTodos());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<DiaNoLaborableDTO>> filtrar(@RequestBody DiaNoLaborableSearchDTO diaNoLaborableSearchDTO) {
        try {
            return ResponseEntity.succeed(admDiaNoLaborableService.filtrar(diaNoLaborableSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
