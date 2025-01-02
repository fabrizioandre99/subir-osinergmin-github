package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSectorTipico;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoSearchDTO;

import java.util.List;

public interface AdmSectorTipicoService{

    MessageDTO deleteByPrimaryKey(String codSectorTipico);

    MessageDTO insert(AdmSectorTipicoFormDTO admSectorTipicoFormDTO);

    int insertSelective(AdmSectorTipico record);

    AdmSectorTipico selectByPrimaryKey(String codSectorTipico);

    int updateByPrimaryKeySelective(AdmSectorTipico record);

    MessageDTO updateByPrimaryKey(AdmSectorTipicoFormDTO admSectorTipicoFormDTO);

    List<AdmSectorTipicoDTO> selectAll();

    PageInfo<AdmSectorTipicoDTO> filtrar(AdmSectorTipicoSearchDTO admSectorTipicoSearchDTO);
}
