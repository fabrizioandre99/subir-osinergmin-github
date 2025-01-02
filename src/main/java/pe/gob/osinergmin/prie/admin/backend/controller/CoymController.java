package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCoymService;

import java.util.List;

@RestController
@RequestMapping("/coym")
public class CoymController {

    @Autowired
    private AdmCoymService coymService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmCoymFormDTO record) {
        try {
            MessageDTO messageDTO = coymService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmCoymFormDTO record) {
        try {
            MessageDTO messageDTO = coymService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codCoym) {
        try {
            MessageDTO messageDTO = coymService.deleteByPrimaryKey(codCoym);
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
    public ResponseEntity<List<AdmCoymDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(coymService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmCoymDTO>> listarTarifasFiltro(@RequestBody AdmCoymSearchDTO admCoymSearchDTO) {
        try {
            return ResponseEntity.succeed(coymService.filtrarCoym(admCoymSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
