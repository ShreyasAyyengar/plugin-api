package link.portalbox.api.plugin;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document(collection = "plugins")
public class Plugin {

    @Id
    private int id;
    private HashMap<String, Boolean> files = new HashMap<>(); // sha256 -> malware
    private String[] altDownloads = new String[]{};
    private int[] dependencies = new int[]{};

    public Plugin(int id) {
        this.id = id;
    }



}