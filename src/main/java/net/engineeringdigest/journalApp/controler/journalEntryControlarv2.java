package net.engineeringdigest.journalApp.controler;

import net.engineeringdigest.journalApp.UserService;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JornalEntryService;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class journalEntryControlarv2 {

    @Autowired
    private JornalEntryService jornalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<List<JournalEntry>> getJournalEntriesOfUsers(@PathVariable String useName) {
        User user = userService.findByUserName(useName);
        List<JournalEntry> entries = user.getJournalEntries();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntity,@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        jornalEntryService.saveJournalEntry(myEntity,userName);
        return new ResponseEntity<>(myEntity, HttpStatus.CREATED);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> entry = jornalEntryService.findById(myId);
        if (entry.isPresent()) {
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<Void> deleteJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> entry = jornalEntryService.findById(myId);
        if (entry.isPresent()) {
            jornalEntryService.deleteById(myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/id/{id}")
//    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntity) {
//        Optional<JournalEntry> oldEntityOptional = jornalEntryService.findById(id);
//        if (oldEntityOptional.isPresent()) {
//            JournalEntry oldEntity = oldEntityOptional.get();
//            oldEntity.setTitle(newEntity.getTitle() != null && !newEntity.getTitle().isEmpty() ? newEntity.getTitle() : oldEntity.getTitle());
//            oldEntity.setContent(newEntity.getContent() != null && !newEntity.getContent().isEmpty() ? newEntity.getContent() : oldEntity.getContent());
//            jornalEntryService.saveJournalEntry(oldEntity);
//            return new ResponseEntity<>(oldEntity, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/deleteInvalidEntries")
    public ResponseEntity<Void> deleteInvalidEntries() {
        jornalEntryService.deleteInvalidEntries();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}