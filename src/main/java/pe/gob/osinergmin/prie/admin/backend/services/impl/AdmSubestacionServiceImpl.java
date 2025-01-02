package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarra;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraListadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionActualizarFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionListadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmBarraMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSubestacionMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSubestacion;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSubestacionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdmSubestacionServiceImpl implements AdmSubestacionService{

    @Autowired
    private AdmSubestacionMapper admSubestacionMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdmBarraMapper admBarraMapper;

    @Override
    public MessageDTO deleteByPrimaryKey(String codSubestacion) {
        try {
            if (codSubestacion == null || codSubestacion.trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de subestación no puede estar vacío.");
            }
            AdmSubestacion existe = admSubestacionMapper.selectByPrimaryKey(codSubestacion);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "La subestación no existe, no se puede eliminar.");
            }
            admSubestacionMapper.deleteByPrimaryKey(codSubestacion);
            return new MessageDTO(Constantes.SUCCES, "Subestación eliminada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "No se puede eliminar porque el COD SUBESTACIÓN esta siendo usado en BARRA");
        }
    }


    @Override
    public MessageDTO insert(AdmSubestacionFormDTO admSubestacionFormDTO) {
        try {
            if (admSubestacionFormDTO.getCodSubestacion() == null || admSubestacionFormDTO.getCodSubestacion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la subestación es requerido.");
            }
            if (admSubestacionFormDTO.getNomSubestacion() == null || admSubestacionFormDTO.getNomSubestacion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la subestación es requerido.");
            }
            if (admSubestacionFormDTO.getEstado() == null || admSubestacionFormDTO.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado de la subestación es requerido.");
            }
            if (admSubestacionFormDTO.getCodUbigeo() == null || admSubestacionFormDTO.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El codigo Ubigeo es requerido.");
            }
            AdmSubestacion existe = admSubestacionMapper.selectByPrimaryKey(admSubestacionFormDTO.getCodSubestacion());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "La subestación con el código proporcionado ya existe.");
            }
            if (!admSubestacionFormDTO.getEstado().equals("1") && !admSubestacionFormDTO.getEstado().equals("0")) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser '1' (Habilitado) o '0' (Deshabilitado).");
            }

            AdmSubestacion admSubestacion = new AdmSubestacion();
            admSubestacion.setCodSubestacion(admSubestacionFormDTO.getCodSubestacion());
            admSubestacion.setNomSubestacion(admSubestacionFormDTO.getNomSubestacion());
            admSubestacion.setEstado(admSubestacionFormDTO.getEstado());
            admSubestacion.setCodUbigeo(admSubestacionFormDTO.getCodUbigeo());
            admSubestacion.setIdSisTrans(admSubestacionFormDTO.getIdSisTrans());
            admSubestacion.setAdCodUsuario("admin");
            admSubestacion.setIpCreacion(IpUtils.getClientIp(request));
            admSubestacion.setAdFecha(new Date());
            admSubestacionMapper.insert(admSubestacion);

            return new MessageDTO(Constantes.SUCCES, "Subestación creada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al crear la subestación: " + e.getMessage());
        }
    }


    @Override
    public int insertSelective(AdmSubestacion record) {
        return admSubestacionMapper.insertSelective(record);
    }

    @Override
    public AdmSubestacion selectByPrimaryKey(String codSubestacion) {
        return admSubestacionMapper.selectByPrimaryKey(codSubestacion);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmSubestacion record) {
        return admSubestacionMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmSubestacionActualizarFormDTO admSubestacionFormDTO) {
        try {
            AdmSubestacion existe = admSubestacionMapper.selectByPrimaryKey(admSubestacionFormDTO.getCodSubestacion());

            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "La subestación no existe.");
            }


            if (admSubestacionFormDTO.getCodSubestacion() == null || admSubestacionFormDTO.getCodSubestacion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la subestación es requerido.");
            }

            if (admSubestacionFormDTO.getNomSubestacion() == null || admSubestacionFormDTO.getNomSubestacion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la subestación es requerido.");
            }

            if (admSubestacionFormDTO.getEstado() == null || admSubestacionFormDTO.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado de la subestación es requerido.");
            }

            if (!admSubestacionFormDTO.getEstado().equals("1") && !admSubestacionFormDTO.getEstado().equals("0")) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser '1' (Habilitado) o '0' (Deshabilitado).");
            }

            AdmSubestacion admSubestacion = new AdmSubestacion();
            admSubestacion.setCodSubestacion(admSubestacionFormDTO.getCodSubestacion());
            admSubestacion.setNomSubestacion(admSubestacionFormDTO.getNomSubestacion());
            admSubestacion.setEstado(admSubestacionFormDTO.getEstado());
            admSubestacion.setCodUbigeo(admSubestacionFormDTO.getCodUbigeo());
            admSubestacion.setIdSisTrans(admSubestacionFormDTO.getIdSisTrans());
            admSubestacion.setUsActualizacion("admin");
            admSubestacion.setIpActualizacion(IpUtils.getClientIp(request));
            admSubestacion.setFeActualizacion(new Date());
            admSubestacionMapper.updateByPrimaryKey(admSubestacion);

            admBarraMapper.deleteByCodSubestacion(admSubestacionFormDTO.getCodSubestacion());

            List<AdmBarraFormDTO> barraList = admSubestacionFormDTO.getBarra();

            if (barraList != null && !barraList.isEmpty()) {
                for (AdmBarraFormDTO barraDTO : barraList) {

                    if (barraDTO.getCodBarra() == null || barraDTO.getCodBarra().trim().isEmpty()) {
                        return new MessageDTO(Constantes.ERROR, "El código de la barra es requerido.");
                    }

                    if (barraDTO.getNomBarra() == null || barraDTO.getNomBarra().trim().isEmpty()) {
                        return new MessageDTO(Constantes.ERROR, "El nombre de la barra es requerido.");
                    }

                    if (barraDTO.getEstado() == null || barraDTO.getEstado().trim().isEmpty()) {
                        return new MessageDTO(Constantes.ERROR, "El estado de la barra es requerido.");
                    }


                    AdmBarra admBarra = new AdmBarra();
                    admBarra.setCodBarra(barraDTO.getCodBarra());
                    admBarra.setNomBarra(barraDTO.getNomBarra());
                    admBarra.setTension(barraDTO.getTension());
                    admBarra.setCodBrg(barraDTO.getCodBrg());
                    admBarra.setAreaDemanda(barraDTO.getAreaDemanda());
                    admBarra.setCodSubestacion(admSubestacionFormDTO.getCodSubestacion());
                    admBarra.setEstado(barraDTO.getEstado());
                    admBarra.setEnRedDistribucion(barraDTO.getEnRedDistribucion());
                    admBarra.setCodBarraCt(barraDTO.getCodBarraCt());
                    admBarra.setAdCodUsuario("admin");
                    admBarra.setIpCreacion(IpUtils.getClientIp(request));
                    admBarra.setAdFecha(new Date());
                    admBarraMapper.insert(admBarra);
                }
            }

            return new MessageDTO(Constantes.SUCCES, "Subestación y barras actualizadas exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al actualizar la subestación: " + e.getMessage());
        }
    }

    @Override
    public List<AdmSubestacionListadoDTO> listarSubestaciones() {
        return admSubestacionMapper.listarSubestaciones();
    }

    @Override
    public PageInfo<AdmSubestacionListadoDTO> listarFiltro(AdmSubestacionSearchDTO searchDTO) {
        try {
            String sortField = mapSortField(
                    searchDTO.getSort() != null && !searchDTO.getSort().isEmpty() ? searchDTO.getSort() : "COD_SUBESTACION"
            );
            String order = validateOrder(
                    searchDTO.getOrder() != null && !searchDTO.getOrder().isEmpty() ? searchDTO.getOrder() : "ASC"
            );

            PageHelper.startPage(searchDTO.getPage(), searchDTO.getSize(), sortField + " " + order);

            List<AdmSubestacionListadoDTO> resultList = admSubestacionMapper.listarFiltro(searchDTO);
            PageInfo<AdmSubestacionListadoDTO> pageInfo = new PageInfo<>(resultList);

            for (AdmSubestacionListadoDTO subestacion : resultList) {
                List<AdmBarraListadoDTO> barras = admBarraMapper.selectByCodSubestacion(subestacion.getCodSubestacion());
                subestacion.setBarra(barras != null ? barras : new ArrayList<>());
            }

            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }


    private String mapSortField(String sort) {
        switch (sort) {
            case "codSubestacion":
                return "S.COD_SUBESTACION";
            case "nomSubestacion":
                return "S.NOM_SUBESTACION";
            case "nomDepartamento":
                return "D.NOM_UBIGEO";
            case "nomProvincia":
                return "P.NOM_UBIGEO";
            case "nomDistrito":
                return "U.NOM_UBIGEO";
            default:
                return "S.COD_SUBESTACION";
        }
    }

    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }


}
