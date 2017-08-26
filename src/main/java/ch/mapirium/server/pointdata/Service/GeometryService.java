package ch.mapirium.server.pointdata.Service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Unterstützung für den Umgang mit Geodaten
 */
@Component
public class GeometryService {

    private GeometryFactory geometryFactory = new GeometryFactory();

    public Geometry parseArea(String location) throws IllegalArgumentException, ParseException {
        // Grundsätzliche überprüfung
        if (StringUtils.isBlank(location)) {
            throw new IllegalArgumentException("Kein Gebiet übergeben");
        }

        // Die vier Zahlen sind mit einem Komma getrennt
        String[] elements = location.split(",");
        if(elements.length != 4){
            throw new IllegalArgumentException("Es werden genau vier Zahlen erwartet");
        }

        // Die einzelnen Zahlen auslesen
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        double northEastLat = format.parse(elements[0]).doubleValue();
        double northEastLng = format.parse(elements[1]).doubleValue();
        double southWestLat = format.parse(elements[2]).doubleValue();
        double southWestLng = format.parse(elements[3]).doubleValue();

        // Koordinaten erstellen
        Coordinate northEast = new Coordinate(northEastLat, northEastLng);
        Coordinate southWest = new Coordinate(southWestLat, southWestLng);

        // Polygon erstellen
        Envelope envelope = new Envelope(northEast, southWest);
        Geometry area = geometryFactory.toGeometry(envelope);
        return area;
    }
}
