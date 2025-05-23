package com.test.Main;
import java.util.List;
import java.util.Map;
import com.test.Collections.*;
import com.test.MongoDBConnection.MongoDBConnection;
import com.test.MongoToPojo.MongoRecordMapper;

public class Main {
    public static void main(String[] args) {

        Map<String, Class<?>> collectionRegistry = Map.of(
        		"Account",Account.class,
        		"Address",Address.class,
        		"Bank",Bank.class,
        		"CreditCard",CreditCard.class,
        		"DebitCard",DebitCard.class,
        		"Loan",Loan.class,
        		"Status",Status.class,
            "Transaction", Transaction.class,
            "Customer", Customer.class,
            "UPI",UPI.class
        );


        var database = MongoDBConnection.getDatabase();
        
        MongoRecordMapper mapper = new MongoRecordMapper(database, collectionRegistry);
        List<CombinedTransactionRecord> combinedRecords = mapper.buildCombinedRecords();
        MongoRecordMapper.writeObjectsByDate(combinedRecords);

        
    }
}

