package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmUitService;

import java.util.List;

@RestController
@RequestMapping("/uit")
public class UitController {

    @Autowired
    private AdmUitService admUitService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmUitFormDTO formDTO) {
        try {
            MessageDTO messageDTO = admUitService.insert(formDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmUitFormDTO formDTO) {
        try {
            MessageDTO messageDTO = admUitService.updateByPrimaryKey(formDTO);
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
    public ResponseEntity<String> deleteByPrimaryKey(@RequestParam String anioMes) {
        try {
            MessageDTO messageDTO = admUitService.deleteByPrimaryKey(anioMes);
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
    public ResponseEntity<List<AdmUitDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admUitService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmUitDTO>> listarTarifasFiltro(@RequestBody AdmUitSearchDTO searchDTO) {
        try {
            return ResponseEntity.succeed(admUitService.listarFiltro(searchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
