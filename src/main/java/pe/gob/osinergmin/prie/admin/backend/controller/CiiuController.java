package pe.gob.osinergmin.prie.admin.backend.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCiiuService;

import java.util.List;

@RestController
@RequestMapping("/ciuu")
public class CiiuController {

    @Autowired
    private AdmCiiuService admCiiuService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmCiiuFormDTO record) {
        try {
            MessageDTO messageDTO = admCiiuService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmCiiuFormDTO record) {
        try {
            MessageDTO messageDTO = admCiiuService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codCiiu) {
        try {
            MessageDTO messageDTO = admCiiuService.deleteByPrimaryKey(codCiiu);
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
    public ResponseEntity<List<AdmCiiuDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admCiiuService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmCiiuDTO>> listarFiltro(@RequestBody AdmCiiuSearchDTO admCiiuSearchDTO) {
        try {
            return ResponseEntity.succeed(admCiiuService.listarFiltro(admCiiuSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
