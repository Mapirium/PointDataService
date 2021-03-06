package ch.mapirium.server.pointdata.rest.controller;

import ch.mapirium.server.common.springmvc.exceptions.NotFoundException;
import ch.mapirium.server.pointdata.Service.FieldDataService;
import ch.mapirium.server.pointdata.model.FieldDataEntity;
import ch.mapirium.server.pointdata.model.PointDataEntity;
import ch.mapirium.server.pointdata.repo.FieldDataRepository;
import ch.mapirium.server.pointdata.repo.PointDataRepository;
import ch.mapirium.server.pointdata.rest.model.FieldDataListMapper;
import ch.mapirium.server.pointdata.rest.model.FieldDataMapper;
import ch.mapirium.server.pointdata.rest.model.FieldDataResource;
import ch.mapirium.server.pointdata.rest.model.GenericDataListResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-Schnittstelle für die Feld-Daten
 */
@RestController
@RequestMapping(path = "/map/{mapId}/point/{pointId}/field")
public class FieldDataRestController {

    @Autowired
    private FieldDataRepository fieldDataRepository;

    @Autowired
    private FieldDataService fieldDataService;

    @Autowired
    private PointDataRepository pointDataRepository;

    @Autowired
    private FieldDataMapper fieldDataMapper;

    @Autowired
    private FieldDataListMapper fieldDataListMapper;

    @RequestMapping(method = RequestMethod.GET)
    public GenericDataListResource getAll(@PathVariable("mapId") String mapId, @PathVariable("pointId") String pointId){
        // Daten laden
        List<FieldDataEntity> data = fieldDataRepository.findByPointId(pointId);

        // Umwandeln
        GenericDataListResource result = fieldDataListMapper.fromEntity(data, mapId, pointId);
        return result;
    }

    @RequestMapping(path = "/{publicId}", method = RequestMethod.GET)
    public FieldDataResource getByPublicId(@PathVariable("mapId") String mapId, @PathVariable("pointId") String pointId, @PathVariable("publicId") String publicId){
        // Daten laden
        FieldDataEntity data = fieldDataRepository.findByPublicId(publicId);

        if (data == null) {
            // Wenn wir nichts gefunden haben geben wir einen 404 zurück
            throw new NotFoundException("Kein Feld mit der ID '" + publicId + "' gefunden");
        } else {
            return fieldDataMapper.fromEntity(data, mapId, pointId);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public FieldDataResource create(@PathVariable("mapId") String mapId, @PathVariable("pointId") String pointId, @RequestBody FieldDataResource fieldData){
        // Zugehöriger Punkt holen
        PointDataEntity pointData = pointDataRepository.findByPublicId(pointId);

        // Entität erstellen
        FieldDataEntity entity = fieldDataMapper.toEntity(fieldData);
        entity.setPointData(pointData);

        // Speichern
        FieldDataEntity savedFieldData = fieldDataService.createFieldData(entity);

        // Mappen und zurückgeben
        FieldDataResource result = fieldDataMapper.fromEntity(savedFieldData, mapId, pointId);
        return result;
    }
}
