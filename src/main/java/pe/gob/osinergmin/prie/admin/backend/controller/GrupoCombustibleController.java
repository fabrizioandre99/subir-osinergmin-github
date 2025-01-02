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
import pe.gob.osinergmin.prie.admin.backend.services.AdmGrupoCombustibleService;

import java.util.List;

@RestController
@RequestMapping("grupoCombustible")
public class GrupoCombustibleController {

    @Autowired
    private AdmGrupoCombustibleService grupoCombustibleService;


    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmGrupoCombustibleFormdDTO record) {
        try {
            MessageDTO messageDTO = grupoCombustibleService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmGrupoCombustibleFormdDTO record) {
        try {
            MessageDTO messageDTO = grupoCombustibleService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> deleteByPrimaryKey(@RequestParam String codGrupoCombustible) {
        try {
            MessageDTO messageDTO = grupoCombustibleService.deleteByPrimaryKey(codGrupoCombustible);
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
    public ResponseEntity<List<AdmGrupoCombustibleDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(grupoCombustibleService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmGrupoCombustibleDTO>> listarTarifasFiltro(@RequestBody AdmGrupoCombustibleSearchDTO admGrupoCombustibleSearchDTO) {
        try {
            return ResponseEntity.succeed(grupoCombustibleService.filtrar(admGrupoCombustibleSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
