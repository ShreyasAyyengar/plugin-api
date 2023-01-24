package link.portalbox.api.plugin;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PluginService {

    @Autowired
    private PluginRepository repository;

    public PluginService() {

    }

    public Optional<Plugin> fromId(int id) {
        return repository.findById(id);
    }

}
