package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.GrupoBarraResponseDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmAreaDemandaService;

import java.util.List;

@RestController
@RequestMapping("/areaDemanda")
public class AreaDemandaController {

    @Autowired
    private AdmAreaDemandaService admAreaDemandaService;

    @GetMapping("/listar")
    public ResponseEntity<List<AdmAreaDemandaDTO>> listarAreas() {
        try {
            return ResponseEntity.succeed(admAreaDemandaService.listarAreas());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmAreaDemandaFormDTO record) {
        try {
            MessageDTO messageDTO = admAreaDemandaService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmAreaDemandaFormDTO record) {
        try {
            MessageDTO messageDTO = admAreaDemandaService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam Integer areaDemanda) {
        try {
            MessageDTO messageDTO = admAreaDemandaService.deleteByPrimaryKey(areaDemanda);
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

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmAreaDemandaDTO>> filtrar(@RequestBody AdmAreaDemandaSearchDTO admAreaDemandaSearchDTO) {
        try {
            return ResponseEntity.succeed(admAreaDemandaService.filtrar(admAreaDemandaSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
