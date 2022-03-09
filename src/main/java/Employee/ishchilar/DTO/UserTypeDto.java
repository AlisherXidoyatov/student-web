package Employee.ishchilar.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTypeDto {

    private Integer id;
    @NotBlank(message = "Invalid name send correctly")
    private String name;

    @NotBlank(message = "Invalid display name send correctly")
    private  String displayName;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;


}
