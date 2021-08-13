package com.pensionerDisbursementMicroservice.Model;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProcessPensionInput {

	@Positive(message= "{aadhaar.positive}")
	private Long aadharNumber;
	@Positive(message = "{pensionAmount.positive}")
	private Double pensionAmount;
	@Positive(message = "{serviceCharge.positive}")
	private double serviceCharge;
}
