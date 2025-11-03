package api.model.responses;

import lombok.Data;
import java.io.Serializable;

@Data
public class CreatedUserJson implements Serializable {
    private String name;
    private String job;
    private Integer id;
    private String createdAt;
}