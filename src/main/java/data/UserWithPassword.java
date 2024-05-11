package data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor

public class UserWithPassword {

    public final String name;
    public final String email;
    public final String password;

    public String getShortPassword (){
        return "MaryM";
    }


}
