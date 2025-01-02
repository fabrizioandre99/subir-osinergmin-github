package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmUit;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitSearchDTO;

import java.util.List;

public interface AdmUitMapper {
    int deleteByPrimaryKey(String anioMes);

    int insert(AdmUit record);

    int insertSelective(AdmUit record);

    AdmUit selectByPrimaryKey(String anioMes);

    int updateByPrimaryKeySelective(AdmUit record);

    int updateByPrimaryKey(AdmUit record);

    List<AdmUitDTO> selectAll();

    List<AdmUitDTO> listarFiltro(AdmUitSearchDTO searchDTO);
}