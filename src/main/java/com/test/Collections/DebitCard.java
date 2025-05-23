package com.test.Collections;

import com.test.annotation.FieldLength;

public record DebitCard(
		@FieldLength(length=4)int customerId,
		@FieldLength(length=10)String CardNumber,
		@FieldLength(length=10)String CardValidFrom,
		@FieldLength(length=10)String CardValidThru,
		@FieldLength(length=10)String CardHolderName,
		@FieldLength(length=10)String CardCVV,
		@FieldLength(length=10)String CardType
	) {
}
