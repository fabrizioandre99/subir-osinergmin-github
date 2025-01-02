package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.Util.CodeEnum;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmRegionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RegionControllerTest {

    @InjectMocks
    private RegionController regionController;

    @Mock
    private AdmRegionService regionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmRegionFormDTO formDTO = new AdmRegionFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(regionService.insert(any(AdmRegionFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = regionController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmRegionFormDTO formDTO = new AdmRegionFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(regionService.updateByPrimaryKey(any(AdmRegionFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = regionController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codRegion = "RG001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(regionService.deleteByPrimaryKey(codRegion)).thenReturn(messageDTO);

        ResponseEntity<String> response = regionController.eliminarPorId(codRegion);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmRegionDTO> regionList = new ArrayList<>();
        regionList.add(new AdmRegionDTO());

        when(regionService.selectAll()).thenReturn(regionList);

        ResponseEntity<List<AdmRegionDTO>> response = regionController.selectAll();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarTarifasFiltro() {
        AdmRegionSearchDTO searchDTO = new AdmRegionSearchDTO();
        PageInfo<AdmRegionDTO> pageInfo = new PageInfo<>();
        pageInfo.setList(new ArrayList<>());

        when(regionService.filtrar(any(AdmRegionSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmRegionDTO>> response = regionController.listarTarifasFiltro(searchDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
