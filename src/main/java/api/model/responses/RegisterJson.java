package api.model.responses;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJson implements Serializable {
    private Integer id;
    private String token;
}
