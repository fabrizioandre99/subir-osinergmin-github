package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorFormActualizarDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTransformadorService;

import java.util.List;

@RestController
@RequestMapping("/transformador")
public class TransformadorController {

    @Autowired
    private AdmTransformadorService transformadorService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmTransformadorFormDTO record) {
        try {
            MessageDTO messageDTO = transformadorService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmTransformadorFormActualizarDTO record) {
        try {
            MessageDTO messageDTO = transformadorService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam Integer idTransformador) {
        try {
            MessageDTO messageDTO = transformadorService.deleteByPrimaryKey(idTransformador);
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
    public ResponseEntity<List<AdmTransformadorDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(transformadorService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmTransformadorDTO>> listarTarifasFiltro(@RequestBody AdmTransformadorSearchDTO admTransformadorSearchDTO) {
        try {
            return ResponseEntity.succeed(transformadorService.filtrar(admTransformadorSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
