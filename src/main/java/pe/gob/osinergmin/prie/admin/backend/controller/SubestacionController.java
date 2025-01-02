package pe.gob.osinergmin.prie.admin.backend.controller;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionActualizarFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionListadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSubestacionService;

import java.util.List;

@RestController
@RequestMapping("/subestacion")
public class SubestacionController {
    @Autowired
    private AdmSubestacionService admSubestacionService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmSubestacionFormDTO record) {
        try {
            MessageDTO messageDTO = admSubestacionService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmSubestacionActualizarFormDTO record) {
        try {
            MessageDTO messageDTO = admSubestacionService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codSubestacion) {
        try {
            MessageDTO messageDTO = admSubestacionService.deleteByPrimaryKey(codSubestacion);
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
    public ResponseEntity<List<AdmSubestacionListadoDTO>> listarSubestaciones() {
        try {
            List<AdmSubestacionListadoDTO> subestaciones = admSubestacionService.listarSubestaciones();
            return ResponseEntity.succeed(subestaciones);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed("Error al listar subestaciones: " + e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmSubestacionListadoDTO>> listarFiltro(@RequestBody AdmSubestacionSearchDTO searchDTO) {
        try {
            return ResponseEntity.succeed(admSubestacionService.listarFiltro(searchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
