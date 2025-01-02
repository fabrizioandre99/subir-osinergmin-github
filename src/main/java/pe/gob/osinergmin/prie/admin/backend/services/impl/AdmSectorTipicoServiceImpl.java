package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico.AdmSectorTipicoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSectorTipicoMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSectorTipico;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSectorTipicoService;

import java.util.Date;
import java.util.List;

@Service
public class AdmSectorTipicoServiceImpl implements AdmSectorTipicoService{

    @Autowired
    private AdmSectorTipicoMapper admSectorTipicoMapper;


    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codSectorTipico) {
        try {
            AdmSectorTipico existe = admSectorTipicoMapper.selectByPrimaryKey(codSectorTipico);
            if (existe != null) {
                admSectorTipicoMapper.deleteByPrimaryKey(codSectorTipico);
                return new MessageDTO(Constantes.SUCCES, "Sector típico eliminado correctamente.");
            }
            return new MessageDTO(Constantes.ERROR, "No se encontró el sector típico.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al eliminar el sector típico: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmSectorTipicoFormDTO admSectorTipicoFormDTO) {
        try {
            if (admSectorTipicoFormDTO.getCodSectorTipico() == null || admSectorTipicoFormDTO.getCodSectorTipico().trim().isEmpty() || admSectorTipicoFormDTO.getCodSectorTipico().length() > 3) {
                return new MessageDTO(Constantes.ERROR, "El código del sector típico es requerido y debe tener entre 1 y 3 caracteres.");
            }

            if (admSectorTipicoFormDTO.getDscSectorTipico() != null && admSectorTipicoFormDTO.getDscSectorTipico().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La descripción del sector típico no debe exceder 100 caracteres.");
            }
            if (admSectorTipicoFormDTO.getEstado() == null || admSectorTipicoFormDTO.getEstado().trim().isEmpty() || admSectorTipicoFormDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del sector típico es requerido y debe tener 1 carácter.");
            }

            AdmSectorTipico existe = admSectorTipicoMapper.selectByPrimaryKey(admSectorTipicoFormDTO.getCodSectorTipico());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "El sector típico ya existe.");
            }

            AdmSectorTipico admSectorTipico = new AdmSectorTipico();
            admSectorTipico.setCodSectorTipico(admSectorTipicoFormDTO.getCodSectorTipico());
            admSectorTipico.setDscSectorTipico(admSectorTipicoFormDTO.getDscSectorTipico());
            admSectorTipico.setEstado(admSectorTipicoFormDTO.getEstado());
            admSectorTipico.setAdFecha(new Date());
            admSectorTipico.setAdCodUsuario("admin");

            admSectorTipicoMapper.insert(admSectorTipico);
            return new MessageDTO(Constantes.SUCCES, "Sector típico agregado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al insertar el sector típico: " + e.getMessage());
        }
    }


    @Override
    public int insertSelective(AdmSectorTipico record) {
        return admSectorTipicoMapper.insertSelective(record);
    }

    @Override
    public AdmSectorTipico selectByPrimaryKey(String codSectorTipico) {
        return admSectorTipicoMapper.selectByPrimaryKey(codSectorTipico);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmSectorTipico record) {
        return admSectorTipicoMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmSectorTipicoFormDTO admSectorTipicoFormDTO) {
        try {
            if (admSectorTipicoFormDTO.getCodSectorTipico() == null || admSectorTipicoFormDTO.getCodSectorTipico().trim().isEmpty() || admSectorTipicoFormDTO.getCodSectorTipico().length() > 3) {
                return new MessageDTO(Constantes.ERROR, "El código del sector típico es requerido y debe tener entre 1 y 3 caracteres.");
            }

            if (admSectorTipicoFormDTO.getDscSectorTipico() != null && admSectorTipicoFormDTO.getDscSectorTipico().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La descripción del sector típico no debe exceder 100 caracteres.");
            }
            if (admSectorTipicoFormDTO.getEstado() == null || admSectorTipicoFormDTO.getEstado().trim().isEmpty() || admSectorTipicoFormDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del sector típico es requerido y debe tener 1 carácter.");
            }

            AdmSectorTipico existe = admSectorTipicoMapper.selectByPrimaryKey(admSectorTipicoFormDTO.getCodSectorTipico());
            if (existe != null) {
                AdmSectorTipico admSectorTipico = new AdmSectorTipico();
                admSectorTipico.setCodSectorTipico(admSectorTipicoFormDTO.getCodSectorTipico());
                admSectorTipico.setDscSectorTipico(admSectorTipicoFormDTO.getDscSectorTipico());
                admSectorTipico.setEstado(admSectorTipicoFormDTO.getEstado());
                admSectorTipico.setAdFecha(new Date());
                admSectorTipico.setAdCodUsuario("admin");

                admSectorTipicoMapper.updateByPrimaryKey(admSectorTipico);
                return new MessageDTO(Constantes.SUCCES, "Sector típico actualizado correctamente.");
            }

            return new MessageDTO(Constantes.ERROR, "No se pudo actualizar el sector típico porque no existe.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al actualizar el sector típico: " + e.getMessage());
        }
    }


    @Override
    public List<AdmSectorTipicoDTO> selectAll() {
        return admSectorTipicoMapper.selectAll();
    }

    @Override
    public PageInfo<AdmSectorTipicoDTO> filtrar(AdmSectorTipicoSearchDTO admSectorTipicoSearchDTO) {
        try {
            String sortField = mapSortField(
                    admSectorTipicoSearchDTO.getSort() != null && !admSectorTipicoSearchDTO.getSort().isEmpty()
                            ? admSectorTipicoSearchDTO.getSort()
                            : "COD_SECTOR_TIPICO"
            );

            String order = validateOrder(
                    admSectorTipicoSearchDTO.getOrder() != null && !admSectorTipicoSearchDTO.getOrder().isEmpty()
                            ? admSectorTipicoSearchDTO.getOrder()
                            : "ASC"
            );

            PageHelper.startPage(admSectorTipicoSearchDTO.getPage(), admSectorTipicoSearchDTO.getSize(), sortField + " " + order);

            List<AdmSectorTipicoDTO> resultList = admSectorTipicoMapper.filtrar(admSectorTipicoSearchDTO);
            return new PageInfo<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }

    private String mapSortField(String sort) {
        switch (sort) {
            case "codSectorTipico":
                return "COD_SECTOR_TIPICO";
            case "dscSectorTipico":
                return "DSC_SECTOR_TIPICO";
            case "estado":
                return "ESTADO";
            default:
                return "COD_SECTOR_TIPICO";
        }
    }

    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

}
