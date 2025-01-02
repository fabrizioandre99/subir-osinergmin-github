package pe.gob.osinergmin.prie.admin.backend.services;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmDiaNoLaborable;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.AdmDiaNoLaborableFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableSearchDTO;

public interface AdmDiaNoLaborableService{

    MessageDTO deleteByPrimaryKey(String fecha);

    MessageDTO insert(AdmDiaNoLaborableFormDTO record);

    int insertSelective(AdmDiaNoLaborable record);

    AdmDiaNoLaborable selectByPrimaryKey(Date fecha);

    int updateByPrimaryKeySelective(AdmDiaNoLaborable record);

    MessageDTO updateByPrimaryKey(AdmDiaNoLaborableFormDTO record);

    List<DiaNoLaborableDTO> enlistarTodos();

    PageInfo<DiaNoLaborableDTO> filtrar(DiaNoLaborableSearchDTO diaNoLaborableSearchDTO);
}
