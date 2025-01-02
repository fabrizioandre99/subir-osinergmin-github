package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreValidarRUC;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmUsuarioLibreMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUsuarioLibre;
import pe.gob.osinergmin.prie.admin.backend.services.AdmUsuarioLibreService;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.ssl.TrustStrategy;
import java.security.cert.X509Certificate;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.List;

@Service
public class AdmUsuarioLibreServiceImpl implements AdmUsuarioLibreService {

    @Autowired
    private AdmUsuarioLibreMapper admUsuarioLibreMapper;

    @Autowired
    private HttpServletRequest request;

    @Value("${ws.consultaRuc}")
    private String urlBase;

    @Value("${ws.usuario}")
    private String usuario;

    @Value("${ws.contrasena}")
    private String contrasena;

    @Value("${ws.organizacion}")
    private String organizacion;

    @Value("${ws.canal}")
    private String canal;

    @Value("${ws.sistema}")
    private String sistema;

    @Value("${ws.modulo}")
    private String modulo;

    @Transactional
    @Override
    public MessageDTO insert(AdmUsuarioLibreFormDTO record) {
        try {
            if (record.getCodUsuarioLibre() == null || record.getCodUsuarioLibre().length() > 11) {
                return new MessageDTO(Constantes.ERROR, "El código del usuario libre no puede tener más de 11 caracteres.");
            }
            if (record.getRazonSocial() != null && record.getRazonSocial().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La razón social no puede tener más de 100 caracteres.");
            }
            if (record.getDireccion() != null && record.getDireccion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La dirección no puede tener más de 100 caracteres.");
            }
            if (record.getTelefono() != null && record.getTelefono().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El teléfono no puede tener más de 50 caracteres.");
            }
            if (record.getNombreRepLegal() != null && record.getNombreRepLegal().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El nombre del representante legal no puede tener más de 50 caracteres.");
            }
            if (record.getCargoRepLeg() != null && record.getCargoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El cargo del representante legal no puede tener más de 50 caracteres.");
            }
            if (record.getTelefonoRepLeg() != null && record.getTelefonoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El teléfono del representante legal no puede tener más de 50 caracteres.");
            }
            if (record.getCorreoRepLeg() != null && record.getCorreoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El correo del representante legal no puede tener más de 50 caracteres.");
            }

            AdmUsuarioLibre existe = admUsuarioLibreMapper.selectByPrimaryKey(record.getCodUsuarioLibre());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe.");
            }

            AdmUsuarioLibre admUsuarioLibre = new AdmUsuarioLibre();
            admUsuarioLibre.setCodUsuarioLibre(record.getCodUsuarioLibre());
            admUsuarioLibre.setRazonSocial(record.getRazonSocial());
            admUsuarioLibre.setDireccion(record.getDireccion());
            admUsuarioLibre.setTelefono(record.getTelefono());
            admUsuarioLibre.setNombreRepLegal(record.getNombreRepLegal());
            admUsuarioLibre.setCargoRepLeg(record.getCargoRepLeg());
            admUsuarioLibre.setTelefonoRepLeg(record.getTelefonoRepLeg());
            admUsuarioLibre.setCorreoRepLeg(record.getCorreoRepLeg());
            admUsuarioLibre.setEsGranCliente(record.getEsGranCliente());
            admUsuarioLibreMapper.insert(admUsuarioLibre);

            return new MessageDTO(Constantes.SUCCES, "Se insertó correctamente el usuario libre.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmUsuarioLibre record) {
        return admUsuarioLibreMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmUsuarioLibreFormDTO record) {
        try {
            if (record.getCodUsuarioLibre() == null || record.getCodUsuarioLibre().length() > 11) {
                return new MessageDTO(Constantes.ERROR, "El código del usuario libre no puede tener más de 11 caracteres.");
            }
            if (record.getRazonSocial() != null && record.getRazonSocial().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La razón social no puede tener más de 100 caracteres.");
            }
            if (record.getDireccion() != null && record.getDireccion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La dirección no puede tener más de 100 caracteres.");
            }
            if (record.getTelefono() != null && record.getTelefono().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El teléfono no puede tener más de 50 caracteres.");
            }
            if (record.getNombreRepLegal() != null && record.getNombreRepLegal().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El nombre del representante legal no puede tener más de 50 caracteres.");
            }
            if (record.getCargoRepLeg() != null && record.getCargoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El cargo del representante legal no puede tener más de 50 caracteres.");
            }
            if (record.getTelefonoRepLeg() != null && record.getTelefonoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El teléfono del representante legal no puede tener más de 50 caracteres.");
            }
            if (record.getCorreoRepLeg() != null && record.getCorreoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El correo del representante legal no puede tener más de 50 caracteres.");
            }

            AdmUsuarioLibre existe = admUsuarioLibreMapper.selectByPrimaryKey(record.getCodUsuarioLibre());
            if (existe != null) {
                AdmUsuarioLibre admUsuarioLibre = new AdmUsuarioLibre();
                admUsuarioLibre.setCodUsuarioLibre(record.getCodUsuarioLibre());
                admUsuarioLibre.setRazonSocial(record.getRazonSocial());
                admUsuarioLibre.setDireccion(record.getDireccion());
                admUsuarioLibre.setTelefono(record.getTelefono());
                admUsuarioLibre.setNombreRepLegal(record.getNombreRepLegal());
                admUsuarioLibre.setCargoRepLeg(record.getCargoRepLeg());
                admUsuarioLibre.setTelefonoRepLeg(record.getTelefonoRepLeg());
                admUsuarioLibre.setCorreoRepLeg(record.getCorreoRepLeg());
                admUsuarioLibre.setEsGranCliente(record.getEsGranCliente());
                admUsuarioLibreMapper.updateByPrimaryKey(admUsuarioLibre);

                return new MessageDTO(Constantes.SUCCES, "Se actualizó correctamente el usuario libre.");
            }

            return new MessageDTO(Constantes.ERROR, "No se encontró el usuario libre para actualizar.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codUsuarioLibre) {
        try {
            AdmUsuarioLibre existe = admUsuarioLibreMapper.selectByPrimaryKey(codUsuarioLibre);
            if (existe != null) {
                admUsuarioLibreMapper.deleteByPrimaryKey(codUsuarioLibre);
                return new MessageDTO(Constantes.SUCCES, "Se eliminó el usuario libre");
            }
            return new MessageDTO(Constantes.ERROR, "No se encontró el usuario libre para eliminar");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmUsuarioLibreDTO> selectAll() {
        return admUsuarioLibreMapper.selectAll();
    }

    @Transactional
    @Override
    public PageInfo<AdmUsuarioLibreDTO> filtrar(AdmUsuarioLibreSearchDTO admUsuarioLibreSearchDTO) {
        String sortField = mapSortField(admUsuarioLibreSearchDTO.getSort());
        String order = (admUsuarioLibreSearchDTO.getOrder() != null && !admUsuarioLibreSearchDTO.getOrder().isEmpty())
                ? admUsuarioLibreSearchDTO.getOrder()
                : "ASC";

        if (admUsuarioLibreSearchDTO.getCodUsuarioLibre() != null) {
            admUsuarioLibreSearchDTO.setCodUsuarioLibre(
                    admUsuarioLibreSearchDTO.getCodUsuarioLibre().toUpperCase().replaceAll("[ÁÉÍÓÚ]", "AEIOU")
            );
        }

        if (admUsuarioLibreSearchDTO.getRazonSocial() != null) {
            admUsuarioLibreSearchDTO.setRazonSocial(
                    admUsuarioLibreSearchDTO.getRazonSocial().toUpperCase().replaceAll("[ÁÉÍÓÚ]", "AEIOU")
            );
        }

        PageHelper.startPage(admUsuarioLibreSearchDTO.getPage(), admUsuarioLibreSearchDTO.getSize(), sortField + " " + order);
        return new PageInfo<>(admUsuarioLibreMapper.filtrar(admUsuarioLibreSearchDTO));
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "COD_USUARIO_LIBRE";
        }
        switch (sort) {
            case "codUsuarioLibre":
                return "COD_USUARIO_LIBRE";
            case "razonSocial":
                return "RAZON_SOCIAL";
            case "direccion":
                return "DIRECCION";
            case "telefono":
                return "TELEFONO";
            case "nombreRepLegal":
                return "NOMBRE_REP_LEGAL";
            default:
                return "COD_USUARIO_LIBRE";
        }
    }

    @Override
    public AdmUsuarioLibreValidarRUC validarSUNAT(String codUsuarioLibre) throws Exception {
//        if (codUsuarioLibre == null || codUsuarioLibre.trim().length() != 11) {
//            throw new Exception("Debe ingresar un valor de RUC válido de 11 dígitos.");
//        }
//        String soapRequest = buildSoapRequest(codUsuarioLibre);
//
//        String soapResponse = sendSoapRequest(soapRequest);
//
//        AdmUsuarioLibreValidarRUC data = parseSoapResponse(soapResponse, codUsuarioLibre);
//
//        return data;

        //VALORES EN DURO - ELIMINAR LUEGO DE DESPLIEGUE
        if (codUsuarioLibre == null || codUsuarioLibre.trim().length() != 11) {
          throw new Exception("Debe ingresar un valor de RUC válido de 11 dígitos.");
        }
        AdmUsuarioLibreValidarRUC admUsuarioLibreValidarRUC = new AdmUsuarioLibreValidarRUC();
        admUsuarioLibreValidarRUC.setRazonSocial("Empresa de Prueba");
        admUsuarioLibreValidarRUC.setDireccion("Dirección de prueba");
        return admUsuarioLibreValidarRUC;
    }

    private String buildSoapRequest(String ruc) {
        String ip = request.getRemoteAddr();

        String soapRequest = String.format(
                "<Envelope xmlns=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "    <Header>\n" +
                        "        <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\n" +
                        "         <wsse:UsernameToken wsu:Id=\"Id-0001427873415141-0000000076fdd541-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
                        "            <wsse:Username>%s</wsse:Username>\n" +
                        "            <wsse:Password>%s</wsse:Password>\n" +
                        "         </wsse:UsernameToken>\n" +
                        "      </wsse:Security>\n" +
                        "        <consumidor xmlns=\"http://soa.osinergmin.gob.pe/schema/comun/consumidor\">\n" +
                        "            <organizacion>%s</organizacion>\n" +
                        "            <canal>%s</canal>\n" +
                        "            <sistema>%s</sistema>\n" +
                        "            <modulo>%s</modulo>\n" +
                        "            <usuario>%s</usuario>\n" +
                        "            <ip>%s</ip>\n" +
                        "        </consumidor>\n" +
                        "    </Header>\n" +
                        "    <Body>\n" +
                        "        <ContribuyentesConsultarReqParam xmlns=\"http://soa.osinergmin.org.pe/schema/consultarucdatosprincipales/contribuyentes/consultar/1.0\">\n" +
                        "            <numRuc>%s</numRuc>\n" +
                        "        </ContribuyentesConsultarReqParam>\n" +
                        "    </Body>\n" +
                        "</Envelope>",
                usuario, contrasena, organizacion, canal, sistema, modulo, usuario, ip, ruc
        );

        return soapRequest;
    }

    private String sendSoapRequest(String soapRequest) throws Exception {
        String url = urlBase + "/consultarucdatosprincipales/contribuyentes/consultar/1.1?wsdl";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", "ContribuyentesConsultar");

        HttpEntity<String> requestEntity = new HttpEntity<>(soapRequest, headers);

        RestTemplate restTemplate = createRestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Error al obtener respuesta del servicio web de SUNAT");
        }

        return response.getBody();
    }

    private RestTemplate createRestTemplate() throws Exception {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory socketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslContext)
                .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();

        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(socketFactory)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setProxy(null)
                .build();


        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        System.out.println("http.proxyHost: " + System.getProperty("http.proxyHost"));
        System.out.println("http.proxyPort: " + System.getProperty("http.proxyPort"));
        System.out.println("https.proxyHost: " + System.getProperty("https.proxyHost"));
        System.out.println("https.proxyPort: " + System.getProperty("https.proxyPort"));

        return new RestTemplate(requestFactory);
    }



    private AdmUsuarioLibreValidarRUC parseSoapResponse(String soapResponse, String codUsuarioLibre) throws Exception {
        AdmUsuarioLibreValidarRUC data = new AdmUsuarioLibreValidarRUC();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(soapResponse)));

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        String ddpNombre = xpath.evaluate("//*[local-name()='ddp_nombre']", doc);
        String ddpNomzon = xpath.evaluate("//*[local-name()='ddp_nomzon']", doc);
        String ddpNomvia = xpath.evaluate("//*[local-name()='ddp_nomvia']", doc);
        String ddpNumer1 = xpath.evaluate("//*[local-name()='ddp_numer1']", doc);
        String descDep = xpath.evaluate("//*[local-name()='desc_dep']", doc);
        String descProv = xpath.evaluate("//*[local-name()='desc_prov']", doc);
        String descDist = xpath.evaluate("//*[local-name()='desc_dist']", doc);

        if (ddpNombre != null && !ddpNombre.isEmpty()) {
            data.setCodUsuarioLibre(codUsuarioLibre);
            data.setRazonSocial(ddpNombre);
            String direccion = String.format("Zona: %s, v: %s, nro: %s %s-%s-%s",
                    ddpNomzon, ddpNomvia, ddpNumer1, descDep, descProv, descDist);
            data.setDireccion(direccion);
        } else {
            throw new Exception("No se encontraron registros para el RUC proporcionado.");
        }

        return data;
    }


}
