package link.grooverdev.web.api.demo.repository;

import link.grooverdev.web.api.demo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByEnabled(boolean enabled);
}
