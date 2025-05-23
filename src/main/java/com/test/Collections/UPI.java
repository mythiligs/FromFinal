package com.test.Collections;

import com.test.annotation.FieldLength;

public record UPI(
		@FieldLength(length=10)String AccountUPI,
		@FieldLength(length=10)String UPIEmail,
		@FieldLength(length=10)double UPITransactionLimit,
		@FieldLength(length=10)boolean UPIIsActive,
		@FieldLength(length=10)String UPILinkedMobileNumber
	) {
}
