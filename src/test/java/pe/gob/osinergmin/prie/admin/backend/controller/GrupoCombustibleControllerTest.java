package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmGrupoCombustibleService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GrupoCombustibleControllerTest {

    @InjectMocks
    private GrupoCombustibleController grupoCombustibleController;

    @Mock
    private AdmGrupoCombustibleService grupoCombustibleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmGrupoCombustibleFormdDTO formDTO = new AdmGrupoCombustibleFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Grupo de combustible creado correctamente");

        when(grupoCombustibleService.insert(any(AdmGrupoCombustibleFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = grupoCombustibleController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Grupo de combustible creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmGrupoCombustibleFormdDTO formDTO = new AdmGrupoCombustibleFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Grupo de combustible actualizado correctamente");

        when(grupoCombustibleService.updateByPrimaryKey(any(AdmGrupoCombustibleFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = grupoCombustibleController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Grupo de combustible actualizado correctamente", response.getDatas());
    }

    @Test
    void testDeleteByPrimaryKey() {
        String codGrupoCombustible = "GC001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Grupo de combustible eliminado correctamente");

        when(grupoCombustibleService.deleteByPrimaryKey(codGrupoCombustible)).thenReturn(messageDTO);

        ResponseEntity<String> response = grupoCombustibleController.deleteByPrimaryKey(codGrupoCombustible);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Grupo de combustible eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmGrupoCombustibleDTO> grupoCombustibleList = new ArrayList<>();
        grupoCombustibleList.add(new AdmGrupoCombustibleDTO());

        when(grupoCombustibleService.selectAll()).thenReturn(grupoCombustibleList);

        ResponseEntity<List<AdmGrupoCombustibleDTO>> response = grupoCombustibleController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarTarifasFiltro() {
        AdmGrupoCombustibleSearchDTO searchDTO = new AdmGrupoCombustibleSearchDTO();
        PageInfo<AdmGrupoCombustibleDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(grupoCombustibleService.filtrar(any(AdmGrupoCombustibleSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmGrupoCombustibleDTO>> response = grupoCombustibleController.listarTarifasFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
