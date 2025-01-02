package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTasaInteresSbs;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaIntereDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresResult;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTasaInteresSbsMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTasaInteresSbsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdmTasaInteresSbsServiceImpl implements AdmTasaInteresSbsService {

    @Autowired
    private AdmTasaInteresSbsMapper admTasaInteresSbsMapper;

    @Autowired
    private HttpServletRequest request;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = LoggerFactory.getLogger(AdmTasaInteresSbsServiceImpl.class);

    @Transactional
    @Override
    public MessageDTO insert(TasaInteresFormDTO formDTO) throws Exception {
        try {
            String fecTasaEmitidaStr = dateFormat.format(formDTO.getFecTasaEmitida());
            AdmTasaInteresSbs existeTasaInteres = admTasaInteresSbsMapper.selectByTasaDiaria(formDTO.getTasaDiaria(), fecTasaEmitidaStr);

            if (existeTasaInteres != null) {
                return new MessageDTO("ERROR", "La tasa de interés diaria ya existe.");
            }

            AdmTasaInteresSbs admTasaInteresSbs = new AdmTasaInteresSbs();
            admTasaInteresSbs.setTasaAnual(formDTO.getTasaAnual());
            admTasaInteresSbs.setTasaMensual(formDTO.getTasaMensual());
            admTasaInteresSbs.setTasaDiaria(formDTO.getTasaDiaria());
            admTasaInteresSbs.setTamn(formDTO.getTamn());
            admTasaInteresSbs.setTipmn(formDTO.getTipmn());
            admTasaInteresSbs.setEstadoRegistro(formDTO.getEstadoRegistro());
            admTasaInteresSbs.setUsuarioCreacion("Usuario01");
            admTasaInteresSbs.setTerminalCreacion(IpUtils.getClientIp(request));
            admTasaInteresSbs.setFecTasaEmitida(formDTO.getFecTasaEmitida());

            admTasaInteresSbs.setFechaCreacion(new Date());

            admTasaInteresSbsMapper.insert(admTasaInteresSbs);
            return new MessageDTO(Constantes.SUCCES, "OK");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public PageInfo<TasaInteresResult> listarConFiltroEstado(TasaInteresSearchDTO tasaInteresSearchDTO) {

        logger.info("Fecha recibida en el filtro: {}", tasaInteresSearchDTO.getFecTasaEmitida());

        String sortField = mapSortField(tasaInteresSearchDTO.getSort());
        String orderDirection = validateOrder(tasaInteresSearchDTO.getOrder());
        String orderBy = sortField + " " + orderDirection;

        PageHelper.startPage(tasaInteresSearchDTO.getPage(), tasaInteresSearchDTO.getSize(), orderBy);
        return new PageInfo<>(admTasaInteresSbsMapper.listarConFiltroEstado(tasaInteresSearchDTO));
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "FEC_TASA_EMITIDA";
        }
        switch (sort) {
            case "tasaAnual":
                return "TASA_ANUAL";
            case "tasaMensual":
                return "TASA_MENSUAL";
            case "tasaDiaria":
                return "TASA_DIARIA";
            case "tamn":
                return "TAMN";
            case "tipmn":
                return "TIPMN";
            case "estadoRegistro":
                return "ESTADO_REGISTRO";
            case "fecTasaEmitida":
                return "FEC_TASA_EMITIDA";
            default:
                return "FEC_TASA_EMITIDA";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

    @Override
    public List<TasaIntereDTO> listarTasaInteres() {
        try {
            return admTasaInteresSbsMapper.ListarTasaInteres();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public MessageDTO updateTasaDiaria(TasaInteresFormDTO formDTO) throws Exception {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fecTasaEmitidaStr = dateFormat.format(formDTO.getFecTasaEmitida());
            AdmTasaInteresSbs existeTasaInteres = admTasaInteresSbsMapper.selectByTasaDiaria(formDTO.getTasaDiaria(), fecTasaEmitidaStr);

            if (existeTasaInteres == null) {
                throw new Exception("No se encontró la tasa diaria de interés.");
            }

            existeTasaInteres.setFecTasaEmitidaString(fecTasaEmitidaStr);
            existeTasaInteres.setTasaAnual(formDTO.getTasaAnual());
            existeTasaInteres.setTasaMensual(formDTO.getTasaMensual());
            existeTasaInteres.setTamn(formDTO.getTamn());
            existeTasaInteres.setTipmn(formDTO.getTipmn());
            existeTasaInteres.setEstadoRegistro(formDTO.getEstadoRegistro());
            existeTasaInteres.setTerminalActualizacion(IpUtils.getClientIp(request));
            existeTasaInteres.setFechaActualizacion(new Date());
            admTasaInteresSbsMapper.updateByFecTasaEmitida(existeTasaInteres);

            return new MessageDTO(Constantes.SUCCES, "OK");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Transactional
    @Override
    public MessageDTO deleteByTasaDiaria(TasaInteresFormDTO formDTO) {
        try {
            String fecTasaEmitidaStr = dateFormat.format(formDTO.getFecTasaEmitida());

            AdmTasaInteresSbs existenteTasaInteres = admTasaInteresSbsMapper.selectByTasaDiaria(formDTO.getTasaDiaria(), fecTasaEmitidaStr);

            if (existenteTasaInteres == null) {
                return new MessageDTO("ERROR", "El ID de la tarifa no existe. No se puede eliminar.");
            }
            admTasaInteresSbsMapper.deleteByPrimaryKey(formDTO.getTasaDiaria(), fecTasaEmitidaStr);
            return new MessageDTO(Constantes.SUCCES, "OK");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la tarifa: " + e.getMessage());
        }
    }

}
