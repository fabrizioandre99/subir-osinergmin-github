package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcSectorTipico;

public interface AdmProcSectorTipicoMapper {
    int deleteByPrimaryKey(@Param("codSectorTipico") String codSectorTipico, @Param("codProcSupervision") String codProcSupervision);

    int insert(AdmProcSectorTipico record);

    int insertSelective(AdmProcSectorTipico record);

    AdmProcSectorTipico selectByPrimaryKey(@Param("codSectorTipico") String codSectorTipico, @Param("codProcSupervision") String codProcSupervision);

    int updateByPrimaryKeySelective(AdmProcSectorTipico record);

    int updateByPrimaryKey(AdmProcSectorTipico record);

    int borrarPorCodProcesoSupervisor(String codProcSupervision);
}