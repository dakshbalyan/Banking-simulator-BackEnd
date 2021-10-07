package com.personal.atmSimulatorBackEnd.requestObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestObjectRegisterBank {
	
	private String bankName;
	private String branchName;

}
