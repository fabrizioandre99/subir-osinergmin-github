package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTarifa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTarifaMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoTarifaMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdmTarifaServiceImplTest {

    @Mock
    private AdmTarifaMapper admTarifaMapper;

    @Mock
    private AdmTipoTarifaMapper admTipoTarifaMapper;

    @InjectMocks
    private AdmTarifaServiceImpl admTarifaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertarTarifa_Exitoso() throws Exception {
        TarifaFormDTO formDTO = new TarifaFormDTO();
        formDTO.setIdTarifa(String.valueOf(123));
        formDTO.setCodigTarifa("1234567890");
        formDTO.setNombretarifa("Tarifa de prueba");
        formDTO.setSecuencia(123);
        formDTO.setTipoMedicion("12");
        formDTO.setEstado("1");

        when(admTarifaMapper.selectByPrimaryKey(123)).thenReturn(null);
        when(admTipoTarifaMapper.existeCod("1234567890")).thenReturn(1);

        MessageDTO result = admTarifaService.insertarTarifa(formDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        verify(admTarifaMapper, times(1)).insertSelective(any(AdmTarifa.class));
    }

    @Test
    public void testInsertarTarifa_ErrorYaExiste() throws Exception {
        TarifaFormDTO formDTO = new TarifaFormDTO();
        formDTO.setIdTarifa(String.valueOf(123));
        formDTO.setCodigTarifa("1234567890");
        formDTO.setNombretarifa("Tarifa de prueba");
        formDTO.setSecuencia(123);
        formDTO.setTipoMedicion("12");
        formDTO.setEstado("1");

        AdmTarifa tarifaExistente = new AdmTarifa();
        when(admTarifaMapper.selectByPrimaryKey(123)).thenReturn(tarifaExistente);

        MessageDTO result = admTarifaService.insertarTarifa(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El ID de la tarifa ya existe. Por favor, elige otro ID.", result.getMessage());
    }

    @Test
    public void testInsertarTarifa_ErrorSecuenciaInvalida() throws Exception {
        TarifaFormDTO formDTO = new TarifaFormDTO();
        formDTO.setIdTarifa(String.valueOf(123));
        formDTO.setCodigTarifa("1234567890");
        formDTO.setNombretarifa("Tarifa de prueba");
        formDTO.setSecuencia(12345);  // Secuencia inválida
        formDTO.setTipoMedicion("12");
        formDTO.setEstado("1");

        when(admTarifaMapper.selectByPrimaryKey(123)).thenReturn(null);
        when(admTipoTarifaMapper.existeCod("1234567890")).thenReturn(1);

        MessageDTO result = admTarifaService.insertarTarifa(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El valor de la secuencia no puede tener más de 3 dígitos enteros.", result.getMessage());
        verify(admTarifaMapper, never()).insertSelective(any(AdmTarifa.class));
    }

    @Test
    public void testInsertarTarifa_ErrorTipoMedicionInvalido() throws Exception {
        TarifaFormDTO formDTO = new TarifaFormDTO();
        formDTO.setIdTarifa(String.valueOf(123));
        formDTO.setCodigTarifa("1234567890");
        formDTO.setNombretarifa("Tarifa de prueba");
        formDTO.setSecuencia(123);
        formDTO.setTipoMedicion("123");  // Tipo de medición inválido
        formDTO.setEstado("1");

        when(admTarifaMapper.selectByPrimaryKey(123)).thenReturn(null);
        when(admTipoTarifaMapper.existeCod("1234567890")).thenReturn(1);

        MessageDTO result = admTarifaService.insertarTarifa(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El tipo de medición debe tener exactamente 2 caracteres.", result.getMessage());
        verify(admTarifaMapper, never()).insertSelective(any(AdmTarifa.class));
    }

    @Test
    public void testInsertarTarifa_ErrorEstadoInvalido() throws Exception {
        TarifaFormDTO formDTO = new TarifaFormDTO();
        formDTO.setIdTarifa(String.valueOf(123));
        formDTO.setCodigTarifa("1234567890");
        formDTO.setNombretarifa("Tarifa de prueba");
        formDTO.setSecuencia(123);
        formDTO.setTipoMedicion("12");
        formDTO.setEstado("5");  // Estado inválido

        when(admTarifaMapper.selectByPrimaryKey(123)).thenReturn(null);
        when(admTipoTarifaMapper.existeCod("1234567890")).thenReturn(1);

        MessageDTO result = admTarifaService.insertarTarifa(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El estado debe ser 1 o 0.", result.getMessage());
        verify(admTarifaMapper, never()).insertSelective(any(AdmTarifa.class));
    }

    @Test
    public void testActualizarTarifa_Exitoso() throws Exception {
        TarifaFormDTO formDTO = new TarifaFormDTO();
        formDTO.setIdTarifa(String.valueOf(123));
        formDTO.setCodigTarifa("1234567890");
        formDTO.setNombretarifa("Tarifa de prueba actualizada");
        formDTO.setSecuencia(123);
        formDTO.setTipoMedicion("12");
        formDTO.setEstado("1");

        AdmTarifa tarifaExistente = new AdmTarifa();
        when(admTarifaMapper.selectByPrimaryKey(123)).thenReturn(tarifaExistente);
        when(admTipoTarifaMapper.existeCod("1234567890")).thenReturn(1);

        MessageDTO result = admTarifaService.actualizarTarifa(formDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        verify(admTarifaMapper, times(1)).updateByPrimaryKey(any(AdmTarifa.class));
    }


    @Test
    public void testActualizarTarifa_ErrorNoExiste() throws Exception {
        TarifaFormDTO formDTO = new TarifaFormDTO();
        formDTO.setIdTarifa(String.valueOf(123));
        formDTO.setCodigTarifa("1234567890");
        formDTO.setNombretarifa("Tarifa de prueba actualizada");
        formDTO.setSecuencia(123);
        formDTO.setTipoMedicion("12");
        formDTO.setEstado("1");

        when(admTarifaMapper.selectByPrimaryKey(123)).thenReturn(null);

        MessageDTO result = admTarifaService.actualizarTarifa(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El ID de la tarifa no existe. No se puede actualizar.", result.getMessage());
        verify(admTarifaMapper, never()).updateByPrimaryKey(any(AdmTarifa.class));
    }


    @Test
    public void testEliminarPorId_Exitoso() {
        AdmTarifa tarifa = new AdmTarifa();
        tarifa.setIdTarifa(5);

        when(admTarifaMapper.selectByPrimaryKey(5)).thenReturn(tarifa);

        MessageDTO result = admTarifaService.eliminarPorId(5);

        assertEquals(Constantes.SUCCES, result.getStatus());
        verify(admTarifaMapper, times(1)).deleteByPrimaryKey(5);
    }

    @Test
    public void testEliminarPorId_ErrorNoExiste() {
        when(admTarifaMapper.selectByPrimaryKey(5)).thenReturn(null);

        MessageDTO result = admTarifaService.eliminarPorId(5);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El ID de la tarifa no existe. No se puede eliminar.", result.getMessage());
        verify(admTarifaMapper, never()).deleteByPrimaryKey(5);
    }
}
