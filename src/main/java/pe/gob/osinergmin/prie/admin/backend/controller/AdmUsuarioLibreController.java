package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreValidarRUC;
import pe.gob.osinergmin.prie.admin.backend.services.AdmUsuarioLibreService;

import java.util.List;

@RestController
@RequestMapping("/usuarioLibre")
public class AdmUsuarioLibreController {

    @Autowired
    private AdmUsuarioLibreService usuarioLibreService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmUsuarioLibreFormDTO admUsuarioLibreFormDTO) {
        try {
            MessageDTO messageDTO = usuarioLibreService.insert(admUsuarioLibreFormDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmUsuarioLibreFormDTO admUsuarioLibreFormDTO) {
        try {
            MessageDTO messageDTO = usuarioLibreService.updateByPrimaryKey(admUsuarioLibreFormDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codUsuarioLibre) {
        try {
            MessageDTO messageDTO = usuarioLibreService.deleteByPrimaryKey(codUsuarioLibre);
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
    public ResponseEntity<List<AdmUsuarioLibreDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(usuarioLibreService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmUsuarioLibreDTO>> listarFiltro(@RequestBody AdmUsuarioLibreSearchDTO admUsuarioLibreSearchDTO) {
        try {
            return ResponseEntity.succeed(usuarioLibreService.filtrar(admUsuarioLibreSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/validarSUNAT")
    public ResponseEntity<AdmUsuarioLibreValidarRUC> validarSUNAT(@RequestParam String codUsuarioLibre) {
        try {
            AdmUsuarioLibreValidarRUC data = usuarioLibreService.validarSUNAT(codUsuarioLibre);
            return ResponseEntity.succeed(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
