package com.processpensionmodule.processpension.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Bank {
	private String bankName;
	private long accountNumber;
	private String bankType;
}