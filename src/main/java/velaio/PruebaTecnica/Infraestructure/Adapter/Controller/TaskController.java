package velaio.PruebaTecnica.Infraestructure.Adapter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import velaio.PruebaTecnica.Application.Services.TaskService;
import velaio.PruebaTecnica.Infraestructure.Adapter.Request.PersonRequest;
import velaio.PruebaTecnica.Infraestructure.Adapter.Request.AddSkillsRequest;
import velaio.PruebaTecnica.Infraestructure.Adapter.Request.RemoveSkillsRequest;
import velaio.PruebaTecnica.Infraestructure.Adapter.Request.TaskRequest;
import velaio.PruebaTecnica.Infraestructure.Adapter.Response.TaskResponse;
import java.util.List;

@RestController
    @RequestMapping("/api/tasks")
    public class TaskController {


    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.createTask(taskRequest);
        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> listTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Void> markTaskAsCompleted(@PathVariable Long id) {
        taskService.markTaskAsCompleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TaskResponse>> filterTasksByStatus(@RequestParam boolean completed) {
        List<TaskResponse> tasks = taskService.filterTasksByStatus(completed);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/{taskId}/persons")
    public ResponseEntity<Void> addPersonToTask(@PathVariable Long taskId, @RequestBody PersonRequest addPersonRequest) {
        taskService.addPersonToTask(taskId, addPersonRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{taskId}/persons/{personId}")
    public ResponseEntity<Void> removePersonFromTask(@PathVariable Long taskId, @PathVariable Long personId) {
        taskService.removePersonFromTask(taskId, personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/persons/{personId}/skills")
    public ResponseEntity<Void> addSkillsToPerson(@PathVariable Long personId, @RequestBody AddSkillsRequest addSkillsRequest) {
        taskService.addSkillsToPerson(personId, addSkillsRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/persons/{personId}/skills")
    public ResponseEntity<Void> removeSkillsFromPerson(@PathVariable Long personId, @RequestBody RemoveSkillsRequest removeSkillsRequest) {
        taskService.removeSkillsFromPerson(personId, removeSkillsRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    }

