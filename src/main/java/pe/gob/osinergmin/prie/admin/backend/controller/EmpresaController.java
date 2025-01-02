package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmEmpresaService;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    AdmEmpresaService admEmpresaService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmEmpresaFormDTO admEmpresaFormDTO) {
        try {
            MessageDTO messageDTO = admEmpresaService.insert(admEmpresaFormDTO);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmEmpresaFormDTO admEmpresaFormDTO) {
        try {
            MessageDTO messageDTO = admEmpresaService.updateByPrimaryKey(admEmpresaFormDTO);
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
    public ResponseEntity<String> deleteByPrimaryKey(@RequestParam String codEmpresa) {
        try {
            MessageDTO messageDTO = admEmpresaService.deleteByPrimaryKey(codEmpresa);
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
    public ResponseEntity<List<AdmEmpresaDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admEmpresaService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public PageInfo<AdmEmpresaDTO> listarFiltro(@RequestBody AdmEmpresaSearchDTO searchDTO) {
        try {
            return admEmpresaService.listarFiltro(searchDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/crearDos")
    public ResponseEntity<String> insertNuevo(@RequestBody AdmEmpresaFormDTO empresaFormDTO) {
        try {
            MessageDTO messageDTO = admEmpresaService.insertNuevo(empresaFormDTO);
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

    @PostMapping("/actualizarNuevo")
    public ResponseEntity<String> updateNuevo(@RequestBody AdmEmpresaFormDTO empresaFormDTO) {
        try {
            MessageDTO messageDTO = admEmpresaService.updateNuevo(empresaFormDTO);
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
}
