package link.portalbox.api.index;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(origins = {"http://127.0.0.1:5173", "https://portalbox.link"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/index", produces = {MediaType.APPLICATION_JSON_VALUE})
public class IndexedPluginController {

    private HashMap<Integer, String> indexCache = new HashMap<>();
    private Gson gson = new Gson();

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.accepted().body(gson.toJson(indexCache));
    }

}
