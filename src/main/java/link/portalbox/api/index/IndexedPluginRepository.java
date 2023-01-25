package link.portalbox.api.index;

import link.portalbox.api.plugin.Plugin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface IndexedPluginRepository extends MongoRepository<IndexedPlugin, Integer> {

}
