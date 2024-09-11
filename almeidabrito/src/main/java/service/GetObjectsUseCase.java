package service;

import repository.ObjectDynamoDB;
import java.util.Map;

public class GetObjectsUseCase {

    private final ObjectDynamoDB objectDynamoDB = new ObjectDynamoDB();

    public Map<String, Object> execute() {
        return objectDynamoDB.getObjects();
    }
}
