package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmGrupoGeneracionMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmGrupoGeneracionServiceImplTest {

    @InjectMocks
    private AdmGrupoGeneracionServiceImpl admGrupoGeneracionService;

    @Mock
    private AdmGrupoGeneracionMapper admGrupoGeneracionMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert_Exitoso() {
        AdmGrupoGeneracionFormDTO formDTO = new AdmGrupoGeneracionFormDTO();
        formDTO.setCodGrupoGeneracion("GRP01");
        formDTO.setNomGrupoGeneracion("Grupo Test");
        formDTO.setCodTipoCombustible("COM");
        formDTO.setEstado("A");

        when(admGrupoGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admGrupoGeneracionService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Se insertó de forma correcta el grupo de generación.", response.getMessage());
        verify(admGrupoGeneracionMapper, times(1)).insert(any(AdmGrupoGeneracion.class));
    }

    @Test
    void testInsert_YaExiste() {
        AdmGrupoGeneracionFormDTO formDTO = new AdmGrupoGeneracionFormDTO();
        formDTO.setCodGrupoGeneracion("GRP01");
        formDTO.setNomGrupoGeneracion("Grupo Test");
        formDTO.setCodTipoCombustible("COM");
        formDTO.setEstado("A");

        when(admGrupoGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmGrupoGeneracion());

        MessageDTO response = admGrupoGeneracionService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("Ya existe un grupo de generación con ese código.", response.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_NoExiste() {
        AdmGrupoGeneracionFormDTO formDTO = new AdmGrupoGeneracionFormDTO();
        formDTO.setCodGrupoGeneracion("GRP01");
        formDTO.setNomGrupoGeneracion("Grupo Test Actualizado");
        formDTO.setCodTipoCombustible("COM");
        formDTO.setEstado("A");

        when(admGrupoGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admGrupoGeneracionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("No se encontró el grupo de generación para actualizar.", response.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Exitoso() {
        AdmGrupoGeneracionFormDTO formDTO = new AdmGrupoGeneracionFormDTO();
        formDTO.setCodGrupoGeneracion("GRP01");
        formDTO.setNomGrupoGeneracion("Grupo Actualizado");
        formDTO.setCodTipoCombustible("COM");
        formDTO.setEstado("A");

        when(admGrupoGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmGrupoGeneracion());

        MessageDTO response = admGrupoGeneracionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Se actualizó de forma correcta el grupo de generación.", response.getMessage());
        verify(admGrupoGeneracionMapper, times(1)).updateByPrimaryKey(any(AdmGrupoGeneracion.class));
    }

    @Test
    void testDeleteByPrimaryKey_Exitoso() {
        when(admGrupoGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmGrupoGeneracion());

        MessageDTO response = admGrupoGeneracionService.deleteByPrimaryKey("GRP01");

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Se eliminó el grupo de generación con éxito.", response.getMessage());
        verify(admGrupoGeneracionMapper, times(1)).deleteByPrimaryKey("GRP01");
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admGrupoGeneracionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admGrupoGeneracionService.deleteByPrimaryKey("GRP01");

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("No se encontró el grupo de generación para eliminar.", response.getMessage());
    }
}
