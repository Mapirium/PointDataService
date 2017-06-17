package ch.mapirium.server.pointdata.rest.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * Created by surech on 04.05.16.
 */
public class PublicIdResource extends ResourceSupport {

    private String publicId;

    private Date createdAt;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
