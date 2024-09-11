package repository;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.Map;

public class ObjectDynamoDB {

    private final DynamoDbClient ddb;
    private final String tableName;

    public ObjectDynamoDB() {
        this.ddb = DynamoDbClient.create();
        this.tableName = System.getenv("OBJECT_TABLE");
    }

    public Map<String, Object> putObject(Map<String, AttributeValue> metadata) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(this.tableName)
                .item(metadata)
                .build();
        try {
            ddb.putItem(request);
            return Map.of("message", "Item successfully inserted");
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error putting item into DynamoDB: " + e.getMessage());
        }
    }

    public Map<String, Object> getObjects() {
        ScanRequest request = ScanRequest.builder()
                .tableName(this.tableName)
                .build();
        try {
            ScanResponse response = ddb.scan(request);
            return Map.of("message", "Get Items Successful", "data", response.items());
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error getting items from DynamoDB: " + e.getMessage());
        }
    }
}
