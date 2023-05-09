package com.client.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    @NotBlank(message = "não pode ser nulo ou vazio")
    @Size(min = 6, message = "deve ter tamanho mínimo de 6 caracteres")
    private String name;
    @Email(message = "inválido")
    private String email;
    @Size(min = 11, message = "deve ter tamanho mínimo de 11 dígitos")
    private String phone;
    @CPF(message = "inválido")
    private String cpf;
    private LocalDate dtSubscription = LocalDate.now();
    private LocalDate dtExpiration = LocalDate.now();
    @NotNull(message = "não pode ser nulo")
    private Long userTypeId;
    private Long subscriptionTypeId;
}
