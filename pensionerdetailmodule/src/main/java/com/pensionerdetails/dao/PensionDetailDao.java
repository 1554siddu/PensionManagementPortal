package com.pensionerdetails.dao;

import com.pensionerdetails.models.PensionerDetail;

public interface PensionDetailDao {
	public PensionerDetail getPensionerDetailByAadhaarNumber(String aadhaarNumber);
}
