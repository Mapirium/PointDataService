package ch.mapirium.server.pointdata.rest.model;

import ch.mapirium.server.pointdata.model.PointDataEntity;
import ch.mapirium.server.pointdata.rest.controller.PointDataRestController;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mapper zwischen der JPA und REST-Entität eines Feld-Types
 */
@Component
public class PointDataMapper {

    public PointDataResource fromEntity(PointDataEntity entity) {
        PointDataResource result = new PointDataResource();
        result.setPublicId(entity.getPublicId());
        result.setMapId(entity.getMapId());
        result.setPointDefinitionId(entity.getPointDefinitionId());
        result.setLocation(entity.getLocation());

        // Link auf uns selbst
        result.add(linkTo(methodOn(PointDataRestController.class).getByPublicId(entity.getMapId(), entity.getPublicId())).withSelfRel());
        result.add(new Link("/map/" + entity.getMapId() + "/point/" + entity.getPublicId() + "/field", "fields"));


        return result;
    }

    public PointDataEntity toEntity(PointDataResource resource) {
        PointDataEntity result = new PointDataEntity();
        result.setPublicId(resource.getPublicId());
        result.setMapId(resource.getMapId());
        result.setPointDefinitionId(resource.getPointDefinitionId());
        result.setLocation(resource.getLocation());
        return result;
    }
}
