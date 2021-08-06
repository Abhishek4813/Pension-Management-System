package com.pension.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {

	private int errorId;
	private String errorText;
	private String errorLogTime;
}
