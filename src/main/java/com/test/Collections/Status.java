package com.test.Collections;

import com.test.annotation.FieldLength;

public record Status(
		@FieldLength(length=10)int statusId,
		@FieldLength(length=10)String Status,
		@FieldLength(length=10)String Description
	) {
}
