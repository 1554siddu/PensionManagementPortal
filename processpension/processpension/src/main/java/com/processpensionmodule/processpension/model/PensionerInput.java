package com.processpensionmodule.processpension.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Model class for pensioner input, given by the user
 * 
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PensionerInput {
    @Id
	@Pattern(regexp = "[0-9]{12}", message = "Aadhaar Number is in invalid format")
	private String aadhaarNumber;



}