package com.processpensionmodule.processpension.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Model class for pensioner details

 *
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PensionerDetail {
	private String name;
	private Date dateOfBirth;
	private String pan;
	private double salary;
	private double allowance;
	private String pensionType;
	private Bank bank;
	

}