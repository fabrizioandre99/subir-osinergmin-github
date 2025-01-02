package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgParametro;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.parametro.CfgParametroSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.CfgCampoAdmMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.CfgParametroMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.CfgTablaAdmMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TablaMaestraServiceImplTest {

    @InjectMocks
    private TablaMaestraServiceImpl tablaMaestraService;

    @Mock
    private CfgTablaAdmMapper cfgTablaAdmMapper;

    @Mock
    private CfgCampoAdmMapper cfgCampoAdmMapper;

    @Mock
    private CfgParametroMapper cfgParametroMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLstTablaMaestra() {
        List<TablaMaestraDTO> tablaList = new ArrayList<>();
        tablaList.add(new TablaMaestraDTO());

        when(cfgParametroMapper.getParametrosByGrupo("GRUPO1")).thenReturn(tablaList);

        List<TablaMaestraDTO> result = tablaMaestraService.getLstTablaMaestra("GRUPO1");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(cfgParametroMapper, times(1)).getParametrosByGrupo("GRUPO1");
    }

    @Test
    void testInsertarRegistro_Exitoso() {
        TablaMaestraFormdDTO formDTO = new TablaMaestraFormdDTO();
        formDTO.setCodParametro("P001");
        formDTO.setDescParametro("Descripción Prueba");

        when(cfgTablaAdmMapper.existeTabla(anyString())).thenReturn(1);

        MessageDTO response = tablaMaestraService.insertarRegistro(formDTO, "GRUPO1");

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Se agregó de forma correcta.", response.getMessage());
        verify(cfgParametroMapper, times(1)).insertParametro(any(CfgParametro.class));
    }

    @Test
    void testInsertarRegistro_TablaNoExiste() {
        TablaMaestraFormdDTO formDTO = new TablaMaestraFormdDTO();
        formDTO.setCodParametro("P001");
        formDTO.setDescParametro("Descripción Prueba");

        when(cfgTablaAdmMapper.existeTabla(anyString())).thenReturn(0);

        MessageDTO response = tablaMaestraService.insertarRegistro(formDTO, "GRUPO1");

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("La tabla seleccionada no existe.", response.getMessage());
    }

    @Test
    void testUpdateRegistro_Exitoso() {
        TablaMaestraFormdDTO formDTO = new TablaMaestraFormdDTO();
        formDTO.setCodParametro("P001");
        formDTO.setDescParametro("Descripción Actualizada");

        when(cfgTablaAdmMapper.existeTabla(anyString())).thenReturn(1);
        when(cfgParametroMapper.getParametro(anyString(), anyString())).thenReturn(new CfgParametro());

        MessageDTO response = tablaMaestraService.updateRegistro(formDTO, "GRUPO1");

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Se agregó de forma correcta.", response.getMessage());
        verify(cfgParametroMapper, times(1)).updateParametro(any(CfgParametro.class));
    }


    @Test
    void testDeleteRegistro_Exitoso() {
        when(cfgParametroMapper.getParametro(anyString(), anyString())).thenReturn(new CfgParametro());

        MessageDTO response = tablaMaestraService.deleteRegistro("GRUPO1", "P001");

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Se elimino correctamente el registro.", response.getMessage());
        verify(cfgParametroMapper, times(1)).deleteByPrimaryKey(anyString(), anyString());
    }

    @Test
    void testDeleteRegistro_ParametroNoExiste() {
        when(cfgParametroMapper.getParametro(anyString(), anyString())).thenReturn(null);

        MessageDTO response = tablaMaestraService.deleteRegistro("GRUPO1", "P001");

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El parametro no existe", response.getMessage());
    }

    @Test
    void testFiltrar() {
        CfgParametroSearchDTO searchDTO = new CfgParametroSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<TablaMaestraDTO> parametrosList = new ArrayList<>();
        PageInfo<TablaMaestraDTO> pageInfo = new PageInfo<>(parametrosList);

        when(cfgParametroMapper.filtrar(any(CfgParametroSearchDTO.class), anyString())).thenReturn(parametrosList);

        PageInfo<TablaMaestraDTO> result = tablaMaestraService.filtrar(searchDTO, "GRUPO1");

        assertNotNull(result);
        assertEquals(0, result.getList().size());
        verify(cfgParametroMapper, times(1)).filtrar(any(CfgParametroSearchDTO.class), anyString());
    }
}
