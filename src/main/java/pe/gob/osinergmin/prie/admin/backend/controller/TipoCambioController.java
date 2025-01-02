package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoCambioService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tipoCambio")
public class TipoCambioController {

    @Resource
    private AdmTipoCambioService admTipoCambioService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearTipoCambio(@RequestBody TipoCambioFormDTO tipoCambioFormDTO) {
        try{
            MessageDTO messageDTO = admTipoCambioService.insertarTipoCambio(tipoCambioFormDTO);
            if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
                throw new Exception(messageDTO.getMessage());
            }else {
                return ResponseEntity.succeed(messageDTO.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<String> actualizarTipoCambio(@RequestBody TipoCambioFormDTO tipoCambioFormDTO) {
        try {
            MessageDTO messageDTO = admTipoCambioService.actualizarTipoCambio(tipoCambioFormDTO);
           if(!messageDTO.getStatus().equals(Constantes.SUCCES)){
               throw new Exception(messageDTO.getMessage());
           }else {
               return ResponseEntity.succeed(messageDTO.getMessage());
           }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.failed(e.getMessage());
        }
    }

    @PostMapping("/eliminar")
    public ResponseEntity<String> eliminarPorMonedaFuenteFecha(@RequestParam String fecha) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = inputFormat.parse(fecha);
            String formattedDate = outputFormat.format(parsedDate);

            MessageDTO messageDTO = admTipoCambioService.eliminarPorMonedaFuenteFecha(formattedDate);
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
    public ResponseEntity<List<TipoCambioResultDTO>> listarTiposCambio() {
        try {
            return ResponseEntity.succeed(admTipoCambioService.listarTiposCambio());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/listarFiltro")
    public ResponseEntity<PageInfo<TipoCambioResultDTO>> listarConFiltro(@RequestBody TipoCambioSearchDTO tipoCambioSearchDTO) {
        try {
            return ResponseEntity.succeed(admTipoCambioService.listarConFiltro(tipoCambioSearchDTO));
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
