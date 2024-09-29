package velaio.PruebaTecnica.Application.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import velaio.PruebaTecnica.Domain.Exception.ResourceNotFoundException;
import velaio.PruebaTecnica.Domain.Model.Person;
import velaio.PruebaTecnica.Domain.Model.Task;
import velaio.PruebaTecnica.Infraestructure.Adapter.Request.PersonRequest;
import velaio.PruebaTecnica.Infraestructure.Adapter.Request.AddSkillsRequest;
import velaio.PruebaTecnica.Infraestructure.Adapter.Request.RemoveSkillsRequest;
import velaio.PruebaTecnica.Infraestructure.Adapter.Request.TaskRequest;
import velaio.PruebaTecnica.Infraestructure.Adapter.Response.PersonResponse;
import velaio.PruebaTecnica.Infraestructure.Adapter.Response.TaskResponse;
import velaio.PruebaTecnica.Infraestructure.Persistence.Repository.PersonRepository;
import velaio.PruebaTecnica.Infraestructure.Persistence.Repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;


    public TaskResponse createTask(TaskRequest request) {
        Task task = modelMapper.map(request, Task.class);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskResponse.class);
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskResponse.class))
                .collect(Collectors.toList());
    }

    public TaskResponse markTaskAsCompleted(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));
        task.setCompleted(true);
        Task updatedTask = taskRepository.save(task);
        return modelMapper.map(updatedTask, TaskResponse.class);
    }

    public List<TaskResponse> filterTasksByStatus(boolean isCompleted) {
        List<Task> tasks = taskRepository.findByCompleted(isCompleted);
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskResponse.class))
                .collect(Collectors.toList());
    }

    public TaskResponse addPersonToTask(Long taskId, PersonRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

        Person person = modelMapper.map(request, Person.class);
        person = personRepository.save(person);
        task.getPersons().add(person);
        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskResponse.class);
    }

    public TaskResponse removePersonFromTask(Long taskId, Long personId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + personId));

        task.getPersons().remove(person);
        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskResponse.class);
    }

    public PersonResponse addSkillsToPerson(Long personId, AddSkillsRequest request) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + personId));

        person.getSkills().addAll(request.getSkills());
        Person updatedPerson = personRepository.save(person);

        return modelMapper.map(updatedPerson, PersonResponse.class);
    }

    public PersonResponse removeSkillsFromPerson(Long personId, RemoveSkillsRequest request) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + personId));

        person.getSkills().removeAll(request.getSkills());
        Person updatedPerson = personRepository.save(person);

        return modelMapper.map(updatedPerson, PersonResponse.class);
    }

}
