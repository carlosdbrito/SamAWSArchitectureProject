package resource;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import java.util.Map;

public class ResponseUtil {

    public static APIGatewayProxyResponseEvent createResponse(int statusCode, String message) {
        return createResponse(statusCode, message, null);
    }

    public static APIGatewayProxyResponseEvent createResponse(int statusCode, String message, Object data) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(statusCode);
        response.setHeaders(Map.of(
                "Access-Control-Allow-Origin", "*",
                "Access-Control-Allow-Headers", "*",
                "Access-Control-Allow-Methods", "*"
        ));
        response.setBody(String.format("{\"message\":\"%s\", \"data\":%s}", message, data != null ? data.toString() : "null"));
        return response;
    }
}
