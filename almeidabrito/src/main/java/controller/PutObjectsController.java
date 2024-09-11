package controller;

import service.PutObjectsUseCase;
import resource.ResponseUtil;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class PutObjectsController implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final PutObjectsUseCase putObjectsUseCase = new PutObjectsUseCase();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        if (!"POST".equals(event.getHttpMethod())) {
            return ResponseUtil.createResponse(405, "Only accepts POST method.");
        }
        if (event.getBody() == null || event.getBody().isEmpty()) {
            return ResponseUtil.createResponse(400, "Event body is required.");
        }
        try {
            // Simulando a análise de multipart (pode ser substituído por uma biblioteca apropriada)
            String body = event.getBody();
            var result = putObjectsUseCase.execute(body);
            return ResponseUtil.createResponse(201, (String) result.get("message"), result.get("data"));
        } catch (Exception e) {
            return ResponseUtil.createResponse(500, "Internal Server Error", e.getMessage());
        }
    }
}