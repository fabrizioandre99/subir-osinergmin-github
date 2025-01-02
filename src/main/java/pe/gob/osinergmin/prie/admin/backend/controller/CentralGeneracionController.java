package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCentralGeneracionService;

import java.util.List;

@RestController
@RequestMapping("/centralGeneracion")
public class CentralGeneracionController {

    @Autowired
    private AdmCentralGeneracionService admCentralGeneracionService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmCentralGeneracionFormdDTO admCentralGeneracionFormdDTO) {
        try {
            MessageDTO messageDTO = admCentralGeneracionService.insert(admCentralGeneracionFormdDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmCentralGeneracionFormdDTO admCentralGeneracionFormdDTO) {
        try {
            MessageDTO messageDTO = admCentralGeneracionService.updateByPrimaryKey(admCentralGeneracionFormdDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codCentralGeneracion) {
        try {
            MessageDTO messageDTO = admCentralGeneracionService.deleteByPrimaryKey(codCentralGeneracion);
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
    public ResponseEntity<List<AdmCentralGeneracionDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admCentralGeneracionService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmCentralGeneracionDTO>> listarFiltro(@RequestBody AdmCentralGeneracionSearchDTO admCentralGeneracionSearchDTO) {
        try {
            return ResponseEntity.succeed(admCentralGeneracionService.filtro(admCentralGeneracionSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
