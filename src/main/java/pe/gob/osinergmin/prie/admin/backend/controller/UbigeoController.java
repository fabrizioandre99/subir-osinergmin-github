package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.AdmUbigeoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoProvDeptDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmUbigeoService;

import java.util.List;

@RestController
@RequestMapping("/ubigeo")
public class UbigeoController {

    @Autowired
    private AdmUbigeoService ubigeoService;

    @GetMapping("/listarDepartamento")
    public List<UbigeoDTO> listarDepartamento() {
        return ubigeoService.listarDepartamento();
    }

    @GetMapping("/listarProvincias")
    public ResponseEntity<List<UbigeoDTO>> listarProvincias(@RequestParam String codUbigeo) {
        List<UbigeoDTO> provincias = ubigeoService.listarProvincia(codUbigeo);
        return ResponseEntity.succeed(provincias);
    }

    @GetMapping("/listarDistritos")
    public ResponseEntity<List<UbigeoDTO>> listarDistrito(@RequestParam String codProvincia) {
        List<UbigeoDTO> distrito = ubigeoService.listarDistrito(codProvincia);
        return ResponseEntity.succeed(distrito);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> insert(@RequestBody AdmUbigeoFormDTO record) {
        try {
            MessageDTO messageDTO = ubigeoService.insert(record);
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
    public ResponseEntity<String> updateByPrimaryKey(@RequestBody AdmUbigeoFormDTO record) {
        try {
            MessageDTO messageDTO = ubigeoService.updateByPrimaryKey(record);
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
    public ResponseEntity<String> eliminarPorId(@RequestParam String codUbigeo) {
        try {
            MessageDTO messageDTO = ubigeoService.deleteByPrimaryKey(codUbigeo);
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
    public ResponseEntity<List<UbigeoDTO>> selectAll() {
        try {
            return ResponseEntity.succeed(ubigeoService.listaAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<UbigeoDTO>> listarFiltro(@RequestBody UbigeoSearchDTO ubigeoSearchDTO) {
        try {
            return ResponseEntity.succeed(ubigeoService.listaFiltro(ubigeoSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarDepaProv")
    public ResponseEntity<UbigeoProvDeptDTO> obtenerProvDeptPorDistrito(@RequestParam String codUbigeo) {
        UbigeoProvDeptDTO result = ubigeoService.obtenerProvDeptPorDistrito(codUbigeo);
        if (result == null) {
            return ResponseEntity.failed("No se encontraron datos para el código de ubigeo proporcionado.");
        }
        return ResponseEntity.succeed(result, "Datos encontrados exitosamente.");
    }


}
