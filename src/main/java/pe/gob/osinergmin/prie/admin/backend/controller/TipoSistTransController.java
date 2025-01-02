package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoSistTransService;

import java.util.List;

@RestController
@RequestMapping("/tipoSisTrans")
public class TipoSistTransController {

    @Autowired
    private AdmTipoSistTransService admTipoSistTransService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmTipoSistTransFormDTO record) {
        try {
            MessageDTO messageDTO = admTipoSistTransService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmTipoSistTransFormDTO record) {
        try {
            MessageDTO messageDTO = admTipoSistTransService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam Integer idTipoSisTrans) {
        try {
            MessageDTO messageDTO = admTipoSistTransService.deleteByPrimaryKey(idTipoSisTrans);
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
    public ResponseEntity<List<AdmTipoSistTransDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admTipoSistTransService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmTipoSistTransDTO>> listarTarifasFiltro(@RequestBody AdmTipoSistTransSearchDTO admTipoSistTransSearchDTO) {
        try {
            return ResponseEntity.succeed(admTipoSistTransService.filtrar(admTipoSistTransSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
