package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoCombustibleService;

import java.util.List;

@RestController
@RequestMapping("/tipoCombustible")
public class TipoCombustibleController {

    @Autowired
    private AdmTipoCombustibleService admTipoCombustibleService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmTipoCombustibleFormDTO admTipoCombustibleFormDTO) {
        try {
            MessageDTO messageDTO = admTipoCombustibleService.insert(admTipoCombustibleFormDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmTipoCombustibleFormDTO admTipoCombustibleFormDTO) {
        try {
            MessageDTO messageDTO = admTipoCombustibleService.updateByPrimaryKey(admTipoCombustibleFormDTO);
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
    public ResponseEntity<String> deleteByPrimaryKey(@RequestParam String codTipoCombustible) {
        try {
            MessageDTO messageDTO = admTipoCombustibleService.deleteByPrimaryKey(codTipoCombustible);
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
    public ResponseEntity<List<AdmTipoCombustibleDTO>> listartodo() {
        try {
            return ResponseEntity.succeed(admTipoCombustibleService.listartodo());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmTipoCombustibleDTO>> filtrarTipoCombustible(@RequestBody AdmTipoCombustibleSearchDTO admTipoCombustibleSearchDTO) {
        try {
            return ResponseEntity.succeed(admTipoCombustibleService.filtrarTipoCombustible(admTipoCombustibleSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
