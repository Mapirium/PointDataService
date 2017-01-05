package ch.mapirium.server.pointdata.rest.gateway;

import ch.mapirium.server.pointdata.rest.model.PublicIdResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Zugriff auf den Service zur Erstellung öffentlicher Schlüssel
 */
@Component
public class PublicIdGateway {
    @Autowired
    private RestTemplate restTemplate;

    public PublicIdResource createNewPublicId(){
        ResponseEntity<PublicIdResource> exchange = restTemplate.postForEntity("http://publicidservice/publicId/PO", "Test", PublicIdResource.class);
        return exchange.getBody();
    }
}
