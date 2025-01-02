package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmSectorTipico;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoSearchDTO;

import java.util.List;

public interface AdmSectorTipicoMapper {
    int deleteByPrimaryKey(String codSectorTipico);

    int insert(AdmSectorTipico record);

    int insertSelective(AdmSectorTipico record);

    AdmSectorTipico selectByPrimaryKey(String codSectorTipico);

    int updateByPrimaryKeySelective(AdmSectorTipico record);

    int updateByPrimaryKey(AdmSectorTipico record);

    List<AdmSectorTipicoDTO> selectAll();

    List<AdmSectorTipicoDTO> filtrar(AdmSectorTipicoSearchDTO admSectorTipicoSearchDTO);
}