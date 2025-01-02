package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmLagoService;

import java.util.List;

@RestController
@RequestMapping("/lago")
public class LagoController {

    @Autowired
    private AdmLagoService admLagoService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmLagoFormDTO admLagoFormDTO) {
        try {
            MessageDTO messageDTO = admLagoService.insert(admLagoFormDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmLagoFormDTO admLagoFormDTO) {
        try {
            MessageDTO messageDTO = admLagoService.updateByPrimaryKey(admLagoFormDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codLago) {
        try {
            MessageDTO messageDTO = admLagoService.deleteByPrimaryKey(codLago);
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
    public ResponseEntity<List<AdmLagoDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admLagoService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmLagoDTO>> listarFiltro(@RequestBody AdmLagoSearchDTO admLagoSearchDTO) {
        try {
            return ResponseEntity.succeed(admLagoService.filtro(admLagoSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
