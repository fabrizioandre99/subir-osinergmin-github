package pe.gob.osinergmin.prie.admin.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pe.gob.osinergmin.prie.admin.backend.Properties;

@RestController
@Slf4j
public class SecurityController {
    @Autowired
    private Properties properties;

    @PostMapping
    @RequestMapping(value = "security")
    public ModelAndView version(
            @RequestParam String taotlus,
            @RequestParam String keselamatan,
            @RequestParam String kasutaja,
            @RequestParam String lehele,
            @RequestParam("p_permisos") String permisos,
            @RequestParam("page") String page
    ) throws Exception {
        try {
            ModelAndView modelAndView = new ModelAndView("redirect:http://prie-mf-admin.cloudcomputing.com.pe/" + page);
            return modelAndView;
        }catch (Exception e) {
            throw e;
        }
    }
}
