package com.test.Collections;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public record CombinedTransactionRecord(
		String transactionid,
    	Transaction transaction,
    	Customer customer,
		Account account,
//    Address address,
//    Bank bank,
//    CreditCard creditCard,
//    DebitCard debitCard,
//    Loan loan,
    Status status
//    UPI upi
) {

}
