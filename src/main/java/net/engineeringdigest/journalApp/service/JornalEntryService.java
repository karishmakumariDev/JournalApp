package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.UserService;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.reposetry.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JornalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveJournalEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }



    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

    public void deleteInvalidEntries() {
        List<JournalEntry> allEntries = journalEntryRepository.findAll();
        for (JournalEntry entry : allEntries) {
            if (entry.getTitle() == null || entry.getTitle().isEmpty()) {
                journalEntryRepository.deleteById(entry.getId());
            }
        }
    }
}
// controller ---> service ---> reposetry
