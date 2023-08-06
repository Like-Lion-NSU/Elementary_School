package thisisus.school.security.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Attributes {

    private Map<String, Object> mainAttributes;
    private Map<String, Object> subAttributes;
    private Map<String, Object> otherAttributes;

}
