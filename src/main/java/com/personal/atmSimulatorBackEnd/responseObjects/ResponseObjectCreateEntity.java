package com.personal.atmSimulatorBackEnd.responseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObjectCreateEntity {
	private int accountId;
	private int accountPin;

	private String status;
	private String message;
	private String exception;
}
