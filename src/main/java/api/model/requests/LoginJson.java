package api.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginJson implements Serializable {
    private String email;
    private String password;
}
