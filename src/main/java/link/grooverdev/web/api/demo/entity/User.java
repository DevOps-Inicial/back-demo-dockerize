package link.grooverdev.web.api.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@AllArgsConstructor
@Document(collection = "users")
@Getter
@NoArgsConstructor
@Setter
public class User {

    @Id
    private String userId;
    private String username;
    private String email;
    private String password;
    private boolean enabled;

    @CreatedBy
    private String createdByUser;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String lastModifiedByUser;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
