package link.portalbox.api.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"http://127.0.0.1:5173", "https://portalbox.link"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/plugins", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PluginController {

    @Autowired
    private PluginRepository repository;

    private Gson gson = new Gson();

    @GetMapping("/{id}")
    public ResponseEntity<Object> fromId(@PathVariable("id") int id) {
        Optional<Plugin> plugin = repository.findById(id);
        return ResponseEntity.accepted().body(gson.toJson(plugin.get()));
    }

    @GetMapping("/file/{sha}")
    public ResponseEntity<Object> fromSha(@PathVariable("sha") String sha256) {
        Plugin plugin = /*repository.findOne();*/null;
        JsonObject obj = new JsonObject();
        obj.addProperty("id", plugin.getId());
        obj.addProperty("malware", plugin.getFiles().containsKey(sha256) ? plugin.getFiles().get(sha256) + "" : "null");
        return ResponseEntity.accepted().body(gson.toJson(obj));
    }



}
