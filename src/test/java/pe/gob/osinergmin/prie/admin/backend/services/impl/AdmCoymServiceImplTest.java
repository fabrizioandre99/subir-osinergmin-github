package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCoym;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmCoymMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmCoymServiceImplTest {

    @InjectMocks
    private AdmCoymServiceImpl admCoymService;

    @Mock
    private AdmCoymMapper admCoymMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey_Exitoso() {
        when(admCoymMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmCoym());
        when(admCoymMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO result = admCoymService.deleteByPrimaryKey("COY001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("OK", result.getMessage());
        verify(admCoymMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admCoymMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admCoymService.deleteByPrimaryKey("COY001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se elimino el Ubigeo", result.getMessage());
    }

//    @Test
//    void testInsert_Exitoso() {
//        AdmCoymFormDTO formDTO = new AdmCoymFormDTO();
//        formDTO.setCodCoym("COY001");
//        formDTO.setNomCoym("Costo y Mantenimiento Test");
//
//        when(admCoymMapper.selectByPrimaryKey(anyString())).thenReturn(null);
//
//        MessageDTO result = admCoymService.insert(formDTO);
//
//        assertEquals(Constantes.SUCCES, result.getStatus());
//        assertEquals("Registro insertado correctamente", result.getMessage());
//        verify(admCoymMapper, times(1)).insert(any(AdmCoym.class));
//    }

    @Test
    void testInsert_YaExiste() {
        AdmCoymFormDTO formDTO = new AdmCoymFormDTO();
        formDTO.setCodCoym("COY001");
        formDTO.setNomCoym("Nombre de prueba");

        when(admCoymMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmCoym());

        MessageDTO result = admCoymService.insert(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Ya existe el código de Costo y Mantenimiento", result.getMessage());
    }


//    @Test
//    void testUpdateByPrimaryKey_Exitoso() {
//        AdmCoymFormDTO formDTO = new AdmCoymFormDTO();
//        formDTO.setCodCoym("COY001");
//        formDTO.setNomCoym("Actualizado");
//
//        AdmCoym existingCoym = new AdmCoym();
//        when(admCoymMapper.selectByPrimaryKey(anyString())).thenReturn(existingCoym);
//
//        MessageDTO result = admCoymService.updateByPrimaryKey(formDTO);
//
//        assertEquals(Constantes.SUCCES, result.getStatus());
//        assertEquals("Registro actualizado correctamente", result.getMessage());
//        verify(admCoymMapper, times(1)).updateByPrimaryKey(any(AdmCoym.class));
//    }

    @Test
    void testUpdateByPrimaryKey_NoExiste() {
        AdmCoymFormDTO formDTO = new AdmCoymFormDTO();
        formDTO.setCodCoym("COY001");
        formDTO.setNomCoym("Nombre de prueba");

        when(admCoymMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admCoymService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("La región no existe, no se puede actualizar", result.getMessage());
    }


    @Test
    void testSelectByPrimaryKey() {
        String codCoym = "COY001";
        AdmCoym coym = new AdmCoym();
        when(admCoymMapper.selectByPrimaryKey(codCoym)).thenReturn(coym);

        AdmCoym result = admCoymService.selectByPrimaryKey(codCoym);

        assertNotNull(result);
        verify(admCoymMapper, times(1)).selectByPrimaryKey(codCoym);
    }

    @Test
    void testSelectAll() {
        List<AdmCoymDTO> coymList = new ArrayList<>();
        when(admCoymMapper.selectAll()).thenReturn(coymList);

        List<AdmCoymDTO> result = admCoymService.selectAll();

        assertNotNull(result);
        assertEquals(coymList, result);
        verify(admCoymMapper, times(1)).selectAll();
    }

    @Test
    void testFiltrarCoym() {
        AdmCoymSearchDTO searchDTO = new AdmCoymSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<AdmCoymDTO> coymList = new ArrayList<>();
        PageInfo<AdmCoymDTO> pageInfo = new PageInfo<>(coymList);
        when(admCoymMapper.filtrarCoym(any(AdmCoymSearchDTO.class))).thenReturn(coymList);

        PageInfo<AdmCoymDTO> result = admCoymService.filtrarCoym(searchDTO);

        assertNotNull(result);
        assertEquals(coymList, result.getList());
        verify(admCoymMapper, times(1)).filtrarCoym(any(AdmCoymSearchDTO.class));
    }
}
