package service;

import repository.ObjectDynamoDB;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.Map;

public class PutObjectsUseCase {

    private final ObjectDynamoDB objectDynamoDB = new ObjectDynamoDB();

    public Map<String, Object> execute(String body) {
        Map<String, AttributeValue> metadata = Map.of(
                "key", AttributeValue.builder().s("value").build()
        );
        return objectDynamoDB.putObject(metadata);
    }
}