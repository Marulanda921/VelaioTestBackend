package velaio.PruebaTecnica.Infraestructure.Adapter.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveSkillsRequest {
    private List<String> skills;

}
