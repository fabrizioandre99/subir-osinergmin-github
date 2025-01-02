package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmModoOperacionService;

import java.util.List;

@RestController
@RequestMapping("/modoOperacion")
public class ModoOperacionController {

    @Autowired
    private AdmModoOperacionService modoOperacionService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmModoOperacionFormdDTO admModoOperacionFormdDTO) {
        try {
            MessageDTO messageDTO = modoOperacionService.insert(admModoOperacionFormdDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmModoOperacionFormdDTO admModoOperacionFormdDTO) {
        try {
            MessageDTO messageDTO = modoOperacionService.updateByPrimaryKey(admModoOperacionFormdDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codModoOperacion) {
        try {
            MessageDTO messageDTO = modoOperacionService.deleteByPrimaryKey(codModoOperacion);
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
    public ResponseEntity<List<AdmModoOperacionDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(modoOperacionService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmModoOperacionDTO>> listarFiltro(@RequestBody AdmModoOperacionSearchDTO admModoOperacionSearchDTO) {
        try {
            return ResponseEntity.succeed(modoOperacionService.filtro(admModoOperacionSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
