package velaio.PruebaTecnica.Infraestructure.Adapter.Request;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String title;
    private boolean completed;
    private List<PersonRequest> persons;
}
