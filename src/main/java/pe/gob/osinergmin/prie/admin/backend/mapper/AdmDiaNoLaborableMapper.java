package pe.gob.osinergmin.prie.admin.backend.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmDiaNoLaborable;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableMapperDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableSearchDTO;

public interface AdmDiaNoLaborableMapper {
    int deleteByPrimaryKey(Date fecha);

    int insert(AdmDiaNoLaborable record);

    int insertSelective(AdmDiaNoLaborable record);

    AdmDiaNoLaborable selectByPrimaryKey(Date fecha);

    int updateByPrimaryKeySelective(AdmDiaNoLaborable record);

    int updateByPrimaryKey(AdmDiaNoLaborable record);

    List<DiaNoLaborableDTO> enlistarTodos();

    List<DiaNoLaborableDTO> filtrar(DiaNoLaborableSearchDTO diaNoLaborableSearchDTO);

    DiaNoLaborableMapperDTO buscarFechaString(String fecha);

    int eliminarString(@Param("fecha") String fecha);

    int insertarNuevo(DiaNoLaborableMapperDTO record);

    int actualizarNuevo(DiaNoLaborableMapperDTO record);
}