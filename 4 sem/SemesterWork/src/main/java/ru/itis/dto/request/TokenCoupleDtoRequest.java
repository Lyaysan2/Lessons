package ru.itis.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenCoupleDtoRequest {

    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;
}