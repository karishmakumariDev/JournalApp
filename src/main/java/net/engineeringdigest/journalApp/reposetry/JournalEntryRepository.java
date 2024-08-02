package net.engineeringdigest.journalApp.reposetry; // Corrected the package name

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    // Custom query methods can be defined here
}
