package ch.mapirium.server.pointdata.Service;

import com.vividsolutions.jts.geom.Geometry;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Test für die Klasse {@link GeometryService}
 */
@RunWith(MockitoJUnitRunner.class)
public class GeometryServiceTest {

    @InjectMocks
    private GeometryService sut;

    @Test
    public void testParseArea() throws Exception {
        String parameter = "46.982594624734936,7.498340606689452,46.92635183322409,7.388477325439452";

        Geometry result = sut.parseArea(parameter);

        Assert.assertNotNull(result);
    }
}