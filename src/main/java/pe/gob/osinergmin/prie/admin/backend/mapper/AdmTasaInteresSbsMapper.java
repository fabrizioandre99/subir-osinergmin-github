package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTasaInteresSbs;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaIntereDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresResult;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresSearchDTO;


import java.util.Date;
import java.util.List;

public interface AdmTasaInteresSbsMapper {
    int insert(AdmTasaInteresSbs record);

    int insertSelective(AdmTasaInteresSbs record);

    List<TasaInteresResult> listarConFiltroEstado(TasaInteresSearchDTO tasaInteresSearchDTO);

    List<TasaIntereDTO> ListarTasaInteres();

    int updateByFecTasaEmitida(AdmTasaInteresSbs admTasaInteresSbs);

    int deleteByPrimaryKey(@Param("tasaDiaria") Double tasaDiaria, @Param("fecTasaEmitida") String fecTasaEmitida);

    AdmTasaInteresSbs selectByTasaDiaria(@Param("tasaDiaria") Double tasaDiaria, @Param("fecTasaEmitida") String fecTasaEmitida);

}