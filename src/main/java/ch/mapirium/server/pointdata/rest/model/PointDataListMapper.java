package ch.mapirium.server.pointdata.rest.model;

import ch.mapirium.server.pointdata.model.PointDataEntity;
import ch.mapirium.server.pointdata.rest.controller.PointDataRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mappt eine Liste von Punkt-Daten in eine REST-Ressource
 */
@Component
public class PointDataListMapper {

    @Autowired
    private PointDataMapper pointDataMapper;

    public GenericDataListResource fromEntity(Iterable<PointDataEntity> entities, String mapId) {
        GenericDataListResource result = new GenericDataListResource();
        result.add(linkTo(methodOn(PointDataRestController.class).getAll(mapId)).withSelfRel());

        // Die einzelnen Entitäten mappen
        List<PointDataResource> dataResources = StreamSupport.stream(entities.spliterator(), false).map(pointDataMapper::fromEntity).collect(Collectors.toList());
        result.embed("pointData", dataResources);
        return result;
    }
}
