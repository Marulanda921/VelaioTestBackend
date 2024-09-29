package velaio.PruebaTecnica.Infraestructure.Persistence.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velaio.PruebaTecnica.Domain.Model.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
