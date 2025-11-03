package api.model.responses;

import lombok.Data;
import java.io.Serializable;

@Data public class ListResourceJson implements Serializable {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;
}