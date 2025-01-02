package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCuencaService;

import java.util.List;

@RestController
@RequestMapping("/cuenca")
public class CuencaController {

    @Autowired
    private AdmCuencaService admCuencaService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmCuencaFormdDTO admCuencaFormdDTO) {
        try {
            MessageDTO messageDTO = admCuencaService.insert(admCuencaFormdDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmCuencaFormdDTO admCuencaFormdDTO) {
        try {
            MessageDTO messageDTO = admCuencaService.updateByPrimaryKey(admCuencaFormdDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codCuenca) {
        try {
            MessageDTO messageDTO = admCuencaService.deleteByPrimaryKey(codCuenca);
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
    public ResponseEntity<List<AdmCuencaDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admCuencaService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmCuencaDTO>> listarFiltro(@RequestBody AdmCuencaSearchDTO admCuencaSearchDTO) {
        try {
            return ResponseEntity.succeed(admCuencaService.filtro(admCuencaSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
