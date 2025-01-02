package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmProductoService;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class AdmProductoController {

    @Autowired
    private AdmProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmProductoFormdDTO admProductoFormdDTO) {
        try {
            MessageDTO messageDTO = productoService.insert(admProductoFormdDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmProductoFormdDTO admProductoFormdDTO) {
        try {
            MessageDTO messageDTO = productoService.updateByPrimaryKey(admProductoFormdDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codProducto) {
        try {
            MessageDTO messageDTO = productoService.deleteByPrimaryKey(codProducto);
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
    public ResponseEntity<List<AdmProductoDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(productoService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<AdmProductoDTO>> listarFiltro(@RequestBody AdmProductoSearchDTO admProductoSearchDTO) {
        try {
            return ResponseEntity.succeed(productoService.filtrar(admProductoSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
