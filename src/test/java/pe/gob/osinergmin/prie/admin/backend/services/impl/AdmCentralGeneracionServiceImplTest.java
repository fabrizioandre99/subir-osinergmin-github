package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCentralGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmCentralGeneracionMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmGrupoGeneracionMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmCentralGeneracionServiceImplTest {

    @InjectMocks
    private AdmCentralGeneracionServiceImpl admCentralGeneracionService;

    @Mock
    private AdmCentralGeneracionMapper admCentralGeneracionMapper;

    @Mock
    private AdmGrupoGeneracionMapper admGrupoGeneracionMapper;

    private AdmCentralGeneracionFormdDTO formDTO;

    @BeforeEach
    void setUp() {
        formDTO = new AdmCentralGeneracionFormdDTO();
        formDTO.setCodCentralGeneracion("CENT1");
        formDTO.setNomCentralGeneracion("Central Test");
        formDTO.setCodTipoCentral("TC");
        formDTO.setCodEmpresa("EMP1");
        formDTO.setCodUbigeo("UBIG01");
        formDTO.setEstado("1");
        formDTO.setTipo("T");
    }

    @Test
    void testInsert_Exitoso() {
        when(admCentralGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(admCentralGeneracionMapper.insert(any(AdmCentralGeneracion.class))).thenReturn(1);

        MessageDTO response = admCentralGeneracionService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Central de generación insertada correctamente.", response.getMessage());
    }

    @Test
    void testInsert_YaExiste() {
        when(admCentralGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmCentralGeneracion());

        MessageDTO response = admCentralGeneracionService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("Ya existe una central de generación con ese código.", response.getMessage());
    }

    @Test
    void testInsert_EstadoInvalido() {
        formDTO.setEstado("invalid");

        MessageDTO response = admCentralGeneracionService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El estado es obligatorio y debe ser un solo carácter.", response.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_Exitoso() {
        when(admCentralGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmCentralGeneracion());
        when(admCentralGeneracionMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO response = admCentralGeneracionService.deleteByPrimaryKey("CENT1");

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Central de generación eliminada correctamente.", response.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admCentralGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admCentralGeneracionService.deleteByPrimaryKey("CENT1");

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("No se encontró la central de generación para eliminar.", response.getMessage());
    }


    @Test
    void testUpdateByPrimaryKey_NoExiste() {
        when(admCentralGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admCentralGeneracionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("No se encontró la central de generación para actualizar.", response.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_EstadoInvalido() {
        formDTO.setEstado("invalid");

        MessageDTO response = admCentralGeneracionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El estado es obligatorio y debe ser un solo carácter.", response.getMessage());
    }
}
