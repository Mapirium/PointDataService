package ch.mapirium.server.pointdata.rest.model;

import ch.mapirium.server.pointdata.model.FieldDataEntity;
import ch.mapirium.server.pointdata.rest.controller.FieldDataRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mappt eine Liste von Feld-Daten in eine REST-Ressource
 */
@Component
public class FieldDataListMapper {

    @Autowired
    private FieldDataMapper fieldDataMapper;

    public GenericDataListResource fromEntity(Iterable<FieldDataEntity> entities, String mapId, String pointId) {
        GenericDataListResource result = new GenericDataListResource();
        result.add(linkTo(methodOn(FieldDataRestController.class).getAll(mapId, pointId)).withSelfRel());

        // Die einzelnen Entitäten mappen
        List<FieldDataResource> dataResources = StreamSupport.stream(entities.spliterator(), false).map(entity -> fieldDataMapper.fromEntity(entity, mapId, pointId)).collect(Collectors.toList());
        result.embed("fieldData", dataResources);
        return result;
    }
}
