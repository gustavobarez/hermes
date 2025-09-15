package br.com.gustavobarez.hermes.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    
    private String errorCode;

    private String message;

}
