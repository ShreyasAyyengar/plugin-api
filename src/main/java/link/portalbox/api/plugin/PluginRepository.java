package link.portalbox.api.plugin;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface PluginRepository extends MongoRepository<Plugin, Integer> {




}
