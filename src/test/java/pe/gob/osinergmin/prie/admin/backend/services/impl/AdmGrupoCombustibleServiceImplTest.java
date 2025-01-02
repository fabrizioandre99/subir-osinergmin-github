package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoCombustible;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmGrupoCombustibleMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmGrupoCombustibleServiceImplTest {

    @Mock
    private AdmGrupoCombustibleMapper admGrupoCombustibleMapper;

    @InjectMocks
    private AdmGrupoCombustibleServiceImpl admGrupoCombustibleService;

    private AdmGrupoCombustibleFormdDTO grupoCombustibleFormDTO;
    private AdmGrupoCombustible grupoCombustible;

    @BeforeEach
    void setUp() {
        grupoCombustibleFormDTO = new AdmGrupoCombustibleFormdDTO();
        grupoCombustibleFormDTO.setCodGrupoCombustible("GC");
        grupoCombustibleFormDTO.setNomGrupoCombustible("Grupo Combustible Prueba");
        grupoCombustibleFormDTO.setEstado("A");

        grupoCombustible = new AdmGrupoCombustible();
        grupoCombustible.setCodGrupoCombustible("GC");
        grupoCombustible.setNomGrupoCombustible("Grupo Combustible Prueba");
        grupoCombustible.setEstado("A");
        grupoCombustible.setAdFecha(new Date());
        grupoCombustible.setAdCodUsuario("admin");
    }

    @Test
    void testInsert_Success() {
        when(admGrupoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admGrupoCombustibleService.insert(grupoCombustibleFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Grupo combustible insertado exitosamente", result.getMessage());
        verify(admGrupoCombustibleMapper, times(1)).insert(any(AdmGrupoCombustible.class));
    }

    @Test
    void testInsert_Failure_GrupoCombustibleExists() {
        when(admGrupoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(grupoCombustible);

        MessageDTO result = admGrupoCombustibleService.insert(grupoCombustibleFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Grupo combustible ya existe", result.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admGrupoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(grupoCombustible);

        MessageDTO result = admGrupoCombustibleService.deleteByPrimaryKey("GC");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Grupo combustible eliminado exitosamente", result.getMessage());
        verify(admGrupoCombustibleMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_GrupoCombustibleNotFound() {
        when(admGrupoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admGrupoCombustibleService.deleteByPrimaryKey("GC");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Grupo combustible no existe", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admGrupoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(grupoCombustible);

        MessageDTO result = admGrupoCombustibleService.updateByPrimaryKey(grupoCombustibleFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Grupo combustible actualizado exitosamente", result.getMessage());
        verify(admGrupoCombustibleMapper, times(1)).updateByPrimaryKey(any(AdmGrupoCombustible.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_GrupoCombustibleNotFound() {
        when(admGrupoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admGrupoCombustibleService.updateByPrimaryKey(grupoCombustibleFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Grupo combustible no existe", result.getMessage());
    }

    @Test
    void testFiltrar_Success() {
        List<AdmGrupoCombustibleDTO> grupoCombustibleList = new ArrayList<>();
        grupoCombustibleList.add(new AdmGrupoCombustibleDTO());

        when(admGrupoCombustibleMapper.filtrar(any(AdmGrupoCombustibleSearchDTO.class))).thenReturn(grupoCombustibleList);

        AdmGrupoCombustibleSearchDTO searchDTO = new AdmGrupoCombustibleSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmGrupoCombustibleDTO> result = admGrupoCombustibleService.filtrar(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
        verify(admGrupoCombustibleMapper, times(1)).filtrar(any(AdmGrupoCombustibleSearchDTO.class));
    }

    @Test
    void testSelectAll_Success() {
        List<AdmGrupoCombustibleDTO> grupoCombustibleList = new ArrayList<>();
        grupoCombustibleList.add(new AdmGrupoCombustibleDTO());

        when(admGrupoCombustibleMapper.selectAll()).thenReturn(grupoCombustibleList);

        List<AdmGrupoCombustibleDTO> result = admGrupoCombustibleService.selectAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(admGrupoCombustibleMapper, times(1)).selectAll();
    }
}
