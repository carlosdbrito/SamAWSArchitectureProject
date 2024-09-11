package almeidabrito;

import controller.PutObjectsController;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PutObjectControllerTest {

    private PutObjectsController putObjectController;

    @Mock
    private Context context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        putObjectController = new PutObjectsController();
    }

    @Test
    void testHandleRequest_withValidPostRequest() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHttpMethod("POST");
        requestEvent.setBody("{ \"key\": \"value\" }");

        APIGatewayProxyResponseEvent response = putObjectController.handleRequest(requestEvent, context);

        assertEquals(201, response.getStatusCode());
        assertTrue(response.getBody().contains("Item successfully inserted"));
    }

    @Test
    void testHandleRequest_withInvalidHttpMethod() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHttpMethod("GET");

        APIGatewayProxyResponseEvent response = putObjectController.handleRequest(requestEvent, context);

        assertEquals(405, response.getStatusCode());
        assertTrue(response.getBody().contains("Only accepts POST method."));
    }

    @Test
    void testHandleRequest_withNoBody() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHttpMethod("POST");

        APIGatewayProxyResponseEvent response = putObjectController.handleRequest(requestEvent, context);

        assertEquals(400, response.getStatusCode());
        assertTrue(response.getBody().contains("Event body is required."));
    }
}
