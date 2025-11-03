package api.model.responses;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserdataJson implements Serializable {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
