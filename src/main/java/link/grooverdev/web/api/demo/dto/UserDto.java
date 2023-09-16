package link.grooverdev.web.api.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDto {

    private String userId;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private String createdByUser;
    private LocalDateTime createdDate;
    private String lastModifiedByUser;
    private LocalDateTime lastModifiedDate;
}
