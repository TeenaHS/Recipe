package com.assessment.Recipe.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorInfo {
    private String errorMessage;
    private Integer errorCode;

}
