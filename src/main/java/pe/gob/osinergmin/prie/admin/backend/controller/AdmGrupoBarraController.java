package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoBarra;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraformDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.GrupoBarraResponseDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmGrupoBarraService;

import java.util.List;

@RestController
@RequestMapping("/grupoBarra")
public class AdmGrupoBarraController {

    @Autowired
    private AdmGrupoBarraService grupoBarraService;

    @PostMapping("/crear")
    public ResponseEntity<String> insertGrupoBarra(@RequestBody AdmGrupoBarraformDTO record) {
        try {
            MessageDTO messageDTO = grupoBarraService.insertGrupoBarra(record);
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
    public ResponseEntity<String> actualizarGrupoBarra(@RequestBody AdmGrupoBarraformDTO record) {
        try {
            MessageDTO messageDTO = grupoBarraService.actualizarGrupoBarra(record);
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
    public ResponseEntity<String> eliminarPorCod(@RequestParam String codBarra) {
        try {
            MessageDTO messageDTO = grupoBarraService.eliminarGrupoBarra(codBarra);
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
    public List<GrupoBarraResponseDTO> listarGruposDeBarras() {
        return grupoBarraService.listarGruposDeBarras();
    }

    @PostMapping("/filtrar")
    public PageInfo<GrupoBarraResponseDTO> filtrarGruposDeBarras(@RequestBody AdmGrupoBarraSearchDTO searchDTO) {
        return grupoBarraService.filtrar(searchDTO);
    }

}
