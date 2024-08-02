package net.engineeringdigest.journalApp.reposetry; // Corrected the package name

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    // Custom query methods can be defined here
    User findByUserName(String userName);
}
