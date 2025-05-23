package com.test.MongoToPojo;
import com.test.Collections.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.test.WriteInTxt.WriteInTxt;


import org.bson.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MongoRecordMapper {

    private final MongoDatabase database;
    private final ObjectMapper objectMapper;
    private final Map<String, Class<?>> collectionToRecordMap;

    public MongoRecordMapper(MongoDatabase database, Map<String, Class<?>> collectionToRecordMap) {
        this.database = database;
        this.collectionToRecordMap = collectionToRecordMap;
        this.objectMapper = new ObjectMapper()
        		.registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    private <T> List<T> mapCollectionToRecords(MongoCollection<Document> collection, Class<T> recordClass) {
        List<T> result = new ArrayList<>();
        
        for (Document doc : collection.find()) {         	
            try {
                T obj = objectMapper.readValue(doc.toJson(), recordClass);

                result.add(obj);
            } catch (JsonProcessingException e) {
                System.err.println("Failed to map document: " + doc);
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<CombinedTransactionRecord> buildCombinedRecords() {
        List<Address> addresses = mapCollectionToRecords(database.getCollection("Address"), Address.class);
        List<Bank> banks = mapCollectionToRecords(database.getCollection("Bank"), Bank.class);
        List<CreditCard> ccards = mapCollectionToRecords(database.getCollection("CreditCard"), CreditCard.class);
        List<DebitCard> dcards = mapCollectionToRecords(database.getCollection("DebitCard"), DebitCard.class);
        List<Loan> loans = mapCollectionToRecords(database.getCollection("Loan"), Loan.class);
        List<Status> statuses = mapCollectionToRecords(database.getCollection("Status"), Status.class);
        List<UPI> upis = mapCollectionToRecords(database.getCollection("UPI"), UPI.class);
        List<Customer> customers = mapCollectionToRecords(database.getCollection("Customer"), Customer.class);
        List<Account> accounts = mapCollectionToRecords(database.getCollection("Account"), Account.class);
        List<Transaction> transactions = mapCollectionToRecords(database.getCollection("Transaction"), Transaction.class);

        Map<Integer, Customer> customerMap = customers.stream()
                .collect(Collectors.toMap(
                        Customer::customerId,
                        c -> c,
                        (existing, replacement) -> existing
                ));


    //    Map<Integer, Address> addressMap =addresses.stream()
      //          .collect(Collectors.toMap(Address::ownerId, ad -> ad));
            
        
        Map<String, Bank> bankMap = banks.stream()
                .collect(Collectors.toMap(Bank::BankIFSC, b -> b));
            
        
        Map<Integer, CreditCard> creditcardMap = ccards.stream()
                .collect(Collectors.toMap(CreditCard::customerId, cc -> cc));
        
        Map<String, UPI> upiMap = upis.stream()
                .collect(Collectors.toMap(UPI::AccountUPI, u -> u));
            
        
        Map<Integer, DebitCard> debitcardMap = dcards.stream()
                .collect(Collectors.toMap(DebitCard::customerId, dc -> dc));
            
        
        Map<Integer, Loan> loanMap = loans.stream()
                .collect(Collectors.toMap(Loan::customerId, l -> l));
            
        
        Map<String,Transaction> transactionMap = transactions.stream()
                .collect(Collectors.toMap(Transaction::TransactionId, t -> t));
            
    
        Map<Integer, Status> statusMap =statuses.stream()
                .collect(Collectors.toMap(Status::statusId, s -> s));
            
        
        
        
        Map<Integer, Account> accountMap = accounts.stream()
            .collect(Collectors.toMap(Account::customerId, a -> a));
  
        
        

        List<CombinedTransactionRecord> combinedRecords = new ArrayList<>();

        for (Transaction txn : transactions) {
        	String txnId = txn. TransactionId();
            int custId = txn.Payer();


            combinedRecords.add(new CombinedTransactionRecord(
                txnId,
                txn,
                customerMap.get(custId),
                accountMap.get(customerMap.get(custId).customerAccountId()),
//                addressMap.get(txnId),
//                bankMap.get(accountMap.get(accId).AccountIFSC()),
//                    bankMap.get("AXIS0001234"),
//                creditcardMap.get(custId),
//                debitcardMap.get(custId),
//                loanMap.get(custId),
                statusMap.get(transactionMap.get(txnId).TransactionStatusId())
//                upiMap.get(accountMap.get(accId).AccountUPI())
            ));
        }

        return combinedRecords;
    }

    public static void writeObjectsByDate(List<CombinedTransactionRecord> combinedRecords) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");


        Map<Object, List<CombinedTransactionRecord>> groupedByDate =
            combinedRecords.stream()
                .collect(Collectors.groupingBy(
                    record -> LocalDateTime.parse(record.transaction().timestamp(), inputFormat),
                    TreeMap::new,
                    Collectors.toList()
                ));


        groupedByDate.forEach((dateTime, records) -> {
            String fileName = "transactions_" + ((LocalDateTime) dateTime).format(outputFormat) + ".txt";
            try {
                WriteInTxt.writeToFile(records, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
    
