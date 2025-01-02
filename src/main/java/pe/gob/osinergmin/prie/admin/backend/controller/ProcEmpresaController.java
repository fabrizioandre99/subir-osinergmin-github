package pe.gob.osinergmin.prie.admin.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.*;
import pe.gob.osinergmin.prie.admin.backend.services.AdmProcEmpresaService;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmProcEmpresaDTO;

import java.util.List;

@RestController
@RequestMapping("/procEmpresa")
public class ProcEmpresaController {

    @Autowired
    private AdmProcEmpresaService admProcEmpresaService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmProcEmpresaFormDTO record) {
        try {
            MessageDTO messageDTO = admProcEmpresaService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmProcEmpresaFormDTO record) {
        try {
            MessageDTO messageDTO = admProcEmpresaService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam Integer idProcEmpresa) {
        try {
            MessageDTO messageDTO = admProcEmpresaService.deleteByPrimaryKey(idProcEmpresa);
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
    public ResponseEntity<List<AdmProcEmpresaDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admProcEmpresaService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/listarProcesoEmpresa")
    public ResponseEntity<List<AdmProcEmpresaResultDTO>> listarCreacionEmpresa() {
        try {
            return ResponseEntity.succeed(admProcEmpresaService.listarCreacionEmpresa());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarPorCodEmpresa")
    public ResponseEntity<List<AdmProcEmpresaDTO>> listarPorCodEmpresa(@RequestParam String codEmpresa) {
        try {
            return ResponseEntity.succeed(admProcEmpresaService.listarPorCodEmpresa(codEmpresa));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/listarFuncion")
    public ResponseEntity<List<AdmProcEmpresaFuncionDTO>> listarFuncion(@RequestParam String codProcSupervision) {
        try {
            return ResponseEntity.succeed(admProcEmpresaService.listarFuncion(codProcSupervision));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @GetMapping("/listarParaActualizar")
    public List<AdmProcEmpresaDTO> listarPorCodEmpresaFunTipo(@RequestParam String codEmpresa) {
        return admProcEmpresaService.listarPorCodEmpresaFunTipo(codEmpresa);
    }
}
