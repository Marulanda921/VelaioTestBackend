package velaio.PruebaTecnica.Infraestructure.Adapter.Request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSkillsRequest {
    @NotEmpty
    private List<String> skills;
}
