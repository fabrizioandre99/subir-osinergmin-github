package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.IndEconomico;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.IndEconomicoMapper;
import pe.gob.osinergmin.prie.admin.backend.services.IndEconomicoService;

import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndEconomicoServiceImplTest {

    @InjectMocks
    private IndEconomicoServiceImpl indEconomicoService;

    @Mock
    private IndEconomicoMapper indEconomicoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEliminarPorCodIndicador_Success() {
        // Configuración
        String codIndicador = "IND1234567";
        when(indEconomicoMapper.deleteByCodIndicador(codIndicador)).thenReturn(1);

        // Ejecución
        MessageDTO result = indEconomicoService.eliminarPorCodIndicador(codIndicador);

        // Verificación
        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Indicador eliminado exitosamente.", result.getMessage());
        verify(indEconomicoMapper, times(1)).deleteByCodIndicador(codIndicador);
    }

    @Test
    void testEliminarPorCodIndicador_Failure() {
        // Configuración
        String codIndicador = "IND1234567";
        when(indEconomicoMapper.deleteByCodIndicador(codIndicador)).thenReturn(0);

        // Ejecución
        MessageDTO result = indEconomicoService.eliminarPorCodIndicador(codIndicador);

        // Verificación
        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se pudo eliminar el indicador.", result.getMessage());
        verify(indEconomicoMapper, times(1)).deleteByCodIndicador(codIndicador);
    }

    @Test
    void testInsertarIndicador_Success() {
        // Configuración
        IndEconomicoFormDTO formDTO = new IndEconomicoFormDTO();
        formDTO.setAnnioMes("202301");
        formDTO.setCodIndicador("IND1234567");
        formDTO.setValIndicador(new BigDecimal("100.50"));
        formDTO.setEstado("1");

        when(indEconomicoMapper.selectByCodIndicador(formDTO.getCodIndicador())).thenReturn(null);
        when(indEconomicoMapper.insert(any(IndEconomico.class))).thenReturn(1);

        // Ejecución
        MessageDTO result = indEconomicoService.insertarIndicador(formDTO);

        // Verificación
        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Indicador creado exitosamente.", result.getMessage());
        verify(indEconomicoMapper, times(1)).selectByCodIndicador(formDTO.getCodIndicador());
        verify(indEconomicoMapper, times(1)).insert(any(IndEconomico.class));
    }

    @Test
    void testInsertarIndicador_CodIndicadorLengthError() {

        IndEconomicoFormDTO formDTO = new IndEconomicoFormDTO();
        formDTO.setAnnioMes("202301");
        formDTO.setCodIndicador("IND123456789");
        formDTO.setValIndicador(new BigDecimal("100.50"));
        formDTO.setEstado("1");

        MessageDTO result = indEconomicoService.insertarIndicador(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código del indicador debe tener entre 1 y 10 caracteres.", result.getMessage());

        verify(indEconomicoMapper, never()).selectByCodIndicador(anyString());
        verify(indEconomicoMapper, never()).insert(any(IndEconomico.class));
    }


    @Test
    void testInsertarIndicador_CodIndicadorExists() {
        // Configuración
        IndEconomicoFormDTO formDTO = new IndEconomicoFormDTO();
        formDTO.setAnnioMes("202301");
        formDTO.setCodIndicador("IND1234567");
        formDTO.setValIndicador(new BigDecimal("100.50"));
        formDTO.setEstado("1");

        when(indEconomicoMapper.selectByCodIndicador(formDTO.getCodIndicador())).thenReturn(new IndEconomico());

        // Ejecución
        MessageDTO result = indEconomicoService.insertarIndicador(formDTO);

        // Verificación
        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código del indicador ya existe.", result.getMessage());
        verify(indEconomicoMapper, times(1)).selectByCodIndicador(formDTO.getCodIndicador());
        verify(indEconomicoMapper, never()).insert(any(IndEconomico.class));
    }

    @Test
    void testInsertarIndicador_DataIntegrityViolationException() {
        // Configuración
        IndEconomicoFormDTO formDTO = new IndEconomicoFormDTO();
        formDTO.setAnnioMes("202301");
        formDTO.setCodIndicador("IND1234567");
        formDTO.setValIndicador(new BigDecimal("100.50"));
        formDTO.setEstado("1");

        when(indEconomicoMapper.selectByCodIndicador(formDTO.getCodIndicador())).thenReturn(null);
        when(indEconomicoMapper.insert(any(IndEconomico.class))).thenThrow(new DataIntegrityViolationException("Violación de integridad"));

        // Ejecución
        MessageDTO result = indEconomicoService.insertarIndicador(formDTO);

        // Verificación
        assertEquals(Constantes.ERROR, result.getStatus());
        assertTrue(result.getMessage().contains("Error de integridad de datos"));
        verify(indEconomicoMapper, times(1)).selectByCodIndicador(formDTO.getCodIndicador());
        verify(indEconomicoMapper, times(1)).insert(any(IndEconomico.class));
    }

    @Test
    void testActualizarIndicador_Success() {
        // Configuración
        IndEconomicoFormDTO formDTO = new IndEconomicoFormDTO();
        formDTO.setAnnioMes("202301");
        formDTO.setCodIndicador("IND1234567");
        formDTO.setValIndicador(new BigDecimal("150.75"));
        formDTO.setEstado("1");

        when(indEconomicoMapper.existeCod(formDTO.getCodIndicador())).thenReturn(1);
        when(indEconomicoMapper.updateByPrimaryKey(any(IndEconomico.class))).thenReturn(1);

        // Ejecución
        MessageDTO result = indEconomicoService.actualizarIndicador(formDTO);

        // Verificación
        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Indicador actualizado exitosamente.", result.getMessage());
        verify(indEconomicoMapper, times(1)).existeCod(formDTO.getCodIndicador());
        verify(indEconomicoMapper, times(1)).updateByPrimaryKey(any(IndEconomico.class));
    }

    @Test
    void testActualizarIndicador_CodIndicadorNotExists() {
        // Configuración
        IndEconomicoFormDTO formDTO = new IndEconomicoFormDTO();
        formDTO.setAnnioMes("202301");
        formDTO.setCodIndicador("IND1234567");
        formDTO.setValIndicador(new BigDecimal("150.75"));
        formDTO.setEstado("1");

        when(indEconomicoMapper.existeCod(formDTO.getCodIndicador())).thenReturn(0);

        // Ejecución
        MessageDTO result = indEconomicoService.actualizarIndicador(formDTO);

        // Verificación
        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código del indicador no existe. No se puede actualizar.", result.getMessage());
        verify(indEconomicoMapper, times(1)).existeCod(formDTO.getCodIndicador());
        verify(indEconomicoMapper, never()).updateByPrimaryKey(any(IndEconomico.class));
    }

    @Test
    void testActualizarIndicador_UpdateFailure() {
        // Configuración
        IndEconomicoFormDTO formDTO = new IndEconomicoFormDTO();
        formDTO.setAnnioMes("202301");
        formDTO.setCodIndicador("IND1234567");
        formDTO.setValIndicador(new BigDecimal("150.75"));
        formDTO.setEstado("1");

        when(indEconomicoMapper.existeCod(formDTO.getCodIndicador())).thenReturn(1);
        when(indEconomicoMapper.updateByPrimaryKey(any(IndEconomico.class))).thenReturn(0);

        // Ejecución
        MessageDTO result = indEconomicoService.actualizarIndicador(formDTO);

        // Verificación
        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se pudo actualizar el indicador. Verifique los datos proporcionados.", result.getMessage());
        verify(indEconomicoMapper, times(1)).existeCod(formDTO.getCodIndicador());
        verify(indEconomicoMapper, times(1)).updateByPrimaryKey(any(IndEconomico.class));
    }

    @Test
    void testActualizarIndicador_DataIntegrityViolationException() {
        // Configuración
        IndEconomicoFormDTO formDTO = new IndEconomicoFormDTO();
        formDTO.setAnnioMes("202301");
        formDTO.setCodIndicador("IND1234567");
        formDTO.setValIndicador(new BigDecimal("150.75"));
        formDTO.setEstado("1");

        when(indEconomicoMapper.existeCod(formDTO.getCodIndicador())).thenReturn(1);
        when(indEconomicoMapper.updateByPrimaryKey(any(IndEconomico.class))).thenThrow(new DataIntegrityViolationException("Violación de integridad"));

        // Ejecución
        MessageDTO result = indEconomicoService.actualizarIndicador(formDTO);

        // Verificación
        assertEquals(Constantes.ERROR, result.getStatus());
        assertTrue(result.getMessage().contains("Error de integridad de datos"));
        verify(indEconomicoMapper, times(1)).existeCod(formDTO.getCodIndicador());
        verify(indEconomicoMapper, times(1)).updateByPrimaryKey(any(IndEconomico.class));
    }

    @Test
    void testListarIndicadores() {
        // Configuración
        List<IndEconomicoDTO> indicadores = new ArrayList<>();
        indicadores.add(new IndEconomicoDTO("202301", "IND1234567", new BigDecimal("100.50"), "1"));
        indicadores.add(new IndEconomicoDTO("202302", "IND1234568", new BigDecimal("200.75"), "1"));

        when(indEconomicoMapper.listarIdEconomico()).thenReturn(indicadores);

        // Ejecución
        List<IndEconomicoDTO> result = indEconomicoService.listarIndicadores();

        // Verificación
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(indEconomicoMapper, times(1)).listarIdEconomico();
    }

    @Test
    void testListarConFiltro() {
        // Configuración
        IndEconomicoSearchDTO searchDTO = new IndEconomicoSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);
        searchDTO.setCodIndicador("IND123");

        List<IndEconomicoDTO> indicadores = new ArrayList<>();
        indicadores.add(new IndEconomicoDTO("202301", "IND1234567", new BigDecimal("100.50"), "1"));
        indicadores.add(new IndEconomicoDTO("202302", "IND1234568", new BigDecimal("200.75"), "1"));

        when(indEconomicoMapper.listarConFiltro(searchDTO)).thenReturn(indicadores);

        // Ejecución
        PageInfo<IndEconomicoDTO> result = indEconomicoService.listarConFiltro(searchDTO);

        // Verificación
        assertNotNull(result);
        assertEquals(2, result.getList().size());
        verify(indEconomicoMapper, times(1)).listarConFiltro(searchDTO);
    }
}
