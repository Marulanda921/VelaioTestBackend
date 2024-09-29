package velaio.PruebaTecnica.Infraestructure.Adapter.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    @NotBlank
    @Size(min = 5)
    private String fullName;
    @Min(18)
    private int age;
    private List<String> skills;
}
