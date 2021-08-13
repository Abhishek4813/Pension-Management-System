package com.pension.authorization.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	@NotEmpty(message = "{username.blank}")
	private String username;
	@NotEmpty(message = "{password.blank}")
	private String password;
}
