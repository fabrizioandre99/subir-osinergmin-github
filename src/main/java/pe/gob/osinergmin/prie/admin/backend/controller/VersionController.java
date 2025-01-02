package pe.gob.osinergmin.prie.admin.backend.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osinergmin.prie.admin.backend.Properties;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;


@RestController
@Slf4j
public class VersionController {

    @Autowired
    private Properties properties;

    @PostMapping
    @RequestMapping(value = "version")
    public MessageDTO version() {
        try {
            return new MessageDTO("001", "ADMIN 1.0.0");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO("000", e.getMessage());
        }
    }
}
