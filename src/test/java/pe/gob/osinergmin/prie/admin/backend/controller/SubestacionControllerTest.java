package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionActualizarFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionListadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSubestacionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SubestacionControllerTest {

    @InjectMocks
    private SubestacionController subestacionController;

    @Mock
    private AdmSubestacionService admSubestacionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmSubestacionFormDTO formDTO = new AdmSubestacionFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Subestación creada correctamente");

        when(admSubestacionService.insert(any(AdmSubestacionFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = subestacionController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Subestación creada correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmSubestacionActualizarFormDTO formDTO = new AdmSubestacionActualizarFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Subestación actualizada correctamente");

        when(admSubestacionService.updateByPrimaryKey(any(AdmSubestacionActualizarFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = subestacionController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Subestación actualizada correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codSubestacion = "SUB001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Subestación eliminada correctamente");

        when(admSubestacionService.deleteByPrimaryKey(codSubestacion)).thenReturn(messageDTO);

        ResponseEntity<String> response = subestacionController.eliminarPorId(codSubestacion);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Subestación eliminada correctamente", response.getDatas());
    }

    @Test
    void testListarSubestaciones() {
        List<AdmSubestacionListadoDTO> subestaciones = new ArrayList<>();
        subestaciones.add(new AdmSubestacionListadoDTO());

        when(admSubestacionService.listarSubestaciones()).thenReturn(subestaciones);

        ResponseEntity<List<AdmSubestacionListadoDTO>> response = subestacionController.listarSubestaciones();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarFiltro() {
        AdmSubestacionSearchDTO searchDTO = new AdmSubestacionSearchDTO();
        PageInfo<AdmSubestacionListadoDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admSubestacionService.listarFiltro(any(AdmSubestacionSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmSubestacionListadoDTO>> response = subestacionController.listarFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
