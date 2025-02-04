package com.softserveinc.ita.home.home_project_blog.service.dto;

import com.softserveinc.ita.home.home_project_blog.security.model.Role;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class UserDto {
    private Long id;

    @NotNull
    @Size(min = 4, max = 255, message = Const.NAME_WRONG_LENGTH)
    private String name;

    @Size(min = 1, max = 255, message = Const.FIRST_NAME_WRONG_LENGTH)
    private String firstName;

    @Size(min = 1, max = 255, message = Const.LAST_NAME_WRONG_LENGTH)
    private String lastName;

    @NotNull
    @Email(message = Const.EMAIL_IS_NOT_VALID)
    private String email;

    private String password;

    private Role role;

    public String getEmail() {
        return email.toLowerCase().trim();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase().trim();
    }
}
