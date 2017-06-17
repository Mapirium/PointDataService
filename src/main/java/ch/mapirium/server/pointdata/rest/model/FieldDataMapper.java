package ch.mapirium.server.pointdata.rest.model;

import ch.mapirium.server.pointdata.model.FieldDataEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper zwischen der JPA und REST-Entität eines Feld-Types
 */
@Component
public class FieldDataMapper {
    public FieldDataResource fromEntity(FieldDataEntity entity) {
        FieldDataResource result = new FieldDataResource();
        result.setPublicId(entity.getPublicId());
        result.setFieldDefinitionId(entity.getFieldDefinitionId());
        result.setValue(entity.getValue());
        return result;
    }

    public FieldDataEntity toEntity(FieldDataResource resource) {
        FieldDataEntity result = new FieldDataEntity();
        result.setPublicId(resource.getPublicId());
        result.setFieldDefinitionId(resource.getFieldDefinitionId());
        result.setValue(resource.getValue());
        return result;
    }
}
