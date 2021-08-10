package com.processPensionMicroservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PensionerDetail implements Comparable<PensionerDetail>{
	private String name;
	private Date dateOfBirth;
	private String pan;
	private double salary;
	private double allowance;
	private String pensionType;
	private Bank bank;
	
	@Override
	public int compareTo(PensionerDetail o) {
		if(this.name.equalsIgnoreCase(o.getName()) && this.dateOfBirth.compareTo(o.getDateOfBirth())==0 && this.pan.equals(o.getPan())
				&& this.pensionType.equalsIgnoreCase(o.getPensionType()))
			return 0;
		else
			return -1;
	}

}
