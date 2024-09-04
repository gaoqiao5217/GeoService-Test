import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSender messageSender;

    @Before
    public void setup() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void testSendRussianMessage() {
        String ip = "172.0.32.11";
        Location location = new Location("Moscow", Country.RUSSIA, null, 0);
        Mockito.when(geoService.byIp(ip)).thenReturn(location);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать!");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = messageSender.send(headers);
        Assert.assertEquals("Добро пожаловать!", result);
    }

    @Test
    public void testSendEnglishMessage() {
        String ip = "96.44.183.149";
        Location location = new Location("New York", Country.USA, null, 0);
        Mockito.when(geoService.byIp(ip)).thenReturn(location);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome!");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = messageSender.send(headers);
        Assert.assertEquals("Welcome!", result);
    }
}