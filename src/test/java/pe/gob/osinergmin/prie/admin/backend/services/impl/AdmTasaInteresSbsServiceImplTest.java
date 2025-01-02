package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTasaInteresSbs;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaIntereDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresResult;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTasaInteresSbsMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmTasaInteresSbsServiceImplTest {

    @InjectMocks
    private AdmTasaInteresSbsServiceImpl admTasaInteresSbsService;

    @Mock
    private AdmTasaInteresSbsMapper admTasaInteresSbsMapper;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarConFiltroEstado() {
        TasaInteresSearchDTO searchDTO = new TasaInteresSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);
        List<TasaInteresResult> resultList = new ArrayList<>();

        when(admTasaInteresSbsMapper.listarConFiltroEstado(any(TasaInteresSearchDTO.class))).thenReturn(resultList);

        PageInfo<TasaInteresResult> response = admTasaInteresSbsService.listarConFiltroEstado(searchDTO);

        assertNotNull(response);
        assertEquals(0, response.getList().size());
    }

    @Test
    void testListarTasaInteres() {
        List<TasaIntereDTO> tasaIntereList = new ArrayList<>();
        tasaIntereList.add(new TasaIntereDTO());

        when(admTasaInteresSbsMapper.ListarTasaInteres()).thenReturn(tasaIntereList);

        List<TasaIntereDTO> response = admTasaInteresSbsService.listarTasaInteres();

        assertNotNull(response);
        assertEquals(1, response.size());
    }


    @Test
    void testDeleteByTasaDiaria() {
        TasaInteresFormDTO formDTO = new TasaInteresFormDTO();
        formDTO.setFecTasaEmitida(new Date());
        formDTO.setTasaDiaria(1.5);

        when(admTasaInteresSbsMapper.selectByTasaDiaria(anyDouble(), anyString())).thenReturn(new AdmTasaInteresSbs());

        MessageDTO response = admTasaInteresSbsService.deleteByTasaDiaria(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admTasaInteresSbsMapper, times(1)).deleteByPrimaryKey(anyDouble(), anyString());
    }
}
