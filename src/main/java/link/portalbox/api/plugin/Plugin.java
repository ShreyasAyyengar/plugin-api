package link.portalbox.api.plugin;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Document(collection = "plugins")
public class Plugin {

    @Id
    private int id;
    private HashMap<String, Boolean> files = new HashMap<>(); // sha256 -> malware
    private List<String> altDownloads = new ArrayList<>();
    private List<Integer> dependencies = new ArrayList<>();

    public Plugin(int id) {
        this.id = id;
        altDownloads.add("test");
        files.put("test", false);
    }

    public int getId() { return id; }
    public HashMap<String, Boolean> getFiles() { return files; }
    public List<String> getAltDownloads() { return altDownloads; }
    public List<Integer> getDependencies() { return dependencies; }

    public void addFile(String sha256, boolean malware) { files.put(sha256, malware); }
    public void addAltDownload(String altDownload) { altDownloads.add(altDownload); }
    public void addDependency(int dependency) { dependencies.add(dependency); }

}