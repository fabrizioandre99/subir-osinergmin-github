package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmProcSupervisionService;

import java.util.List;

@RestController
@RequestMapping("/procesoSupervisor")
public class ProcSupervisionController {

    @Autowired
    private AdmProcSupervisionService procSupervisionService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmProcSupervisionFormDTO record) {
        try {
            MessageDTO messageDTO = procSupervisionService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmProcSupervisionFormDTO record) {
        try {
            MessageDTO messageDTO = procSupervisionService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codProcSupervision) {
        try {
            MessageDTO messageDTO = procSupervisionService.deleteByPrimaryKey(codProcSupervision);
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
    public ResponseEntity<List<AdmProcSupervisionDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(procSupervisionService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmProcSupervisionDTO>> filtrar(@RequestBody AdmProcSupervisionSearchDTO admProcSupervisionSearchDTO) {
        try {
            return ResponseEntity.succeed(procSupervisionService.filtrar(admProcSupervisionSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
