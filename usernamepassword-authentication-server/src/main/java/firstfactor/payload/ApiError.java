package firstfactor.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class ApiError {

    private String errorResponse;

    @Override
    public String toString() {
        return "{\"error\"" + ":" + "\"" + errorResponse + "\"" + "}";
    }

}
