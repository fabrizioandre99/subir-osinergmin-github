package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTasaInteresSbs;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaIntereDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresResult;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresSearchDTO;

import java.util.List;

public interface AdmTasaInteresSbsService{

    MessageDTO insert(TasaInteresFormDTO fomrDTO) throws Exception;

    PageInfo<TasaInteresResult> listarConFiltroEstado(TasaInteresSearchDTO tasaInteresSearchDTO);

    List<TasaIntereDTO> listarTasaInteres();

    MessageDTO updateTasaDiaria(TasaInteresFormDTO fomrDTO) throws Exception;

    MessageDTO deleteByTasaDiaria(TasaInteresFormDTO formDTO);
}
