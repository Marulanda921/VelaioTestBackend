package velaio.PruebaTecnica.Infraestructure.Persistence.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velaio.PruebaTecnica.Domain.Model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByCompleted(boolean completed);
}
