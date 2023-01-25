package link.portalbox.api.index;

import com.google.gson.Gson;
import link.portalbox.api.plugin.Plugin;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://127.0.0.1:5173", "https://portalbox.link"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/index", produces = {MediaType.APPLICATION_JSON_VALUE})
public class IndexedPlugin {

    private HashMap<Integer, String> indexCache = new HashMap<>();
    private Gson gson = new Gson();

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.accepted().body(gson.toJson(indexCache));
    }

}
