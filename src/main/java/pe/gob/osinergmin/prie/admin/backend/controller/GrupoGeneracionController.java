package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmGrupoGeneracionService;

import java.util.List;

@RestController
@RequestMapping("/grupoGeneración")
public class GrupoGeneracionController {

    @Autowired
    private AdmGrupoGeneracionService grupoGeneracionService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmGrupoGeneracionFormDTO admGrupoGeneracionFormDTO) {
        try {
            MessageDTO messageDTO = grupoGeneracionService.insert(admGrupoGeneracionFormDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmGrupoGeneracionFormDTO admGrupoGeneracionFormDTO) {
        try {
            MessageDTO messageDTO = grupoGeneracionService.updateByPrimaryKey(admGrupoGeneracionFormDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codGrupoGeneracion) {
        try {
            MessageDTO messageDTO = grupoGeneracionService.deleteByPrimaryKey(codGrupoGeneracion);
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
    public ResponseEntity<List<AdmGrupoGeneracionDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(grupoGeneracionService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmGrupoGeneracionDTO>> listarFiltro(@RequestBody AdmGrupoGeneracionSearchDTO admGrupoGeneracionSearchDTO) {
        try {
            return ResponseEntity.succeed(grupoGeneracionService.filtro(admGrupoGeneracionSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
