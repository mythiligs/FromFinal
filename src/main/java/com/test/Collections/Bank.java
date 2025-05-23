package com.test.Collections ;

import com.test.annotation.FieldLength;

public record Bank(
		@FieldLength(length=20)String BankName,
		@FieldLength(length=20)String BankIFSC,
		@FieldLength(length=20)String BankMICR,
		@FieldLength(length=20)String BankBranch,
		@FieldLength(length=20)String BankBranchCode,
		@FieldLength(length=20)String BankAddress,
		@FieldLength(length=20)String BankContactNumber,
		@FieldLength(length=20)String BankContactEmail
) {
}
