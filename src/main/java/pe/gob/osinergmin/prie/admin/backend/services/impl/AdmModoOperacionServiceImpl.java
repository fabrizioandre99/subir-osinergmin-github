package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmModoOperacionMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmModoOperacion;
import pe.gob.osinergmin.prie.admin.backend.services.AdmModoOperacionService;

import java.util.Date;
import java.util.List;

@Service
public class AdmModoOperacionServiceImpl implements AdmModoOperacionService{

    @Autowired
    private AdmModoOperacionMapper admModoOperacionMapper;

    @Autowired
    private HttpServletRequest request;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codModoOperacion) {
        try {
            AdmModoOperacion existe = admModoOperacionMapper.selectByPrimaryKey(codModoOperacion);

            if (existe != null) {
                admModoOperacionMapper.deleteByPrimaryKey(codModoOperacion);
                return new MessageDTO(Constantes.SUCCES, "Modo de operación eliminado con éxito");
            }
            return new MessageDTO(Constantes.ERROR, "Modo de operación no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmModoOperacionFormdDTO admModoOperacionFormdDTO) {
        try {
            if (admModoOperacionFormdDTO.getCodModoOperacion() == null ||
                    admModoOperacionFormdDTO.getCodModoOperacion().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código no debe exceder los 6 caracteres");
            }

            if (admModoOperacionFormdDTO.getDscModoOperacion() != null &&
                    admModoOperacionFormdDTO.getDscModoOperacion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La descripción no debe exceder 100 caracteres");
            }
            if (admModoOperacionFormdDTO.getCodCoes() != null &&
                    admModoOperacionFormdDTO.getCodCoes().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código COES no debe exceder 10 caracteres");
            }
            if (admModoOperacionFormdDTO.getEstadoRegistro() == null ||
                    admModoOperacionFormdDTO.getEstadoRegistro().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado de registro debe tener 1 carácter");
            }

            AdmModoOperacion existe = admModoOperacionMapper.selectByPrimaryKey(admModoOperacionFormdDTO.getCodModoOperacion());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Modo de operación ya existe");
            }

            AdmModoOperacion modoOperacion = new AdmModoOperacion();
            modoOperacion.setCodModoOperacion(admModoOperacionFormdDTO.getCodModoOperacion());
            modoOperacion.setDscModoOperacion(admModoOperacionFormdDTO.getDscModoOperacion());
            modoOperacion.setCodCoes(admModoOperacionFormdDTO.getCodCoes());
            modoOperacion.setEstadoRegistro(admModoOperacionFormdDTO.getEstadoRegistro());
            modoOperacion.setCodModoOperacionAnt(admModoOperacionFormdDTO.getCodModoOperacionAnt());

            modoOperacion.setUsuarioCreacion("admin");
            modoOperacion.setTerminalCreacion(IpUtils.getClientIp(request));
            modoOperacion.setFechaCreacion(new Date());

            admModoOperacionMapper.insert(modoOperacion);
            return new MessageDTO(Constantes.SUCCES, "Modo de operación insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmModoOperacion record) {
        return admModoOperacionMapper.insertSelective(record);
    }

    @Override
    public AdmModoOperacion selectByPrimaryKey(String codModoOperacion) {
        return admModoOperacionMapper.selectByPrimaryKey(codModoOperacion);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmModoOperacion record) {
        return admModoOperacionMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmModoOperacionFormdDTO admModoOperacionFormdDTO) {
        try {
            if (admModoOperacionFormdDTO.getCodModoOperacion() == null ||
                    admModoOperacionFormdDTO.getCodModoOperacion().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código no debe exceder los 6 caracteres");
            }
            if (admModoOperacionFormdDTO.getDscModoOperacion() != null &&
                    admModoOperacionFormdDTO.getDscModoOperacion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La descripción no debe exceder 100 caracteres");
            }
            if (admModoOperacionFormdDTO.getCodCoes() != null &&
                    admModoOperacionFormdDTO.getCodCoes().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código COES no debe exceder 10 caracteres");
            }
            if (admModoOperacionFormdDTO.getEstadoRegistro() == null ||
                    admModoOperacionFormdDTO.getEstadoRegistro().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado de registro debe tener 1 carácter");
            }

            AdmModoOperacion existe = admModoOperacionMapper.selectByPrimaryKey(admModoOperacionFormdDTO.getCodModoOperacion());
            if (existe != null) {
                AdmModoOperacion modoOperacion = new AdmModoOperacion();
                modoOperacion.setCodModoOperacion(admModoOperacionFormdDTO.getCodModoOperacion());
                modoOperacion.setDscModoOperacion(admModoOperacionFormdDTO.getDscModoOperacion());
                modoOperacion.setCodCoes(admModoOperacionFormdDTO.getCodCoes());
                modoOperacion.setEstadoRegistro(admModoOperacionFormdDTO.getEstadoRegistro());
                modoOperacion.setCodModoOperacionAnt(admModoOperacionFormdDTO.getCodModoOperacionAnt());

                modoOperacion.setUsuarioActualizacion("admin");
                modoOperacion.setTerminalActualizacion(IpUtils.getClientIp(request));
                modoOperacion.setFechaActualizacion(new Date());

                admModoOperacionMapper.updateByPrimaryKey(modoOperacion);
                return new MessageDTO(Constantes.SUCCES, "Modo de operación actualizado correctamente");
            }

            return new MessageDTO(Constantes.ERROR, "Modo de operación no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmModoOperacionDTO> selectAll() {
        return admModoOperacionMapper.selectAll();
    }

    @Override
    public PageInfo<AdmModoOperacionDTO> filtro(AdmModoOperacionSearchDTO admModoOperacionSearchDTO) {
        try {
            String sortField = mapSortField(
                    admModoOperacionSearchDTO.getSort() != null && !admModoOperacionSearchDTO.getSort().isEmpty()
                            ? admModoOperacionSearchDTO.getSort()
                            : "codModoOperacion"
            );

            String order = validateOrder(
                    admModoOperacionSearchDTO.getOrder() != null && !admModoOperacionSearchDTO.getOrder().isEmpty()
                            ? admModoOperacionSearchDTO.getOrder()
                            : "ASC"
            );

            PageHelper.startPage(admModoOperacionSearchDTO.getPage(), admModoOperacionSearchDTO.getSize(), sortField + " " + order);

            List<AdmModoOperacionDTO> resultList = admModoOperacionMapper.filtro(admModoOperacionSearchDTO);
            return new PageInfo<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }

    private String mapSortField(String sort) {
        if ("codModoOperacion".equals(sort)) {
            return "COD_MODO_OPERACION";
        } else if ("dscModoOperacion".equals(sort)) {
            return "DSC_MODO_OPERACION";
        } else if ("codCoes".equals(sort)) {
            return "COD_COES";
        } else if ("estadoRegistro".equals(sort)) {
            return "ESTADO_REGISTRO";
        } else if ("codModoOperacionAnt".equals(sort)) {
            return "COD_MODO_OPERACION_ANT";
        }
        return "COD_MODO_OPERACION";
    }

    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }
}
