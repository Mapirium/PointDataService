package ch.mapirium.server.pointdata.rest.model;

import ch.mapirium.server.pointdata.rest.converter.PointDeserializer;
import ch.mapirium.server.pointdata.rest.converter.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Point;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * Definiert die REST-Ressource für die Punkt-Daten
 */
public class PointDataResource extends ResourceSupport {

    /** Öffentlicher Schlüssel */
    private String publicId;

    /** Öffentlicher Schlüssel der Definition für diese Punkt */
    private String pointDefinitionId;

    /** Öffentlicher Schlüssel der Karte */
    private String mapId;

    /** Geografische Position dieses Punktes */
    @JsonSerialize(using = PointSerializer.class)
    @JsonDeserialize(using = PointDeserializer.class)
    private Point location;

    /** Erstelldatum dieses Eintrages */
    private Date createdAt;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getPointDefinitionId() {
        return pointDefinitionId;
    }

    public void setPointDefinitionId(String pointDefinitionId) {
        this.pointDefinitionId = pointDefinitionId;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
