package pe.gob.osinergmin.prie.admin.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;

import pe.gob.osinergmin.prie.admin.backend.dto.actividadEconomica.AdmActividadEconomicaDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmActividadEconomicaService;

import java.util.List;


@RestController
@RequestMapping("/actividadEconomica")
public class AdmActividadEconomica {

    @Autowired
    private AdmActividadEconomicaService admActividadEconomicaService;

    @GetMapping("/listar")
    public ResponseEntity<List<AdmActividadEconomicaDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(admActividadEconomicaService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }
}
