package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
@ExtendWith(MockitoExtension.class)
public class MessageSenderUSAIpTest {

    @Test
    public void testSendRussianTextForUSAIP() {
        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);

        Mockito.when(geoService.byIp("96.44.183.149"))
                        .thenReturn(new Location("New-York",Country.USA,"10th Avenue",54));

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService,localizationService);

        Map<String,String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER,"96.44.183.149");

        String result = messageSender.send(headers);

        assertEquals("Welcome", result);

        Mockito.verify(localizationService,Mockito.times(2)).locale(Country.USA);

    }
}
