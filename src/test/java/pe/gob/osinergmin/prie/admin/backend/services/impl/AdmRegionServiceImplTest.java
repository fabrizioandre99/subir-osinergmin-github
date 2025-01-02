package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmRegion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmRegionMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmRegionServiceImplTest {

    @InjectMocks
    private AdmRegionServiceImpl admRegionService;

    @Mock
    private AdmRegionMapper admRegionMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey_Exitoso() {
        when(admRegionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmRegion());
        when(admRegionMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO result = admRegionService.deleteByPrimaryKey("RG");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Región eliminada correctamente", result.getMessage());
        verify(admRegionMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admRegionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admRegionService.deleteByPrimaryKey("RG");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("La región no existe, no se puede eliminar", result.getMessage());
    }

    @Test
    void testInsert_YaExiste() {
        AdmRegionFormDTO formDTO = new AdmRegionFormDTO();
        formDTO.setCodRegion("RG");
        formDTO.setNomRegion("Nombre de prueba");

        when(admRegionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmRegion());

        MessageDTO result = admRegionService.insert(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Ya existe la región con el código proporcionado", result.getMessage());
    }


    @Test
    void testUpdateByPrimaryKey_NoExiste() {
        AdmRegionFormDTO formDTO = new AdmRegionFormDTO();
        formDTO.setCodRegion("RG");
        formDTO.setNomRegion("Nombre de prueba");

        when(admRegionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admRegionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("La región no existe, no se puede actualizar", result.getMessage());
    }


    @Test
    void testSelectByPrimaryKey() {
        String codRegion = "RG";
        AdmRegion region = new AdmRegion();
        when(admRegionMapper.selectByPrimaryKey(codRegion)).thenReturn(region);

        AdmRegion result = admRegionService.selectByPrimaryKey(codRegion);

        assertNotNull(result);
        verify(admRegionMapper, times(1)).selectByPrimaryKey(codRegion);
    }

    @Test
    void testSelectAll() {
        List<AdmRegionDTO> regionList = new ArrayList<>();
        when(admRegionMapper.selectAll()).thenReturn(regionList);

        List<AdmRegionDTO> result = admRegionService.selectAll();

        assertNotNull(result);
        assertEquals(regionList, result);
        verify(admRegionMapper, times(1)).selectAll();
    }

    @Test
    void testFiltrar() {
        AdmRegionSearchDTO searchDTO = new AdmRegionSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<AdmRegionDTO> regionList = new ArrayList<>();
        PageInfo<AdmRegionDTO> pageInfo = new PageInfo<>(regionList);
        when(admRegionMapper.filtrar(any(AdmRegionSearchDTO.class))).thenReturn(regionList);

        PageInfo<AdmRegionDTO> result = admRegionService.filtrar(searchDTO);

        assertNotNull(result);
        assertEquals(regionList, result.getList());
        verify(admRegionMapper, times(1)).filtrar(any(AdmRegionSearchDTO.class));
    }
}
