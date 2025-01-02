package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUit;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitSearchDTO;

import java.util.List;

public interface AdmUitService{

    MessageDTO deleteByPrimaryKey(String anioMes);

    MessageDTO insert(AdmUitFormDTO formDTO) throws Exception;

    AdmUit selectByPrimaryKey(String anioMes);

    MessageDTO updateByPrimaryKey(AdmUitFormDTO formDTO)throws Exception;

    List<AdmUitDTO> selectAll();

    PageInfo<AdmUitDTO> listarFiltro(AdmUitSearchDTO searchDTO);
}
