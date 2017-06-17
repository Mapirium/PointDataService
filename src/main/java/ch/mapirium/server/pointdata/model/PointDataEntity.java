package ch.mapirium.server.pointdata.model;

import ch.mapirium.server.common.jpa.model.PublicIdEntity;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert einen konkreten Punkt auf der Karte
 */
@Entity
@Table(name = "pointdata")
public class PointDataEntity extends PublicIdEntity {

    @Column(name = "POINT_DEFINITION_ID", nullable = false)
    @NotNull
    private String pointDefinitionId;

    @Column(name = "MAP_ID", nullable = false)
    @NotNull
    private String mapId;

    @Column(name = "LOCATION", nullable = false, columnDefinition = "Point")
    @NotNull
    private Point location;

    @OneToMany(mappedBy = "pointData")
    private List<FieldDataEntity> fields;

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

    public List<FieldDataEntity> getFields() {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        return fields;
    }
}
