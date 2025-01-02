package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoCentralService;

import java.util.List;

@RestController
@RequestMapping("/tipoCentral")
public class TipoCentralController {

    @Autowired
    private AdmTipoCentralService admTipoCentralService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmTipoCentralFormdDTO record) {
        try {
            MessageDTO messageDTO = admTipoCentralService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmTipoCentralFormdDTO record) {
        try {
            MessageDTO messageDTO = admTipoCentralService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> deleteByPrimaryKey(@RequestParam String codTipoCentral) {
        try {
            MessageDTO messageDTO = admTipoCentralService.deleteByPrimaryKey(codTipoCentral);
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
    public ResponseEntity<List<AdmTipoCentralDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admTipoCentralService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmTipoCentralDTO>> listarTarifasFiltro(@RequestBody AdmTipoCentralSearchDTO admTipoCentralSearchDTO) {
        try {
            return ResponseEntity.succeed(admTipoCentralService.filtrar(admTipoCentralSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
