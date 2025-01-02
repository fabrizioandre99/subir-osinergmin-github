package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSuministroUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/suministroUsuario")
public class AdmSuministroUsuarioController {

    @Autowired
    private AdmSuministroUsuarioService suministroUsuarioService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmSuministroUsuarioFormDTO admSuministroUsuarioFormDTO) {
        try {
            MessageDTO messageDTO = suministroUsuarioService.insert(admSuministroUsuarioFormDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmSuministroUsuarioFormDTO admSuministroUsuarioFormDTO) {
        try {
            MessageDTO messageDTO = suministroUsuarioService.updateByPrimaryKey(admSuministroUsuarioFormDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codSuministroUsuario) {
        try {
            MessageDTO messageDTO = suministroUsuarioService.deleteByPrimaryKey(codSuministroUsuario);
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
    public ResponseEntity<List<AdmSuministroUsuarioDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(suministroUsuarioService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmSuministroUsuarioDTO>> listarFiltro(@RequestBody AdmSuministroUsuarioSearchDTO admSuministroUsuarioSearchDTO) {
        try {
            return ResponseEntity.succeed(suministroUsuarioService.filtrar(admSuministroUsuarioSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
