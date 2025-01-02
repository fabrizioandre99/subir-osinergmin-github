package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSunatService;

import java.util.List;

@RestController
@RequestMapping("/sunat")
public class AdmSunatController {

    @Autowired
    private AdmSunatService admSunatService;


    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmSunatFormDTO formDTO) {
        try {
            MessageDTO messageDTO = admSunatService.insert(formDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmSunatFormDTO formDTO) {
        try {
            MessageDTO messageDTO = admSunatService.updateByPrimaryKey(formDTO);
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
    public ResponseEntity<String> deleteByPrimaryKey(@RequestParam String codSunat) {
        try {
            MessageDTO messageDTO = admSunatService.deleteByPrimaryKey(codSunat);
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
    public ResponseEntity<List<AdmSunatDTO>> listar() {
        try {
            List<AdmSunatDTO> list = admSunatService.listar();
            return ResponseEntity.succeed(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmSunatDTO>> listarFiltro(@RequestBody AdmSunatSearchDTO searchDTO) {
        try {
            PageInfo<AdmSunatDTO> result = admSunatService.listarFiltro(searchDTO);
            return ResponseEntity.succeed(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
