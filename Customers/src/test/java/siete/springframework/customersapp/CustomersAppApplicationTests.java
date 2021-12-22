package siete.springframework.customersapp;

import org.apache.coyote.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CustomersAppApplicationTests {

    @LocalServerPort
    private int randomServerPort;

    @Test
    public void testGetEmployeesListSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseurl = "http://localhost:" + randomServerPort + "/customers";
        URI uri = new URI(baseurl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("customers"));

    }





}
