package almeidabrito;

import controller.GetObjectsController;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class GetObjectsControllerTest {

    private GetObjectsController getObjectsController;

    @Mock
    private Context context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getObjectsController = new GetObjectsController();
    }

    @Test
    void testHandleRequest_withValidGetRequest() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHttpMethod("GET");

        APIGatewayProxyResponseEvent response = getObjectsController.handleRequest(requestEvent, context);

        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().contains("Get Items Successful"));
    }

    @Test
    void testHandleRequest_withInvalidHttpMethod() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHttpMethod("POST");

        APIGatewayProxyResponseEvent response = getObjectsController.handleRequest(requestEvent, context);

        assertEquals(405, response.getStatusCode());
        assertTrue(response.getBody().contains("Only accepts GET method."));
    }
}
