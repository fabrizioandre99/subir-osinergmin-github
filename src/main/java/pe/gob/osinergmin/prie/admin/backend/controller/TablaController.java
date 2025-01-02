package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tabla.CfgTablaAdmDTO;
import pe.gob.osinergmin.prie.admin.backend.services.CfgTablaAdmService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maestros")
public class TablaController {

    private final CfgTablaAdmService cfgTablaAdmService;

    public TablaController(CfgTablaAdmService cfgTablaAdmService) {
        this.cfgTablaAdmService = cfgTablaAdmService;
    }


    @GetMapping("/{codTabla}/listar")
    public ResponseEntity<Map<String, Object>> listarDatos(@PathVariable String codTabla) {
        return ResponseEntity.succeed(cfgTablaAdmService.listarDatos(codTabla));
    }

    @PostMapping("/{codTabla}/insertar")
    public ResponseEntity<String> insertarDatos(@PathVariable String codTabla, @RequestBody Map<String, Object> parametros) {
        try {
            cfgTablaAdmService.insertarDatosDinamicos(codTabla, parametros);
            return ResponseEntity.succeed("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/{codTabla}/actualizar")
    public ResponseEntity<String> actualizarDatos(
            @PathVariable String codTabla,
            @RequestParam String primaryKey,
            @RequestParam String primaryKeyValue,
            @RequestBody Map<String, Object> parametros) {

        try {
            String[] primaryKeys = primaryKey.split(",");
            String[] primaryKeyValues = primaryKeyValue.split(",");

            if (primaryKeys.length != primaryKeyValues.length) {
                throw new RuntimeException("El número de claves y valores primarios debe ser el mismo");
            }

            Map<String, Object> primaryKeyMap = new HashMap<>();
            for (int i = 0; i < primaryKeys.length; i++) {
                primaryKeyMap.put(primaryKeys[i], primaryKeyValues[i]);
            }

            cfgTablaAdmService.actualizarDatosDinamicos(codTabla, parametros, primaryKeyMap);

            return ResponseEntity.succeed("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }


    @PostMapping("/{codTabla}/eliminar")
    public ResponseEntity<String> eliminarDatos(
            @PathVariable String codTabla,
            @RequestParam String primaryKey,
            @RequestParam String primaryKeyValue) {

        try {
            String[] primaryKeys = primaryKey.split(",");
            String[] primaryKeyValues = primaryKeyValue.split(",");

            if (primaryKeys.length != primaryKeyValues.length) {
                throw new RuntimeException("El número de claves y valores primarios debe ser el mismo");
            }

            Map<String, Object> primaryKeyMap = new HashMap<>();
            for (int i = 0; i < primaryKeys.length; i++) {
                primaryKeyMap.put(primaryKeys[i].toUpperCase().trim(), primaryKeyValues[i]);
            }

            cfgTablaAdmService.eliminarDatosDinamicos(codTabla, primaryKeyMap);
            return ResponseEntity.succeed("Datos eliminados correctamente de " + codTabla);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @GetMapping("/listarTablas")
    public ResponseEntity<List<CfgTablaAdmDTO>> listarPorTabla() {
        return ResponseEntity.succeed(cfgTablaAdmService.listarPorTabla());
    }


    @PostMapping("/{codTabla}/filtrar")
    public ResponseEntity<PageInfo<Map<String, Object>>> filtrarDatosDinamicos(
            @PathVariable String codTabla,
            @RequestBody Map<String, Object> filtros,
            PaginacionDTO paginacion) {
        try {
            PageInfo<Map<String, Object>> resultados = cfgTablaAdmService.listarConFiltro(codTabla, filtros, paginacion);
            return ResponseEntity.succeed(resultados);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @GetMapping("/columnas")
    public List<Map<String, Object>> obtenerCamposPorTabla(@RequestParam("codTabla") String codTabla) {
        return cfgTablaAdmService.obtenerCamposPorTabla(codTabla);
    }

}
