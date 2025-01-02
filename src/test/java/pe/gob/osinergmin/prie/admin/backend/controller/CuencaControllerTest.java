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
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCuencaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CuencaControllerTest {

    @InjectMocks
    private CuencaController cuencaController;

    @Mock
    private AdmCuencaService admCuencaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmCuencaFormdDTO formDTO = new AdmCuencaFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Cuenca creada correctamente");

        when(admCuencaService.insert(any(AdmCuencaFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = cuencaController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Cuenca creada correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmCuencaFormdDTO formDTO = new AdmCuencaFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Cuenca actualizada correctamente");

        when(admCuencaService.updateByPrimaryKey(any(AdmCuencaFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = cuencaController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Cuenca actualizada correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codCuenca = "CUE001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Cuenca eliminada correctamente");

        when(admCuencaService.deleteByPrimaryKey(codCuenca)).thenReturn(messageDTO);

        ResponseEntity<String> response = cuencaController.eliminarPorId(codCuenca);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Cuenca eliminada correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmCuencaDTO> cuencaList = new ArrayList<>();
        cuencaList.add(new AdmCuencaDTO());

        when(admCuencaService.selectAll()).thenReturn(cuencaList);

        ResponseEntity<List<AdmCuencaDTO>> response = cuencaController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarFiltro() {
        AdmCuencaSearchDTO searchDTO = new AdmCuencaSearchDTO();
        PageInfo<AdmCuencaDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admCuencaService.filtro(any(AdmCuencaSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmCuencaDTO>> response = cuencaController.listarFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}