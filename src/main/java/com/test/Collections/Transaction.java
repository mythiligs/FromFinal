package com.test.Collections;


import com.test.annotation.*;

public record Transaction
		(
				String TransactionId,
		@FieldLength(length = 11) String timestamp,
		@FieldLength(length = 30) Double TransactionWithdrawalAmount,
		@FieldLength(length = 26) Double TransactionDepositAmount,
		@FieldLength(length = 6) int Payer,
		@FieldLength(length = 6) int Payee,
		@FieldLength(length = 20) String TransactionType,
		@FieldLength(length = 30) String TransactionReferenceNumber,
		@FieldLength(length = 26) Double TransactionClosingAmount,
		@FieldLength(length = 20) int TransactionStatusId
		) 
{
}
