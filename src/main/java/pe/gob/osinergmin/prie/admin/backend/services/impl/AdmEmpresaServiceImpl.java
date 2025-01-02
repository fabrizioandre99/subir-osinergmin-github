package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmProcEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.*;
import pe.gob.osinergmin.prie.admin.backend.services.AdmEmpresaService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class AdmEmpresaServiceImpl implements AdmEmpresaService {

    @Autowired
    private AdmEmpresaMapper admEmpresaMapper;

    @Autowired
    private AdmProcEmpresaMapper admProcEmpresaMapper;

    @Autowired
    private AdmTransformadorMapper admTransformadorMapper;

    @Autowired
    private AdmCentralGeneracionMapper admCentralGeneracionMapper;

    @Autowired
    private AdmGrupoGeneracionMapper admGrupoGeneracionMapper;


    @Autowired
    private HttpServletRequest request;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codEmpresa) {
        try {
            AdmEmpresa existe = admEmpresaMapper.selectByPrimaryKey(codEmpresa);
            if (existe != null) {
                int countTransformador = admTransformadorMapper.countByCodEmpresa(codEmpresa);
                if (countTransformador > 0) {
                    admTransformadorMapper.deleteByCodEmpresa(codEmpresa);
                }

                List<AdmProcEmpresaDTO> procesos = admProcEmpresaMapper.listarPorCodEmpresa(codEmpresa);
                if (procesos != null && !procesos.isEmpty()) {
                    for (AdmProcEmpresaDTO proceso : procesos) {
                        admProcEmpresaMapper.deleteByIdProcEmpresa(proceso.getIdProcEmpresa());
                    }
                }

                List<AdmCentralGeneracionDTO> centrales = admCentralGeneracionMapper.selectByCodEmpresa(codEmpresa);
                if (centrales != null && !centrales.isEmpty()) {
                    for (AdmCentralGeneracionDTO central : centrales) {
                        admGrupoGeneracionMapper.eliminarCodCentral(central.getCodCentralGeneracion());
                        admCentralGeneracionMapper.deleteByPrimaryKey(central.getCodCentralGeneracion());
                    }
                }

                admEmpresaMapper.deleteByPrimaryKey(codEmpresa);
                return new MessageDTO(Constantes.SUCCES, "Empresa eliminada correctamente");
            } else {
                return new MessageDTO(Constantes.ERROR, "El Código de la empresa no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al eliminar la empresa: " + e.getMessage());
        }
    }


    @Transactional
    @Override
    public MessageDTO insert(AdmEmpresaFormDTO admEmpresaFormDTO) throws UnknownHostException {
        try {
            AdmEmpresa existe = admEmpresaMapper.selectByPrimaryKey(admEmpresaFormDTO.getCodEmpresa());

            int existeRuc = admEmpresaMapper.existeRuc(admEmpresaFormDTO.getRuc());

            if (existeRuc != 0) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el RUC");
            }

            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el Código Empresa");
            } else {
                AdmEmpresa admEmpresa = new AdmEmpresa();
                admEmpresa.setCodEmpresa(admEmpresaFormDTO.getCodEmpresa());
                admEmpresa.setDscCortaEmpresa(admEmpresaFormDTO.getDscCortaEmpresa());
                admEmpresa.setDscEmpresa(admEmpresaFormDTO.getDscEmpresa());
                admEmpresa.setRuc(admEmpresaFormDTO.getRuc());
                admEmpresa.setDireccion(admEmpresaFormDTO.getDireccion());
                admEmpresa.setCodUbigeo(admEmpresaFormDTO.getCodUbigeo());
                admEmpresa.setEstado(admEmpresaFormDTO.getEstado());
                String ipCreacion = InetAddress.getLocalHost().getHostAddress();
                admEmpresa.setIpCreacion(ipCreacion);
                admEmpresa.setAdFecha(new Date());
                admEmpresaMapper.insert(admEmpresa);

                return new MessageDTO(Constantes.SUCCES, "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int insertSelective(AdmEmpresa record) {
        return admEmpresaMapper.insertSelective(record);
    }

    @Override
    public AdmEmpresa selectByPrimaryKey(String codEmpresa) {
        return admEmpresaMapper.selectByPrimaryKey(codEmpresa);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmEmpresa record) {
        return admEmpresaMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmEmpresaFormDTO admEmpresaFormDTO) throws UnknownHostException {
        try {
            AdmEmpresa existe = admEmpresaMapper.selectByPrimaryKey(admEmpresaFormDTO.getCodEmpresa());

            if (existe != null) {
                AdmEmpresa admEmpresa = new AdmEmpresa();
                admEmpresa.setCodEmpresa(admEmpresaFormDTO.getCodEmpresa());
                admEmpresa.setDscCortaEmpresa(admEmpresaFormDTO.getDscCortaEmpresa());
                admEmpresa.setDscEmpresa(admEmpresaFormDTO.getDscEmpresa());
                admEmpresa.setRuc(admEmpresaFormDTO.getRuc());
                admEmpresa.setDireccion(admEmpresaFormDTO.getDireccion());
                admEmpresa.setCodUbigeo(admEmpresaFormDTO.getCodUbigeo());
                admEmpresa.setEstado(admEmpresaFormDTO.getEstado());

                String ipCreacion = InetAddress.getLocalHost().getHostAddress();

                admEmpresa.setIpCreacion(ipCreacion);
                admEmpresa.setAdFecha(new Date());
                admEmpresaMapper.updateByPrimaryKey(admEmpresa);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No Existe el Código de la empresa");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<AdmEmpresaDTO> selectAll() {
        return admEmpresaMapper.selectAll();
    }

    @Override
    public PageInfo<AdmEmpresaDTO> listarFiltro(AdmEmpresaSearchDTO searchDTO) {
        try {

            String sortField = mapSortField(searchDTO.getSort());
            String orderDirection = validateOrder(searchDTO.getOrder());
            String orderBy = sortField + " " + orderDirection;

            PageHelper.startPage(searchDTO.getPage(), searchDTO.getSize(), orderBy);
            List<AdmEmpresaDTO> empresas = admEmpresaMapper.ListarFiltro(searchDTO);

            if ("procesosAsociados".equalsIgnoreCase(searchDTO.getSort())) {
                empresas.sort((e1, e2) -> {
                    int size1 = e1.getProcesosAsociados() != null ? e1.getProcesosAsociados().size() : 0;
                    int size2 = e2.getProcesosAsociados() != null ? e2.getProcesosAsociados().size() : 0;
                    return "asc".equalsIgnoreCase(orderDirection) ? Integer.compare(size1, size2) : Integer.compare(size2, size1);
                });
            }

            return new PageInfo<>(empresas);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "COD_EMPRESA";
        }
        switch (sort) {
            case "codEmpresa":
                return "COD_EMPRESA";
            case "dscEmpresa":
                return "DSC_EMPRESA";
            case "ruc":
                return "RUC";
            case "estado":
                return "ESTADO";
            case "procesosAsociados":
                return "COUNT_PROCESOS";
            default:
                return "COD_EMPRESA";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }


    @Override
    @Transactional
    public MessageDTO insertNuevo(AdmEmpresaFormDTO empresaFormDTO) {
        try {
            AdmEmpresaDTO existente = admEmpresaMapper.selectByCodEmpresa(empresaFormDTO.getCodEmpresa());
            if (existente != null) {
                return new MessageDTO(Constantes.ERROR, "El código de empresa ya existe. Por favor, use un código único.");
            }
            empresaFormDTO.setAdCodUsuario("admin");
            empresaFormDTO.setAdFecha(new Date());
            empresaFormDTO.setIpCreacion(IpUtils.getClientIp(request));
            admEmpresaMapper.insertEmpresa(empresaFormDTO);

            if (empresaFormDTO.getProcesos() != null && !empresaFormDTO.getProcesos().isEmpty()) {
                for (AdmProcEmpresaDTO proceso : empresaFormDTO.getProcesos()) {
                    proceso.setCodEmpresa(empresaFormDTO.getCodEmpresa());
                    proceso.setAdCodUsuario("admin");

                    Integer maxId = admEmpresaMapper.obtenerMaximoIdProcEmpresa();
                    proceso.setIdProcEmpresa(maxId != null ? maxId + 1 : 1);

                    admEmpresaMapper.insertProcEmpresa(proceso);
                }
            }

            return new MessageDTO(Constantes.SUCCES, "OK");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al crear la empresa: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public MessageDTO updateNuevo(AdmEmpresaFormDTO empresaFormDTO) {
        try {
            AdmEmpresaDTO existente = admEmpresaMapper.selectByCodEmpresa(empresaFormDTO.getCodEmpresa());

            if (existente == null) {
                return new MessageDTO(Constantes.ERROR, "La empresa no fue encontrada para actualizar.");
            }

            AdmEmpresa empresa = new AdmEmpresa();
            empresa.setCodEmpresa(empresaFormDTO.getCodEmpresa());
            empresa.setDscCortaEmpresa(empresaFormDTO.getDscCortaEmpresa());
            empresa.setDscEmpresa(empresaFormDTO.getDscEmpresa());
            empresa.setRuc(empresaFormDTO.getRuc());
            empresa.setDireccion(empresaFormDTO.getDireccion());
            empresa.setCodUbigeo(empresaFormDTO.getCodUbigeo());
            empresa.setEstado(empresaFormDTO.getEstado());
            empresa.setAdFecha(new Date());
            empresa.setUsActualizacion("admin");
            empresa.setIpActualizacion(IpUtils.getClientIp(request));
            empresa.setFeActualizacion(new Date());

            admEmpresaMapper.updateByPrimaryKeySelective(empresa);

            List<AdmProcEmpresaDTO> procesosExistentes = admProcEmpresaMapper.listarPorCodEmpresaFunTipo(empresaFormDTO.getCodEmpresa());

            Map<Integer, AdmProcEmpresaDTO> mapaProcesosExistentes = procesosExistentes.stream()
                    .collect(Collectors.toMap(
                            AdmProcEmpresaDTO::getIdProcEmpresa,
                            p -> p
                    ));

            for (AdmProcEmpresaDTO procesoDTO : empresaFormDTO.getProcesos()) {
                procesoDTO.setCodEmpresa(empresaFormDTO.getCodEmpresa());
                procesoDTO.setAdCodUsuario("admin");

                if (procesoDTO.getIdProcEmpresa() != null && mapaProcesosExistentes.containsKey(procesoDTO.getIdProcEmpresa())) {
                    procesoDTO.setAdFecha(new Date());
                    admProcEmpresaMapper.updateByPrimaryKeySelective(procesoDTO);
                    mapaProcesosExistentes.remove(procesoDTO.getIdProcEmpresa());
                } else {
                    Integer maxId = admEmpresaMapper.obtenerMaximoIdProcEmpresa();
                    procesoDTO.setIdProcEmpresa(maxId != null ? maxId + 1 : 1);
                    admEmpresaMapper.insertProcEmpresa(procesoDTO);
                }
            }

            for (AdmProcEmpresaDTO procesoNoEnviado : mapaProcesosExistentes.values()) {
                if (procesoNoEnviado.getIdProcEmpresa() != null) {
                    admProcEmpresaMapper.deleteByIdProcEmpresa(procesoNoEnviado.getIdProcEmpresa());
                } else {
                    System.err.println("No se puede eliminar el proceso sin idProcEmpresa: " + procesoNoEnviado);
                }
            }

            return new MessageDTO(Constantes.SUCCES, "OK");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al actualizar la empresa: " + e.getMessage());
        }
    }


}
