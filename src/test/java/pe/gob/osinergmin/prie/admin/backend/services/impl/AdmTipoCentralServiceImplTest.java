package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoCentral;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoCentralMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdmTipoCentralServiceImplTest {

    @InjectMocks
    private AdmTipoCentralServiceImpl admTipoCentralService;

    @Mock
    private AdmTipoCentralMapper admTipoCentralMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey_Existe() {
        when(admTipoCentralMapper.selectByPrimaryKey("TC")).thenReturn(new AdmTipoCentral());

        MessageDTO response = admTipoCentralService.deleteByPrimaryKey("TC");

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Tipo central eliminado exitosamente", response.getMessage());
        verify(admTipoCentralMapper, times(1)).deleteByPrimaryKey("TC");
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admTipoCentralMapper.selectByPrimaryKey("TC")).thenReturn(null);

        MessageDTO response = admTipoCentralService.deleteByPrimaryKey("TC");

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("Tipo central no existe", response.getMessage());
    }

    @Test
    void testInsert_NuevoTipoCentral() {
        AdmTipoCentralFormdDTO formDTO = new AdmTipoCentralFormdDTO();
        formDTO.setCodTipoCentral("TC");
        formDTO.setNomTipoCentral("Central Térmica");
        formDTO.setEstado("1");

        when(admTipoCentralMapper.selectByPrimaryKey("TC")).thenReturn(null);

        MessageDTO response = admTipoCentralService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Tipo central insertado exitosamente", response.getMessage());
        verify(admTipoCentralMapper, times(1)).insert(any(AdmTipoCentral.class));
    }

    @Test
    void testInsert_TipoCentralExistente() {
        AdmTipoCentralFormdDTO formDTO = new AdmTipoCentralFormdDTO();
        formDTO.setCodTipoCentral("TC");
        formDTO.setNomTipoCentral("Central Térmica");
        formDTO.setEstado("1");

        when(admTipoCentralMapper.selectByPrimaryKey("TC")).thenReturn(new AdmTipoCentral());

        MessageDTO response = admTipoCentralService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("Tipo central ya existe", response.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Existe() {
        AdmTipoCentralFormdDTO formDTO = new AdmTipoCentralFormdDTO();
        formDTO.setCodTipoCentral("TC");
        formDTO.setNomTipoCentral("Central Hidroeléctrica");
        formDTO.setEstado("1");

        when(admTipoCentralMapper.selectByPrimaryKey("TC")).thenReturn(new AdmTipoCentral());

        MessageDTO response = admTipoCentralService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Tipo central actualizado exitosamente", response.getMessage());
        verify(admTipoCentralMapper, times(1)).updateByPrimaryKey(any(AdmTipoCentral.class));
    }

    @Test
    void testUpdateByPrimaryKey_NoExiste() {
        AdmTipoCentralFormdDTO formDTO = new AdmTipoCentralFormdDTO();
        formDTO.setCodTipoCentral("TC");
        formDTO.setNomTipoCentral("Central Hidroeléctrica");
        formDTO.setEstado("1");

        when(admTipoCentralMapper.selectByPrimaryKey("TC")).thenReturn(null);

        MessageDTO response = admTipoCentralService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("Tipo central no existe", response.getMessage());
    }

    @Test
    void testSelectAll() {
        List<AdmTipoCentralDTO> tipoCentrales = new ArrayList<>();
        tipoCentrales.add(new AdmTipoCentralDTO());

        when(admTipoCentralMapper.selectAll()).thenReturn(tipoCentrales);

        List<AdmTipoCentralDTO> response = admTipoCentralService.selectAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(admTipoCentralMapper, times(1)).selectAll();
    }

    @Test
    void testFiltrar() {
        AdmTipoCentralSearchDTO searchDTO = new AdmTipoCentralSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<AdmTipoCentralDTO> tipoCentrales = new ArrayList<>();
        tipoCentrales.add(new AdmTipoCentralDTO());

        when(admTipoCentralMapper.filtrar(any(AdmTipoCentralSearchDTO.class))).thenReturn(tipoCentrales);

        PageInfo<AdmTipoCentralDTO> response = admTipoCentralService.filtrar(searchDTO);

        assertNotNull(response);
        assertEquals(1, response.getList().size());
        verify(admTipoCentralMapper, times(1)).filtrar(any(AdmTipoCentralSearchDTO.class));
    }
}
