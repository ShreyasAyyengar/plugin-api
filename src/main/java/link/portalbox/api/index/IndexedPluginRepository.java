package link.portalbox.api.index;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexedPluginRepository extends MongoRepository<IndexedPluginController, Integer> {
}