package ch.mapirium.server.pointdata.rest.model;

import org.springframework.hateoas.ResourceSupport;

/**
 * Definiert die REST-Schnittstelle für die Feld-Daten
 */
public class FieldDataResource extends ResourceSupport {
    /** Öffentlicher Schlüssel */
    private String publicId;

    /** Öffentlicher Schlüssel zur Definition dieses Feldes */
    private String fieldDefinitionId;

    /** Wert dieses Feldes */
    private String value;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getFieldDefinitionId() {
        return fieldDefinitionId;
    }

    public void setFieldDefinitionId(String fieldDefinitionId) {
        this.fieldDefinitionId = fieldDefinitionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
