import org.junit.Assert;
import org.junit.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {

    private GeoService geoService = new GeoServiceImpl();

    @Test
    public void testLocationByRussianIp() {
        Location location = geoService.byIp("172.0.32.11");
        Assert.assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    public void testLocationByAmericanIp() {
        Location location = geoService.byIp("96.44.183.149");
        Assert.assertEquals(Country.USA, location.getCountry());
    }
}