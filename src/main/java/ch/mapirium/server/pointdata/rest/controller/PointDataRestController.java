package ch.mapirium.server.pointdata.rest.controller;

import ch.mapirium.server.common.springmvc.exceptions.NotFoundException;
import ch.mapirium.server.pointdata.model.PointDataEntity;
import ch.mapirium.server.pointdata.repo.PointDataRepository;
import ch.mapirium.server.pointdata.rest.model.PointDataMapper;
import ch.mapirium.server.pointdata.rest.model.PointDataResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-Schnittstelle für die Daten eines Punktes
 */
@RestController
@RequestMapping(path = "/map/{mapId}/point")
public class PointDataRestController {

    @Autowired
    private PointDataRepository pointDataRepository;

    @Autowired
    private PointDataMapper pointDataMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<PointDataResource> getAll(@PathVariable("mapId") String mapId) {
        // Daten laden
        List<PointDataEntity> data = pointDataRepository.findByMapId(mapId);

        // Umwandeln
        List<PointDataResource> result = data.stream().map(pointDataMapper::fromEntity).collect(Collectors.toList());
        return result;
    }

    @RequestMapping(path = "/{publicId}", method = RequestMethod.GET)
    public PointDataResource getByPublicId(@PathVariable("mapId") String mapId, @PathVariable("publicId") String publicId){
        // Daten laden
        PointDataEntity data = pointDataRepository.findByPublicId(publicId);

        if (data == null) {
            // Wenn wir nichts gefunden haben geben wir einen 404 zurück
            throw new NotFoundException("Kein Punkt mit der ID '" + publicId + "' gefunden");
        } else {
            // Mappen
            PointDataResource result = pointDataMapper.fromEntity(data);
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public PointDataResource create(@PathVariable("mapId") String mapId, @RequestBody PointDataResource pointData) {
        // IDs aus der URL übernehmen
        pointData.setMapId(mapId);

        // Entität erstellen
        PointDataEntity entity = pointDataMapper.toEntity(pointData);

        // Speichern
        PointDataEntity savedEntity = pointDataRepository.save(entity);

        // Mappen und zurückgeben
        PointDataResource result = pointDataMapper.fromEntity(savedEntity);
        return result;
    }
}