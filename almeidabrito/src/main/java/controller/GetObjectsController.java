package controller;

import service.GetObjectsUseCase;
import resource.ResponseUtil;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class GetObjectsController implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final GetObjectsUseCase getObjectsUseCase = new GetObjectsUseCase();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        if (!"GET".equals(event.getHttpMethod())) {
            return ResponseUtil.createResponse(405, "Only accepts GET method.");
        }
        try {
            var result = getObjectsUseCase.execute();
            return ResponseUtil.createResponse(200, (String) result.get("message"), result.get("data"));
        } catch (Exception e) {
            return ResponseUtil.createResponse(500, "Internal Server Error", e.getMessage());
        }
    }
}
