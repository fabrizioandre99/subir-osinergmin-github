package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCiiu;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmCiiuMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AdmCiiuServiceImplTest {

    @InjectMocks
    private AdmCiiuServiceImpl admCiiuService;

    @Mock
    private AdmCiiuMapper admCiiuMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        String codCiiu = "C123";
        AdmCiiu ciiu = new AdmCiiu();
        when(admCiiuMapper.selectByPrimaryKey(codCiiu)).thenReturn(ciiu);

        MessageDTO response = admCiiuService.deleteByPrimaryKey(codCiiu);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admCiiuMapper, times(1)).deleteByPrimaryKey(codCiiu);
    }

    @Test
    public void testInsert() {
        AdmCiiuFormDTO formDTO = new AdmCiiuFormDTO();
        formDTO.setCodCiiu("C123");
        formDTO.setCodActividadEconomica("AE001");
        formDTO.setCiiu("Test CIIU");

        when(admCiiuMapper.selectByPrimaryKey(formDTO.getCodCiiu())).thenReturn(null);

        MessageDTO response = admCiiuService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admCiiuMapper, times(1)).insert(any(AdmCiiu.class));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        AdmCiiuFormDTO formDTO = new AdmCiiuFormDTO();
        formDTO.setCodCiiu("C123");
        formDTO.setCodActividadEconomica("AE002");
        formDTO.setCiiu("Updated CIIU");

        when(admCiiuMapper.selectByPrimaryKey(formDTO.getCodCiiu())).thenReturn(new AdmCiiu());

        MessageDTO response = admCiiuService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admCiiuMapper, times(1)).updateByPrimaryKey(any(AdmCiiu.class));
    }

    @Test
    public void testSelectAll() {
        List<AdmCiiuDTO> ciius = new ArrayList<>();
        ciius.add(new AdmCiiuDTO());

        when(admCiiuMapper.selectAll()).thenReturn(ciius);

        List<AdmCiiuDTO> response = admCiiuService.selectAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    public void testListarFiltro() {
        AdmCiiuSearchDTO searchDTO = new AdmCiiuSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);
        List<AdmCiiuDTO> ciius = new ArrayList<>();

        when(admCiiuMapper.listarFiltro(any(AdmCiiuSearchDTO.class))).thenReturn(ciius);

        PageInfo<AdmCiiuDTO> response = admCiiuService.listarFiltro(searchDTO);

        assertNotNull(response);
        assertEquals(0, response.getList().size());
    }
}
