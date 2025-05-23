package com.test.Collections;

import com.test.annotation.FieldLength;

public record Loan(
		@FieldLength(length=10)int customerId,
		@FieldLength(length=10)String LoanScheme,
		@FieldLength(length=10)double LoanAmount,
		@FieldLength(length=10)double LoanInterest,
		@FieldLength(length=10)String LoanTenureDuration,
		@FieldLength(length=10)String LoanDisbursementDate,
		@FieldLength(length=10)String LoanMaturityDate,
		@FieldLength(length=10)double LoanOutstandingAmount,
		@FieldLength(length=10)double LoanEMIAmount,
		@FieldLength(length=10)String LoanCollateralDetails,
		@FieldLength(length=10)double LoanPenalty,
		@FieldLength(length=10)String LoanStatus
	) {

}
