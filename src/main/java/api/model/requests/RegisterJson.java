package api.model.requests;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJson implements Serializable {
    private String username;
    private String email;
    private String password;
}
