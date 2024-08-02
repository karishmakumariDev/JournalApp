package net.engineeringdigest.journalApp.controler;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.ToDo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todoist")
public class ToDoControler {

    private Map<Long, ToDo> todoistEntities = new HashMap<>();
    public ToDoControler() {
    }

    @GetMapping("/getAlltodoist")
    public List<ToDo> getAll() {
        return new ArrayList<>(todoistEntities.values());
    }

    @PostMapping("/addtodo")
    public List<ToDo> createEntry(@RequestBody ToDo myTodo) {
        todoistEntities.put(myTodo.getId(), myTodo);
        return new ArrayList<>(todoistEntities.values());
    }

    @GetMapping("/id/{myId}")
    public ToDo getTodobyId(@PathVariable long myId) {
        return todoistEntities.get(myId);
    }

    @DeleteMapping ("/id/{myId}")
    public List<ToDo> deletejournalEntryId(@PathVariable long myId) {
        todoistEntities.remove(myId);
        return new ArrayList<>(todoistEntities.values());
    }

    @PutMapping("/id/{myId}")
    public ArrayList<ToDo> updateEntry(@PathVariable long myId, @RequestBody ToDo updatedToDo) {
        updatedToDo.setId(myId);
        todoistEntities.put(myId, updatedToDo);
        return new ArrayList<>(todoistEntities.values());
    }

    @PatchMapping("/id/{myId}")
    public ArrayList<ToDo> setTaskComplete(@PathVariable long myId, @RequestBody boolean completeValue) {
        ToDo existingToDo = todoistEntities.get(myId);
        // {id: 123, taskName: read a book, complete: true }

        if (existingToDo != null) {
            existingToDo.setComplete(completeValue);
            todoistEntities.put(myId, existingToDo);
        }
        return new ArrayList<>(todoistEntities.values());
    }

}
