package pe.gob.osinergmin.prie.admin.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervFormDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmFuncionProcSupervService;

import java.util.List;

@RestController
@RequestMapping("/funcionProcSuperv")
public class FuncionProcSupervController {

    @Autowired
    private AdmFuncionProcSupervService funcionProcSupervService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmFuncionProcSupervFormDTO record) {
        try {
            MessageDTO messageDTO = funcionProcSupervService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmFuncionProcSupervFormDTO record) {
        try {
            MessageDTO messageDTO = funcionProcSupervService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codProcSupervision,@RequestParam String codFuncionProcSuperv) {
        try {
            MessageDTO messageDTO = funcionProcSupervService.deleteByPrimaryKey(codProcSupervision, codFuncionProcSuperv);
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
    public ResponseEntity<List<AdmFuncionProcSupervDTO>> listar() {
        try {
            return ResponseEntity.succeed(funcionProcSupervService.listar());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
