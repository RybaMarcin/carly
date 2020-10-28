package org.carly.api.rest.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
	private String type = "Bearer";
	private String token;
	private String id;
	private String email;
	private String companyId;
	private String firstName;
	private String lastName;
	private List<String> roles;

	public JwtResponse(String accessToken, String id, String email, String companyId, String firstName, String lastName, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.companyId = companyId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}
}
