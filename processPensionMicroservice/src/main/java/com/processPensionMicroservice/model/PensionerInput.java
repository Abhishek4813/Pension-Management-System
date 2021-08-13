package com.processPensionMicroservice.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class PensionerInput {
	
	
	@NotNull(message="{name.notnull}")
	private String name;
	@NotNull(message="{dob.notnull}")
	@PastOrPresent
	private  @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth;
	@NotNull(message="{pan.notnull}")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "{pan.valid}")
	private String pan;
	@Positive(message="{aadhar.positive}")
	private long aadharNumber;
	@NotNull(message="{pentiontype.notnull}")
	private String pensionType;

}
