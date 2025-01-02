package pe.gob.osinergmin.prie.admin.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgCampoAdm;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuDTO;
import pe.gob.osinergmin.prie.admin.backend.services.CfgCampoAdmService;

import java.util.List;

@RestController
@RequestMapping("/campos")
public class CamposController {


    @Autowired
    private CfgCampoAdmService cfgCampoAdmService;

    @GetMapping("/listarCampos")
    public ResponseEntity<List<CfgCampoAdm>> getCamposByTabla(@RequestParam String codTabla) {
        try {
            return ResponseEntity.succeed(cfgCampoAdmService.getCamposByTabla(codTabla));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
