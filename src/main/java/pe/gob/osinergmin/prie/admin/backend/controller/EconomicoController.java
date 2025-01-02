package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.IndEconomicoService;

import java.util.List;

@RestController
@RequestMapping("/indEconomico")
public class EconomicoController {

    @Resource
    private IndEconomicoService indEconomicoService;

    @PostMapping("/crear")
    public MessageDTO crearIndicador(@RequestBody IndEconomicoFormDTO indEconomicoDTO) {
        try {
            return indEconomicoService.insertarIndicador(indEconomicoDTO);
        }catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "No se pudo crear el indicador: " + e.getMessage());
        }
    }

    @PostMapping("/actualizar")
    public MessageDTO actualizarIndicador(@RequestBody IndEconomicoFormDTO indEconomicoDTO) {
        try {
            return indEconomicoService.actualizarIndicador(indEconomicoDTO);
        }catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "No se pudo actualizar el indicador: " + e.getMessage());
        }
    }

    @PostMapping("/eliminar")
    public MessageDTO eliminarPorCodIndicador(@RequestParam String codIndicador) {
        try {
            return indEconomicoService.eliminarPorCodIndicador(codIndicador);
        }catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "No se pudo eliminar el indicador: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<IndEconomicoDTO> listarIndicadores() {
        return indEconomicoService.listarIndicadores();
    }

    @PostMapping("/listarFiltro")
    public PageInfo<IndEconomicoDTO> listarConFiltro(@RequestBody IndEconomicoSearchDTO indEconomicoSearchDTO) {
        return indEconomicoService.listarConFiltro(indEconomicoSearchDTO);
    }
}
