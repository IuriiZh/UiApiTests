package api.model.responses;

import lombok.Data;
import java.io.Serializable;

@Data
public class LoginJson implements Serializable {
    private String token;
}
