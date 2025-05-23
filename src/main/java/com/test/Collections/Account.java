package com.test.Collections;

import com.test.annotation.FieldLength;

public record Account(
		@FieldLength(length=20) String  AccountId,
		@FieldLength(length = 6)int customerId,
		@FieldLength(length=20)String AccountHolderNumber,
		@FieldLength(length=20) String AccountScheme,
		@FieldLength(length=20) String AccountSchemeCode,
		@FieldLength(length=20) String AccountBranch,
		@FieldLength(length=20)String AccountIFSC,
		@FieldLength(length=20)double AccountBalance,
		@FieldLength(length=25)double AccountUnclearedFunds,
		@FieldLength(length=25)double AccountAmountOnHold,
		@FieldLength(length=22)double AccountSpendingLimit,
		@FieldLength(length=20) String AccountBank,
		@FieldLength(length=20) String AccountCreditCard,
		@FieldLength(length=20) String AccountDebitCard,
		@FieldLength(length=20) String AccountLoan,
		@FieldLength(length=20)String AccountCurrency,
		@FieldLength(length=20) String AccountOpeningDate,
		@FieldLength(length=20) String AccountUPI
	) {
}