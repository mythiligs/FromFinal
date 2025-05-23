package com.test.Collections;

import com.test.annotation.FieldLength;

public record Address(
		@FieldLength(length=20)int  ownerId,
		@FieldLength(length=20)String DoorNumber,
		@FieldLength(length=20)String Street,
		@FieldLength(length=20)String City,
		@FieldLength(length=20) String District,
		@FieldLength(length=20)String State,
		@FieldLength(length=20) String Pincode,
		@FieldLength(length=20)String Country
	) { }

