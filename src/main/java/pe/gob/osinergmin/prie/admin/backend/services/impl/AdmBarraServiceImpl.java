package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarraSiselecAlim;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUit;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.*;
import pe.gob.osinergmin.prie.admin.backend.dto.barraSisElectrico.BarraSistElectricoDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmBarraMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarra;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmBarraSiselecAlimMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmBarraService;

import java.util.Date;
import java.util.List;

@Service
public class AdmBarraServiceImpl implements AdmBarraService{

    @Autowired
    private AdmBarraMapper admBarraMapper;

    @Autowired
    private AdmBarraSiselecAlimMapper admBarraSiselecAlimMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public MessageDTO deleteByPrimaryKey(String codBarra) {
        try {
            if (codBarra == null || codBarra.trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la barra es requerido.");
            }

            AdmBarra existe = admBarraMapper.selectByPrimaryKey(codBarra);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "El código de barra no existe. No se puede eliminar.");
            }
            admBarraMapper.deleteByPrimaryKey(codBarra);
            return new MessageDTO(Constantes.SUCCES, "Barra eliminada exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al eliminar la barra: " + e.getMessage());
        }
    }


    @Override
    public MessageDTO insert(AdmBarraFormDTO record) {
        try {
            String usuario = "Admin";
            if (record.getCodBarra() == null || record.getCodBarra().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la barra es requerido.");
            }

            if (record.getNomBarra() == null || record.getNomBarra().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la barra es requerido.");
            }

            if (record.getEstado() == null || record.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado de la barra es requerido.");
            }

            AdmBarra existe = admBarraMapper.selectByPrimaryKey(record.getCodBarra());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "La barra con el código proporcionado ya existe.");
            }

            if (!record.getEstado().equals("1") && !record.getEstado().equals("0")) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser '1' (Habilitado) o '0' (Deshabilitado).");
            }

            AdmBarra admBarra = new AdmBarra();
            admBarra.setCodBarra(record.getCodBarra());
            admBarra.setNomBarra(record.getNomBarra());
            admBarra.setTension(record.getTension());
            admBarra.setCodBrg(record.getCodBrg());
            admBarra.setAreaDemanda(record.getAreaDemanda());
            admBarra.setCodSubestacion(record.getCodSubestacion());
            admBarra.setEstado(record.getEstado());
            admBarra.setEnRedDistribucion(record.getEnRedDistribucion());
            admBarra.setCodBarraCt(record.getCodBarraCt());

            admBarra.setAdCodUsuario(usuario);
            admBarra.setIpCreacion(IpUtils.getClientIp(request));
            admBarra.setAdFecha(new Date());
            admBarraMapper.insert(admBarra);

            return new MessageDTO(Constantes.SUCCES, "Barra creada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al crear la barra: " + e.getMessage());
        }
    }


    @Override
    public int insertSelective(AdmBarra record) {
        return admBarraMapper.insertSelective(record);
    }

    @Override
    public AdmBarra selectByPrimaryKey(String codBarra) {
        return admBarraMapper.selectByPrimaryKey(codBarra);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmBarra record) {
        return admBarraMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmBarraActualizarDTO record) {
        try {
            if (record.getCodBarra() == null || record.getCodBarra().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la barra es requerido.");
            }

            if (record.getNomBarra() == null || record.getNomBarra().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la barra es requerido.");
            }

            if (record.getEstado() == null || record.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado de la barra es requerido.");
            }

            AdmBarra existe = admBarraMapper.selectByPrimaryKey(record.getCodBarra());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "El código de barra no existe. No se puede actualizar.");
            }

            if (!record.getEstado().equals("1") && !record.getEstado().equals("0")) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser '1' (Habilitado) o '0' (Deshabilitado).");
            }

            AdmBarra admBarra = new AdmBarra();
            admBarra.setCodBarra(record.getCodBarra());
            admBarra.setNomBarra(record.getNomBarra());
            admBarra.setTension(record.getTension());
            admBarra.setCodBrg(record.getCodBrg());
            admBarra.setAreaDemanda(record.getAreaDemanda());
            admBarra.setCodSubestacion(record.getCodSubestacion());
            admBarra.setEstado(record.getEstado());
            admBarra.setEnRedDistribucion(record.getEnRedDistribucion());
            admBarra.setCodBarraCt(record.getCodBarraCt());
            admBarra.setIpActualizacion(IpUtils.getClientIp(request));
            admBarra.setFeActualizacion(new Date());
            admBarraMapper.updateByPrimaryKey(admBarra);

            if (record.getBarraSistElectricoDTOList() != null && !record.getBarraSistElectricoDTOList().isEmpty()) {
                for (BarraSistElectricoDTO dto : record.getBarraSistElectricoDTOList()) {
                    admBarraSiselecAlimMapper.borrarPorBarra(record.getCodBarra());
                }
                for (BarraSistElectricoDTO dto : record.getBarraSistElectricoDTOList()) {
                    AdmBarraSiselecAlim alim = new AdmBarraSiselecAlim();
                    alim.setCodSisElec(dto.getCodSisElec());
                    alim.setCodBarra(record.getCodBarra());
                    alim.setFecAlta(new Date());
                    alim.setAdCodUsuario("usuario_actual");
                    alim.setAdFecha(new Date());
                    alim.setEstado("1");
                    admBarraSiselecAlimMapper.insert(alim);
                }
            }

            return new MessageDTO(Constantes.SUCCES, "Barra actualizada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al actualizar la barra: " + e.getMessage());
        }
    }



    @Override
    public List<AdmBarraDTO> selectAll() {
        return admBarraMapper.selectAll();
    }

    @Override
    public PageInfo<AdmBarraDTO> filtrar(AdmBarraSearchDTO admBarraSearchDTO) {
        try {
            String sortField = mapSortField(
                    admBarraSearchDTO.getSort() != null && !admBarraSearchDTO.getSort().isEmpty()
                            ? admBarraSearchDTO.getSort()
                            : "COD_BARRA"
            );

            String order = validateOrder(
                    admBarraSearchDTO.getOrder() != null && !admBarraSearchDTO.getOrder().isEmpty()
                            ? admBarraSearchDTO.getOrder()
                            : "ASC"
            );

            PageHelper.startPage(admBarraSearchDTO.getPage(), admBarraSearchDTO.getSize(), sortField + " " + order);

            List<AdmBarraDTO> resultList = admBarraMapper.filtrar(admBarraSearchDTO);
            return new PageInfo<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }

    private String mapSortField(String sort) {
        switch (sort) {
            case "codigo":
                return "A.COD_BARRA";
            case "descripcion":
                return "A.NOM_BARRA";
            case "tension":
                return "A.TENSION";
            case "brg":
                return "B.NOM_BARRA";
            case "subestacion":
                return "C.NOM_SUBESTACION";
            case "areaDemandada":
                return "D.DSC_AREA_DEMANDA";
            default:
                return "A.COD_BARRA";
        }
    }

    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

    @Override
    public List<AdmBarraResultDTO> listarBRG() {
        return admBarraMapper.listarBRG();
    }

    @Override
    public List<AdmBarraDTO> listarPorCodSubestacion(String subestacion) {
        return admBarraMapper.listarPorCodSubestacion(subestacion);
    }

}
