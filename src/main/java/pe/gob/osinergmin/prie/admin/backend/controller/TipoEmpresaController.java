package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoEmpresaService;


import java.util.List;

@RestController
@RequestMapping("/tipoEmpresa")
public class TipoEmpresaController {

    @Autowired
    private AdmTipoEmpresaService admTipoEmpresaService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody TipoEmpresaFormDTO tipoEmpresaFormDTO) {
        try {
            MessageDTO messageDTO = admTipoEmpresaService.insert(tipoEmpresaFormDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody TipoEmpresaFormDTO tipoEmpresaFormDTO) {
        try {
            MessageDTO messageDTO = admTipoEmpresaService.updateByPrimaryKey(tipoEmpresaFormDTO);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codTipoEmpresa) {
        try {
            MessageDTO messageDTO = admTipoEmpresaService.deleteByPrimaryKey(codTipoEmpresa);
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
    public ResponseEntity<List<TipoEmpresaDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admTipoEmpresaService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<TipoEmpresaDTO>> listarTarifasFiltro(@RequestBody TipoEmpresaSearchDTO tipoEmpresaSearchDTO) {
        try {
            return ResponseEntity.succeed(admTipoEmpresaService.ListaFiltro(tipoEmpresaSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
