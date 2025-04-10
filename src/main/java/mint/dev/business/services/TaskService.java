package mint.dev.business.services;

import mint.dev.infrastructure.repository.TaskRepository;
import mint.dev.infrastructure.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public TaskEntity save(TaskEntity taskEntity) {
        var user = this.userService.findId(taskEntity.getUser().getId());
        taskEntity.setUser(user);
        return this.taskRepository.save(taskEntity);
    }

    public List<TaskEntity> getAll() {
        return this.taskRepository.findAll();
    }

    public TaskEntity finById(String taskId){
        return this.taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Taks not found"));
    }

    public void completed(String taskId) {
        var taskCompleted = this.finById(taskId);
        taskCompleted.setCompleted(true);
        this.save(taskCompleted);
    }

    public void delete(String taskId){
        this.taskRepository.deleteById(taskId);
    }

}
