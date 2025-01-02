package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseFomrDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmEmbalseService;

import java.util.List;

@RestController
@RequestMapping("/embalse")
public class EmbalseServiceController {

    @Autowired
    private AdmEmbalseService embalseService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmEmbalseFomrDTO admEmbalseFomrDTO) {
        try {
            MessageDTO messageDTO = embalseService.insert(admEmbalseFomrDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmEmbalseFomrDTO admEmbalseFomrDTO) {
        try {
            MessageDTO messageDTO = embalseService.updateByPrimaryKey(admEmbalseFomrDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codEmbalse) {
        try {
            MessageDTO messageDTO = embalseService.deleteByPrimaryKey(codEmbalse);
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
    public ResponseEntity<List<AdmEmbalseDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(embalseService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmEmbalseDTO>> listarFiltro(@RequestBody AdmEmbalseSearchDTO admEmbalseSearchDTO) {
        try {
            return ResponseEntity.succeed(embalseService.filtro(admEmbalseSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
