package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.AdmPliegoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.AdmPliegoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.PliegoActualizarDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.PliegoCrearDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmPliegoService;

import java.util.List;

@RestController
@RequestMapping("/pliego")
public class AdmPliegoController {

    @Autowired
    private AdmPliegoService pliegoService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody PliegoCrearDTO pliegoCrearDTO) {
        try {
            MessageDTO messageDTO = pliegoService.insert(pliegoCrearDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody PliegoActualizarDTO pliegoActualizarDTO) {
        try {
            MessageDTO messageDTO = pliegoService.updateByPrimaryKey(pliegoActualizarDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codPliego) {
        try {
            MessageDTO messageDTO = pliegoService.deleteByPrimaryKey(codPliego);
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
    public ResponseEntity<List<AdmPliegoDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(pliegoService.selectAllAdmPliegos());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmPliegoDTO>> listarFiltro(@RequestBody AdmPliegoSearchDTO admPliegoSearchDTO) {
        try {
            return ResponseEntity.succeed(pliegoService.filtro(admPliegoSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
