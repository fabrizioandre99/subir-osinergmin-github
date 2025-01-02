package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaIntereDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresResult;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTarifaService;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTasaInteresSbsService;


import java.util.List;

@RestController
@RequestMapping("/tasaInteresSbs")
public class TasaInteresSbsController {

    @Autowired
    private AdmTasaInteresSbsService admTasaInteresSbsService;

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody TasaInteresFormDTO fomrDTO) {
        try {
            MessageDTO messageDTO = admTasaInteresSbsService.insert(fomrDTO);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/actualizar")
    public  ResponseEntity<String> updateTasaDiaria(@RequestBody TasaInteresFormDTO fomrDTO) {
        try {
            MessageDTO messageDTO = admTasaInteresSbsService.updateTasaDiaria(fomrDTO);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/eliminar")
    public  ResponseEntity<String> deleteByTasaDiaria(@RequestBody TasaInteresFormDTO formDTO) {
        try {
            MessageDTO messageDTO = admTasaInteresSbsService.deleteByTasaDiaria(formDTO);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TasaIntereDTO>> listarTasaInteres() {
        try {
            return ResponseEntity.succeed(admTasaInteresSbsService.listarTasaInteres());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<TasaInteresResult>> listarConFiltroEstado(@RequestBody TasaInteresSearchDTO tasaInteresSearchDTO) {
        try {
            return ResponseEntity.succeed(admTasaInteresSbsService.listarConFiltroEstado(tasaInteresSearchDTO));
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
