package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoBarra;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.CodBarraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.*;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmGrupoBarraMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmGrupoBarraService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdmGrupoBarraServiceImpl implements AdmGrupoBarraService{

    @Autowired
    private AdmGrupoBarraMapper admGrupoBarraMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public int deleteByPrimaryKey(String codBarraGrupo,String codBarra) {
        return admGrupoBarraMapper.deleteByPrimaryKey(codBarraGrupo,codBarra);
    }

    @Override
    public int insert(AdmGrupoBarra record) {
        return admGrupoBarraMapper.insert(record);
    }

    @Override
    public int insertSelective(AdmGrupoBarra record) {
        return admGrupoBarraMapper.insertSelective(record);
    }


    @Override
    public int updateByPrimaryKeySelective(AdmGrupoBarra record) {
        return admGrupoBarraMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdmGrupoBarra record) {
        return admGrupoBarraMapper.updateByPrimaryKey(record);
    }

    @Transactional
    @Override
    public MessageDTO insertGrupoBarra(AdmGrupoBarraformDTO admGrupoBarraformDTO) {
        try {
            if (admGrupoBarraformDTO.getCodBarraGrupo() == null || admGrupoBarraformDTO.getCodBarraGrupo().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del grupo de barra es requerido.");
            }
            if (admGrupoBarraformDTO.getEstado() == null || admGrupoBarraformDTO.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado es requerido.");
            }
            if (!admGrupoBarraformDTO.getEstado().equals("1") && !admGrupoBarraformDTO.getEstado().equals("0")) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser '1' (Habilitado) o '0' (Deshabilitado).");
            }
            if (admGrupoBarraformDTO.getCodBarras() == null || admGrupoBarraformDTO.getCodBarras().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "Se requiere al menos un código de barra.");
            }

            int count = admGrupoBarraMapper.countByCodBarraGrupo(admGrupoBarraformDTO.getCodBarraGrupo());
            if (count > 0) {
                return new MessageDTO(Constantes.ERROR, "El código de grupo de barra ya existe.");
            }

            String usuarioCreacion = "adminUser";
            String terminalCreacion = IpUtils.getClientIp(request);
            Date fechaCreacion = new Date();

            List<CodBarraDTO> codBarras = admGrupoBarraformDTO.getCodBarras();
            for (CodBarraDTO codBarra : codBarras) {
                Map<String, Object> params = new HashMap<>();
                params.put("codBarraGrupo", admGrupoBarraformDTO.getCodBarraGrupo());
                params.put("codBarra", codBarra.getCodBarra());
                params.put("estado", admGrupoBarraformDTO.getEstado());
                params.put("usuarioCreacion", usuarioCreacion);
                params.put("terminalCreacion", terminalCreacion);
                params.put("fechaCreacion", fechaCreacion);
                admGrupoBarraMapper.insertGrupoBarra(params);
            }

            return new MessageDTO(Constantes.SUCCES, "Grupo de Barra creado correctamente.");
        } catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "Excepción al crear el Grupo de Barra: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO actualizarGrupoBarra(AdmGrupoBarraformDTO admGrupoBarraformDTO) {
        try {
            if (admGrupoBarraformDTO.getCodBarraGrupo() == null || admGrupoBarraformDTO.getCodBarraGrupo().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del grupo de barra es requerido.");
            }
            if (admGrupoBarraformDTO.getEstado() == null || admGrupoBarraformDTO.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado es requerido.");
            }
            if (!admGrupoBarraformDTO.getEstado().equals("1") && !admGrupoBarraformDTO.getEstado().equals("0")) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser '1' (Habilitado) o '0' (Deshabilitado).");
            }

            int count = admGrupoBarraMapper.countByCodBarraGrupo(admGrupoBarraformDTO.getCodBarraGrupo());
            if (count == 0) {
                return new MessageDTO(Constantes.ERROR, "El Grupo de Barra no existe.");
            }

            admGrupoBarraMapper.updateEstadoGrupo(admGrupoBarraformDTO.getCodBarraGrupo(), admGrupoBarraformDTO.getEstado());
            admGrupoBarraMapper.deleteCodBarra(admGrupoBarraformDTO.getCodBarraGrupo());

            String usuarioCreacion = "adminUser";
            String terminalCreacion = IpUtils.getClientIp(request);
            Date fechaCreacion = new Date();

            List<CodBarraDTO> codBarras = admGrupoBarraformDTO.getCodBarras();
            if (codBarras != null && !codBarras.isEmpty()) {
                for (CodBarraDTO codBarra : codBarras) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("codBarraGrupo", admGrupoBarraformDTO.getCodBarraGrupo());
                    params.put("codBarra", codBarra.getCodBarra());
                    params.put("estado", admGrupoBarraformDTO.getEstado());
                    params.put("usuarioCreacion", usuarioCreacion);
                    params.put("terminalCreacion", terminalCreacion);
                    params.put("fechaCreacion", fechaCreacion);
                    admGrupoBarraMapper.insertGrupoBarra(params);
                }
            } else {
                Map<String, Object> params = new HashMap<>();
                params.put("codBarraGrupo", admGrupoBarraformDTO.getCodBarraGrupo());
                params.put("codBarra", "NO_BAR");
                params.put("estado", admGrupoBarraformDTO.getEstado());
                params.put("usuarioCreacion", usuarioCreacion);
                params.put("terminalCreacion", terminalCreacion);
                params.put("fechaCreacion", fechaCreacion);
                admGrupoBarraMapper.insertGrupoBarra(params);
            }

            return new MessageDTO(Constantes.SUCCES, "Grupo de Barra actualizado correctamente.");
        } catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "Excepción al actualizar el Grupo de Barra: " + e.getMessage());
        }
    }


    @Override
    public MessageDTO eliminarGrupoBarra(String codBarra) {
       try {
           int existe = admGrupoBarraMapper.deleteCodBarra(codBarra);
           if (existe > 0) {
               return new MessageDTO(Constantes.SUCCES,"Grupo de Barra eliminado correctamente.");
           }
           return new MessageDTO(Constantes.ERROR, "El Grupo de Barra no existe");
       }catch (Exception e) {
           e.printStackTrace();
           throw e;
       }
    }

    @Override
    public List<GrupoBarraResponseDTO> listarGruposDeBarras() {
        return admGrupoBarraMapper.listarGruposDeBarras();
    }


    public PageInfo<GrupoBarraResponseDTO> filtrar(AdmGrupoBarraSearchDTO admGrupoBarraSearchDTO) {
        try {

            List<GrupoBarraFlatDTO> flatList = admGrupoBarraMapper.filtrar(admGrupoBarraSearchDTO);

            Map<String, GrupoBarraResponseDTO> grouped = new LinkedHashMap<>();
            for (GrupoBarraFlatDTO dto : flatList) {
                String codigo = dto.getCodigo();
                GrupoBarraResponseDTO group = grouped.get(codigo);
                if (group == null) {
                    group = new GrupoBarraResponseDTO();
                    group.setCodigo(codigo);
                    group.setEstado(dto.getEstado());
                    group.setBarras(new ArrayList<>());
                    grouped.put(codigo, group);
                }
                if (dto.getCodBarra() != null) {
                    BarraDTO barra = new BarraDTO();
                    barra.setCodBarra(dto.getCodBarra());
                    barra.setNomBarra(dto.getNomBarra());
                    group.getBarras().add(barra);
                }
            }

            List<GrupoBarraResponseDTO> groupedList = new ArrayList<>(grouped.values());

            String sortField = admGrupoBarraSearchDTO.getSort();
            String order = admGrupoBarraSearchDTO.getOrder();

            Comparator<GrupoBarraResponseDTO> comparator;
            if ("estado".equalsIgnoreCase(sortField)) {
                comparator = Comparator.comparing(GrupoBarraResponseDTO::getEstado);
            } else {
                comparator = Comparator.comparing(GrupoBarraResponseDTO::getCodigo);
            }
            if ("DESC".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }
            groupedList.sort(comparator);

            int page = admGrupoBarraSearchDTO.getPage();
            int size = admGrupoBarraSearchDTO.getSize();
            int total = groupedList.size();
            int fromIndex = (page - 1) * size;
            int toIndex = Math.min(fromIndex + size, total);

            List<GrupoBarraResponseDTO> pageList = new ArrayList<>();
            if (fromIndex < total) {
                pageList = groupedList.subList(fromIndex, toIndex);
            }

            Page<GrupoBarraResponseDTO> pageData = new Page<>(page, size);
            pageData.setTotal(total);
            pageData.setPages((total + size - 1) / size);
            pageData.addAll(pageList);

            PageInfo<GrupoBarraResponseDTO> pageInfo = new PageInfo<>(pageData, 8);

            return pageInfo;

        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }


}
