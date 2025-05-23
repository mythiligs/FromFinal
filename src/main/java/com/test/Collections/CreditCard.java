package com.test.Collections;

import com.test.annotation.FieldLength;

public record CreditCard(
		@FieldLength(length = 4)int customerId,
		@FieldLength(length=10)String CardNumber,
		@FieldLength(length=10)String CardHolderName,
		@FieldLength(length=10)String CardCVV,
		@FieldLength(length=10)String CardType,
		@FieldLength(length=10)double CardCreditLimit,
		@FieldLength(length=10)double CardAvailableBalance,
		@FieldLength(length=10)String CardValidFrom,
		@FieldLength(length=10)String CardValidThru,
		@FieldLength(length=10)String CardPaymentDueDate,
		@FieldLength(length=10)boolean CardIsActive,
		@FieldLength(length=10)int CardRewardPoint
	) {
}
