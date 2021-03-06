package ch.mapirium.server.pointdata.rest.controller;

import ch.mapirium.server.common.springmvc.exceptions.BadRequestException;
import ch.mapirium.server.common.springmvc.exceptions.NotFoundException;
import ch.mapirium.server.pointdata.Service.GeometryService;
import ch.mapirium.server.pointdata.Service.PointDataService;
import ch.mapirium.server.pointdata.model.PointDataEntity;
import ch.mapirium.server.pointdata.repo.PointDataRepository;
import ch.mapirium.server.pointdata.rest.model.PointDataListMapper;
import ch.mapirium.server.pointdata.rest.model.GenericDataListResource;
import ch.mapirium.server.pointdata.rest.model.PointDataMapper;
import ch.mapirium.server.pointdata.rest.model.PointDataResource;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * REST-Schnittstelle für die Daten eines Punktes
 */
@RestController
@RequestMapping(path = "/map/{mapId}/point")
public class PointDataRestController {

    @Autowired
    private PointDataRepository pointDataRepository;

    @Autowired
    private PointDataService pointDataService;

    @Autowired
    private PointDataMapper pointDataMapper;

    @Autowired
    private PointDataListMapper pointDataListMapper;

    @Autowired
    private GeometryService geometryService;

    @RequestMapping(method = RequestMethod.GET)
    public GenericDataListResource getAll(@PathVariable("mapId") String mapId) {
        // Daten laden
        List<PointDataEntity> data = pointDataRepository.findByMapId(mapId);

        // Umwandeln
        GenericDataListResource result = pointDataListMapper.fromEntity(data, mapId);
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
        PointDataEntity savedEntity = pointDataService.createPointData(entity);

        // Mappen und zurückgeben
        PointDataResource result = pointDataMapper.fromEntity(savedEntity);
        return result;
    }

    // Der Regex muss in den Path rein, weil Spring sonst die Location nach dem letzten Punkt abschneidet.
    @RequestMapping(path = "/search/area/{location:.+}", method = RequestMethod.GET)
    public GenericDataListResource findInArea(@PathVariable("mapId") String mapId, @PathVariable("location") String location){
        // Parameter parsen
        Geometry area = null;
        try {
            area = geometryService.parseArea(location);
        } catch (IllegalArgumentException | ParseException e) {
            throw new BadRequestException(e.getMessage());
        }

        // Punkte suchen
        List<PointDataEntity> entities = pointDataRepository.findWithing(mapId, area);
        GenericDataListResource result = pointDataListMapper.fromEntity(entities, mapId);

        return result;
    }
}
