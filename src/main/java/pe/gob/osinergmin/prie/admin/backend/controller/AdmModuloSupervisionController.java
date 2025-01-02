package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmModuloSupervisionService;

import java.util.List;

@RestController
@RequestMapping("/moduloSupervision")
public class AdmModuloSupervisionController {

    @Autowired
    private AdmModuloSupervisionService moduloSupervisionService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmModuloSupervisionFormDTO admModuloSupervisionFormDTO) {
        try {
            MessageDTO messageDTO = moduloSupervisionService.insert(admModuloSupervisionFormDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmModuloSupervisionFormDTO admModuloSupervisionFormDTO) {
        try {
            MessageDTO messageDTO = moduloSupervisionService.updateByPrimaryKey(admModuloSupervisionFormDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String coModulo) {
        try {
            MessageDTO messageDTO = moduloSupervisionService.deleteByPrimaryKey(coModulo);
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
    public ResponseEntity<List<AdmModuloSupervisionDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(moduloSupervisionService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmModuloSupervisionDTO>> listarFiltro(@RequestBody AdmModuloSupervisionSearchDTO searchDTO) {
        try {
            return ResponseEntity.succeed(moduloSupervisionService.filtrar(searchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
