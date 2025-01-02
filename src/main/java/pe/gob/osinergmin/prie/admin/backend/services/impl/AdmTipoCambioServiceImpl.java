package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.*;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoCambioMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoCambioService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdmTipoCambioServiceImpl implements AdmTipoCambioService {

    @Autowired
    private AdmTipoCambioMapper admTipoCambioMapper;

    @Transactional
    @Override
    public MessageDTO eliminarPorMonedaFuenteFecha(String fecha) {
        try {
            int existeFuente = admTipoCambioMapper.obtenerFecha(fecha);
            if (existeFuente > 0) {
                admTipoCambioMapper.deleteByPrimaryKey(fecha);
                return new MessageDTO(Constantes.SUCCES, "Se eliminó correctamente.");
            }
            return new MessageDTO(Constantes.ERROR, "El Tipo de cambio no fue eliminado.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insertarTipoCambio(TipoCambioFormDTO tipoCambioFormDTO) {
        try {
            if (tipoCambioFormDTO.getFuente() == null || tipoCambioFormDTO.getFuente().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La fuente es obligatoria.");
            }
            if (tipoCambioFormDTO.getFuente().length() != 3) {
                return new MessageDTO(Constantes.ERROR, "La fuente debe tener exactamente 3 caracteres.");
            }

            if (tipoCambioFormDTO.getMoneda() == null || tipoCambioFormDTO.getMoneda().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La moneda es obligatoria.");
            }

            if (tipoCambioFormDTO.getFecha() == null || tipoCambioFormDTO.getFecha().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La fecha es obligatoria.");
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(tipoCambioFormDTO.getFecha());
            } catch (ParseException e) {
                return new MessageDTO(Constantes.ERROR, "La fecha debe tener el formato 'yyyy-MM-dd'.");
            }

            if (tipoCambioFormDTO.getValCompra() != null) {
                try {
                    BigDecimal valCompra = new BigDecimal(tipoCambioFormDTO.getValCompra());
                    if (valCompra.scale() > 4) {
                        return new MessageDTO(Constantes.ERROR, "El valor de compra no puede tener más de 4 decimales.");
                    }
                    if (valCompra.compareTo(BigDecimal.ZERO) < 0) {
                        return new MessageDTO(Constantes.ERROR, "El valor de compra debe ser positivo.");
                    }
                    tipoCambioFormDTO.setValCompra(valCompra.toString());
                } catch (NumberFormatException e) {
                    return new MessageDTO(Constantes.ERROR, "El valor de compra debe ser un número válido.");
                }
            }

            if (tipoCambioFormDTO.getValVenta() != null) {
                try {
                    BigDecimal valVenta = new BigDecimal(tipoCambioFormDTO.getValVenta());
                    if (valVenta.scale() > 4) {
                        return new MessageDTO(Constantes.ERROR, "El valor de venta no puede tener más de 4 decimales.");
                    }
                    if (valVenta.compareTo(BigDecimal.ZERO) < 0) {
                        return new MessageDTO(Constantes.ERROR, "El valor de venta debe ser positivo.");
                    }
                    tipoCambioFormDTO.setValVenta(valVenta.toString());
                } catch (NumberFormatException e) {
                    return new MessageDTO(Constantes.ERROR, "El valor de venta debe ser un número válido.");
                }
            }

            int existeFuente = admTipoCambioMapper.obtenerFecha(tipoCambioFormDTO.getFecha());
            if (existeFuente > 0) {
                return new MessageDTO(Constantes.ERROR, "El tipo de cambio ya existe.");
            }

            TipoCambioCrearActDTO nuevoTipoCambio = new TipoCambioCrearActDTO();
            nuevoTipoCambio.setMoneda(tipoCambioFormDTO.getMoneda());
            nuevoTipoCambio.setFuente(tipoCambioFormDTO.getFuente());
            nuevoTipoCambio.setFecha(tipoCambioFormDTO.getFecha());
            nuevoTipoCambio.setValCompra(new BigDecimal(tipoCambioFormDTO.getValCompra()));
            nuevoTipoCambio.setValVenta(new BigDecimal(tipoCambioFormDTO.getValVenta()));
            nuevoTipoCambio.setAdCodUsuario("admin");
            nuevoTipoCambio.setAdFecha(new Date());

            admTipoCambioMapper.insertarNuevo(nuevoTipoCambio);

            return new MessageDTO(Constantes.SUCCES, "Tipo de cambio insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al insertar el tipo de cambio: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO actualizarTipoCambio(TipoCambioFormDTO tipoCambioFormDTO) {
        try {
            if (tipoCambioFormDTO.getFuente() == null || tipoCambioFormDTO.getFuente().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La fuente es obligatoria.");
            }

            if (tipoCambioFormDTO.getMoneda() == null || tipoCambioFormDTO.getMoneda().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La moneda es obligatoria.");
            }

            if (tipoCambioFormDTO.getFecha() == null || tipoCambioFormDTO.getFecha().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La fecha es obligatoria.");
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(tipoCambioFormDTO.getFecha());
            } catch (ParseException e) {
                return new MessageDTO(Constantes.ERROR, "La fecha debe tener el formato 'yyyy-MM-dd'.");
            }

            if (tipoCambioFormDTO.getValCompra() != null) {
                try {
                    BigDecimal valCompra = new BigDecimal(tipoCambioFormDTO.getValCompra());
                    if (valCompra.scale() > 4) {
                        return new MessageDTO(Constantes.ERROR, "El valor de compra no puede tener más de 4 decimales.");
                    }
                    if (valCompra.compareTo(BigDecimal.ZERO) < 0) {
                        return new MessageDTO(Constantes.ERROR, "El valor de compra debe ser positivo.");
                    }
                    tipoCambioFormDTO.setValCompra(valCompra.toString());
                } catch (NumberFormatException e) {
                    return new MessageDTO(Constantes.ERROR, "El valor de compra debe ser un número válido.");
                }
            }

            if (tipoCambioFormDTO.getValVenta() != null) {
                try {
                    BigDecimal valVenta = new BigDecimal(tipoCambioFormDTO.getValVenta());
                    if (valVenta.scale() > 4) {
                        return new MessageDTO(Constantes.ERROR, "El valor de venta no puede tener más de 4 decimales.");
                    }
                    if (valVenta.compareTo(BigDecimal.ZERO) < 0) {
                        return new MessageDTO(Constantes.ERROR, "El valor de venta debe ser positivo.");
                    }
                    tipoCambioFormDTO.setValVenta(valVenta.toString());
                } catch (NumberFormatException e) {
                    return new MessageDTO(Constantes.ERROR, "El valor de venta debe ser un número válido.");
                }
            }

            int fechaExistente = admTipoCambioMapper.obtenerFecha(tipoCambioFormDTO.getFecha());
            if (fechaExistente == 0) {
                return new MessageDTO(Constantes.ERROR, "No se encontró una fecha asociada a esta fuente.");
            }

            TipoCambioCrearActDTO nuevoTipoCambio = new TipoCambioCrearActDTO();
            nuevoTipoCambio.setMoneda(tipoCambioFormDTO.getMoneda());
            nuevoTipoCambio.setFuente(tipoCambioFormDTO.getFuente());
            nuevoTipoCambio.setFecha(tipoCambioFormDTO.getFecha());
            nuevoTipoCambio.setValCompra(new BigDecimal(tipoCambioFormDTO.getValCompra()));
            nuevoTipoCambio.setValVenta(new BigDecimal(tipoCambioFormDTO.getValVenta()));
            nuevoTipoCambio.setAdCodUsuario("admin");

            int filasActualizadas = admTipoCambioMapper.actualizarNuevo(nuevoTipoCambio);
            if (filasActualizadas > 0) {
                return new MessageDTO(Constantes.SUCCES, "Tipo de cambio actualizado exitosamente.");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se pudo actualizar el tipo de cambio.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al actualizar el tipo de cambio: " + e.getMessage());
        }
    }

    @Override
    public List<TipoCambioResultDTO> listarTiposCambio() {
        return admTipoCambioMapper.selectAll();
    }

    @Override
    public PageInfo<TipoCambioResultDTO> listarConFiltro(TipoCambioSearchDTO tipoCambioSearchDTO) {
        String sortField = mapSortField(tipoCambioSearchDTO.getSort());
        String orderDirection = validateOrder(tipoCambioSearchDTO.getOrder());
        String orderBy = sortField + " " + orderDirection;
        PageHelper.startPage(tipoCambioSearchDTO.getPage(), tipoCambioSearchDTO.getSize(), orderBy);
        PageInfo<TipoCambioResultDTO> pageInfo = new PageInfo<>(admTipoCambioMapper.selectByFecha(tipoCambioSearchDTO));

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

        pageInfo.getList().forEach(tipoCambio -> {
            try {
                String formattedDate = outputFormat.format(inputFormat.parse(tipoCambio.getFecha()));
                tipoCambio.setFecha(formattedDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return pageInfo;
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "FECHA";
        }
        switch (sort) {
            case "moneda":
                return "MONEDA";
            case "fuente":
                return "FUENTE";
            case "valCompra":
                return "VAL_COMPRA";
            case "valVenta":
                return "VAL_VENTA";
            case "fecha":
                return "FECHA";
            default:
                return "FECHA";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }
}
