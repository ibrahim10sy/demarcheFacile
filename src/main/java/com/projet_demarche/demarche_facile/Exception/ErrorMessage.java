package com.projet_demarche.demarche_facile.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ErrorMessage {
    private int statusCode;
    private Date timestemp;
    private String message;
    private String description;
}
