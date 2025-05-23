package com.test.Collections;

import com.test.annotation.FieldLength;



public record Customer(
		@FieldLength(length=6)int customerId,
		@FieldLength(length=15)String customerName,
		@FieldLength(length=20)String customerUsername,
		@FieldLength(length=20)String customerDOB,
		@FieldLength(length=20)String customerEmail,
		@FieldLength(length=20)String customerContactNumber,
		@FieldLength(length=20)String customerAlternateNumber,
		@FieldLength(length=20)String customerMailingAddress,
		@FieldLength(length=20)String customerPermanentAddress,
		@FieldLength(length=20)String customerNationality,
		@FieldLength(length=20)String customerNominee,
		@FieldLength(length=20)String customerMMID,
		@FieldLength(length=20)String customerAadhar,
		@FieldLength(length=20)String customerPANNumber,
		@FieldLength(length=20)String customerAccountId
) 	
{
}
