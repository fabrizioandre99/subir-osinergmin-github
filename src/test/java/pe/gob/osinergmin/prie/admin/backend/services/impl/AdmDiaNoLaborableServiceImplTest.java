package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.AdmDiaNoLaborableFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableMapperDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmDiaNoLaborableMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdmDiaNoLaborableServiceImplTest {

    @InjectMocks
    private AdmDiaNoLaborableServiceImpl admDiaNoLaborableService;

    @Mock
    private AdmDiaNoLaborableMapper admDiaNoLaborableMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        String fecha = "2024-09-12";
        DiaNoLaborableMapperDTO diaNoLaborable = new DiaNoLaborableMapperDTO();
        when(admDiaNoLaborableMapper.buscarFechaString(fecha)).thenReturn(diaNoLaborable);

        MessageDTO response = admDiaNoLaborableService.deleteByPrimaryKey(fecha);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admDiaNoLaborableMapper, times(1)).eliminarString(fecha);
    }

    @Test
    public void testInsert() {
        AdmDiaNoLaborableFormDTO formDTO = new AdmDiaNoLaborableFormDTO();
        formDTO.setFecha("2024-12-05");
        formDTO.setTipo("Nacional");
        formDTO.setMotivo("Festividad");

        when(admDiaNoLaborableMapper.buscarFechaString(formDTO.getFecha())).thenReturn(null);

        MessageDTO response = admDiaNoLaborableService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admDiaNoLaborableMapper, times(1)).insertarNuevo(any(DiaNoLaborableMapperDTO.class));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        AdmDiaNoLaborableFormDTO formDTO = new AdmDiaNoLaborableFormDTO();
        formDTO.setFecha("2024-12-05");
        formDTO.setTipo("Nacional");
        formDTO.setMotivo("Festividad");
        formDTO.setEstado("1");

        when(admDiaNoLaborableMapper.buscarFechaString(formDTO.getFecha())).thenReturn(new DiaNoLaborableMapperDTO());

        MessageDTO response = admDiaNoLaborableService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admDiaNoLaborableMapper, times(1)).actualizarNuevo(any(DiaNoLaborableMapperDTO.class));
    }

    @Test
    public void testEnlistarTodos() {
        List<DiaNoLaborableDTO> diaNoLaborables = new ArrayList<>();
        diaNoLaborables.add(new DiaNoLaborableDTO());

        when(admDiaNoLaborableMapper.enlistarTodos()).thenReturn(diaNoLaborables);

        List<DiaNoLaborableDTO> response = admDiaNoLaborableService.enlistarTodos();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    public void testFiltrar() {
        DiaNoLaborableSearchDTO searchDTO = new DiaNoLaborableSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);
        List<DiaNoLaborableDTO> diaNoLaborables = new ArrayList<>();

        when(admDiaNoLaborableMapper.filtrar(any(DiaNoLaborableSearchDTO.class))).thenReturn(diaNoLaborables);

        PageInfo<DiaNoLaborableDTO> response = admDiaNoLaborableService.filtrar(searchDTO);

        assertNotNull(response);
        assertEquals(0, response.getList().size());
    }
}
