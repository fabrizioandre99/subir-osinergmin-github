package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSectorTipicoService;

import java.util.List;

@RestController
@RequestMapping("/sectorTipico")
public class AdmSectorTipicoController {

    @Autowired
    private AdmSectorTipicoService sectorTipicoService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmSectorTipicoFormDTO admSectorTipicoFormDTO) {
        try {
            MessageDTO messageDTO = sectorTipicoService.insert(admSectorTipicoFormDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmSectorTipicoFormDTO admSectorTipicoFormDTO) {
        try {
            MessageDTO messageDTO = sectorTipicoService.updateByPrimaryKey(admSectorTipicoFormDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codSectorTipico) {
        try {
            MessageDTO messageDTO = sectorTipicoService.deleteByPrimaryKey(codSectorTipico);
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
    public ResponseEntity<List<AdmSectorTipicoDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(sectorTipicoService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmSectorTipicoDTO>> listarFiltro(@RequestBody AdmSectorTipicoSearchDTO admSectorTipicoSearchDTO) {
        try {
            return ResponseEntity.succeed(sectorTipicoService.filtrar(admSectorTipicoSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
