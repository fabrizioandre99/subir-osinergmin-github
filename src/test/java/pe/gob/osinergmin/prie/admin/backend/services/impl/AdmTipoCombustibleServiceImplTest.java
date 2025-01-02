package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoCombustible;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoCombustibleMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmTipoCombustibleServiceImplTest {

    @Mock
    private AdmTipoCombustibleMapper admTipoCombustibleMapper;

    @InjectMocks
    private AdmTipoCombustibleServiceImpl admTipoCombustibleService;

    private AdmTipoCombustibleFormDTO combustibleFormDTO;
    private AdmTipoCombustible admTipoCombustible;

    @BeforeEach
    void setUp() {
        combustibleFormDTO = new AdmTipoCombustibleFormDTO();
        combustibleFormDTO.setCodTipoCombustible("001");
        combustibleFormDTO.setNomTipoCombustible("Gasolina");
        combustibleFormDTO.setCodGrupoCombustible("01");
        combustibleFormDTO.setEstado("A");

        admTipoCombustible = new AdmTipoCombustible();
        admTipoCombustible.setCodTipoCombustible("001");
        admTipoCombustible.setNomTipoCombustible("Gasolina");
        admTipoCombustible.setCodGrupoCombustible("01");
        admTipoCombustible.setEstado("A");
    }

    @Test
    void testInsert_Success() {
        when(admTipoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(admTipoCombustibleMapper.insert(any(AdmTipoCombustible.class))).thenReturn(1);

        MessageDTO result = admTipoCombustibleService.insert(combustibleFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("El tipo de combustible se insertó correctamente.", result.getMessage());
        verify(admTipoCombustibleMapper, times(1)).insert(any(AdmTipoCombustible.class));
    }

    @Test
    void testInsert_Failure_CombustibleAlreadyExists() {
        when(admTipoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(admTipoCombustible);

        MessageDTO result = admTipoCombustibleService.insert(combustibleFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El tipo de combustible ya existe.", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admTipoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(admTipoCombustible);
        when(admTipoCombustibleMapper.updateByPrimaryKey(any(AdmTipoCombustible.class))).thenReturn(1);

        MessageDTO result = admTipoCombustibleService.updateByPrimaryKey(combustibleFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("El tipo de combustible se actualizó correctamente.", result.getMessage());
        verify(admTipoCombustibleMapper, times(1)).updateByPrimaryKey(any(AdmTipoCombustible.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_CombustibleDoesNotExist() {
        when(admTipoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admTipoCombustibleService.updateByPrimaryKey(combustibleFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El tipo de combustible no existe.", result.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admTipoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(admTipoCombustible);
        when(admTipoCombustibleMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO result = admTipoCombustibleService.deleteByPrimaryKey("001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("El tipo de combustible se eliminó correctamente.", result.getMessage());
        verify(admTipoCombustibleMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_CombustibleDoesNotExist() {
        when(admTipoCombustibleMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admTipoCombustibleService.deleteByPrimaryKey("001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No existe el tipo de combustible.", result.getMessage());
    }

    @Test
    void testFiltrarTipoCombustible() {
        List<AdmTipoCombustibleDTO> list = new ArrayList<>();
        list.add(new AdmTipoCombustibleDTO());

        when(admTipoCombustibleMapper.filtrarTipoCombustible(any(AdmTipoCombustibleSearchDTO.class))).thenReturn(list);

        AdmTipoCombustibleSearchDTO searchDTO = new AdmTipoCombustibleSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmTipoCombustibleDTO> result = admTipoCombustibleService.filtrarTipoCombustible(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
    }
}
