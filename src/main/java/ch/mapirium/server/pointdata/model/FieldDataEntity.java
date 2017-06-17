package ch.mapirium.server.pointdata.model;

import ch.mapirium.server.common.jpa.model.PublicIdEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Enthält die Daten eines spezifischen Feldes
 */
@Entity
@Table(name = "fielddata")
public class FieldDataEntity extends PublicIdEntity {

    /** Fremdschlüssel auf den Punkt, zu welchem dieses Feld gehört */
    @ManyToOne
    @JoinColumn(name = "POINTDATA_FK", nullable = false)
    @NotNull
    private PointDataEntity pointData;

    /** Öffentlicher Schlüssel zur Definition dieses Feldes */
    @Column(name = "FIELD_DEFINITION_ID", nullable = false)
    @NotNull
    private String fieldDefinitionId;

    /** Wert des Feldes */
    @Column(name = "value", nullable = true)
    private String value;

    public PointDataEntity getPointData() {
        return pointData;
    }

    public void setPointData(PointDataEntity pointData) {
        this.pointData = pointData;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFieldDefinitionId() {
        return fieldDefinitionId;
    }

    public void setFieldDefinitionId(String fieldDefinitionId) {
        this.fieldDefinitionId = fieldDefinitionId;
    }
}
