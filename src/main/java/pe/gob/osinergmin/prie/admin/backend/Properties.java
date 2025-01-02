package pe.gob.osinergmin.prie.admin.backend;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sisseg")
@Getter
@Setter
public class Properties {
    private String keyUpdateUrl;
    private String validateAdUrl;
    private String keyUpdateApiKey;

    private String lenPwd;
    private String usuPwd;
    private String numPwd;
    private String lmiPwd;
    private String lmaPwd;
    private String hisPwd;
    private String symPwd;

    private String key;
    private String internalKey;

    private String textoPermitido;
    private String logstashUrl;

}
