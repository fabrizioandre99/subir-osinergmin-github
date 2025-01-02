package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUit;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmUitMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmUitServiceImplTest {

    @InjectMocks
    private AdmUitServiceImpl admUitService;

    @Mock
    private AdmUitMapper admUitMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey_Exitoso() {
        when(admUitMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmUit());
        when(admUitMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO response = admUitService.deleteByPrimaryKey("202310");

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("El periodo se eliminó correctamente.", response.getMessage());
        verify(admUitMapper, times(1)).deleteByPrimaryKey("202310");
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admUitMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admUitService.deleteByPrimaryKey("202310");

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El periodo no existe", response.getMessage());
    }

    @Test
    void testInsert_NuevoPeriodo() throws Exception {
        AdmUitFormDTO formDTO = new AdmUitFormDTO();
        formDTO.setAnioMes("202310");
        formDTO.setValor(4500);

        when(admUitMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admUitService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admUitMapper, times(1)).insert(any(AdmUit.class));
    }

    @Test
    void testInsert_PeriodoYaExiste() throws Exception {
        AdmUitFormDTO formDTO = new AdmUitFormDTO();
        formDTO.setAnioMes("202310");
        formDTO.setValor(4500);

        when(admUitMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmUit());

        MessageDTO response = admUitService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("Ya existe el periodo.", response.getMessage());
    }


    @Test
    void testUpdateByPrimaryKey_Exitoso() throws Exception {
        AdmUitFormDTO formDTO = new AdmUitFormDTO();
        formDTO.setAnioMes("202310");
        formDTO.setValor(4500);

        when(admUitMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmUit());

        MessageDTO response = admUitService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admUitMapper, times(1)).updateByPrimaryKey(any(AdmUit.class));
    }

    @Test
    void testUpdateByPrimaryKey_NoExiste() throws Exception {
        AdmUitFormDTO formDTO = new AdmUitFormDTO();
        formDTO.setAnioMes("202310");
        formDTO.setValor(4500);

        when(admUitMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admUitService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El periodo no existe.", response.getMessage());
    }


    @Test
    void testSelectAll() {
        List<AdmUitDTO> periods = new ArrayList<>();
        periods.add(new AdmUitDTO());

        when(admUitMapper.selectAll()).thenReturn(periods);

        List<AdmUitDTO> result = admUitService.selectAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(admUitMapper, times(1)).selectAll();
    }

    @Test
    void testListarFiltro() {
        AdmUitSearchDTO searchDTO = new AdmUitSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<AdmUitDTO> periods = new ArrayList<>();
        PageInfo<AdmUitDTO> pageInfo = new PageInfo<>(periods);

        when(admUitMapper.listarFiltro(any(AdmUitSearchDTO.class))).thenReturn(periods);

        PageInfo<AdmUitDTO> result = admUitService.listarFiltro(searchDTO);

        assertNotNull(result);
        assertEquals(0, result.getList().size());
        verify(admUitMapper, times(1)).listarFiltro(any(AdmUitSearchDTO.class));
    }
}
