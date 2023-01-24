package link.portalbox.api.plugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://127.0.0.1:5173", "https://portalbox.link"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/plugins", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PluginController {

    @Autowired
    private PluginService service;

    @GetMapping
    public ResponseEntity<Object> account(@RequestParam(value = "id", required = false) Integer id) {
        return service.fromId(id)
                .map(Object.class::cast)
                .map(plugin -> ResponseEntity.accepted().body(plugin))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }



}
