package ch.mapirium.server.pointdata.rest.model;

import ch.mapirium.server.pointdata.model.FieldDataEntity;
import ch.mapirium.server.pointdata.rest.controller.FieldDataRestController;
import ch.mapirium.server.pointdata.rest.controller.PointDataRestController;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mapper zwischen der JPA und REST-Entität eines Feld-Types
 */
@Component
public class FieldDataMapper {
    public FieldDataResource fromEntity(FieldDataEntity entity, String mapId, String pointId) {
        FieldDataResource result = new FieldDataResource();
        result.setPublicId(entity.getPublicId());
        result.setFieldDefinitionId(entity.getFieldDefinitionId());
        result.setValue(entity.getValue());

        result.add(linkTo(methodOn(FieldDataRestController.class).getByPublicId(mapId, pointId, entity.getPublicId())).withSelfRel());

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
