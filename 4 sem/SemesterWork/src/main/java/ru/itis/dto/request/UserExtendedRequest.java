package ru.itis.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserExtendedRequest extends UserRequest {

    @Size(min = 2, max = 15, message = "MINIMUM_NAME_SIZE: {min}, MAXIMUM_NAME_SIZE: {max}")
    private String firstName;

    @Size(min = 4, max = 20, message = "MINIMUM_LAST_NAME_SIZE: {min}, MAXIMUM_LAST_NAME_SIZE: {max}")
    private String lastName;

}