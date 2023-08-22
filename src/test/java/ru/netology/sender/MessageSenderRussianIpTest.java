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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;



@ExtendWith(MockitoExtension.class)
class MessageSenderRussianIpTest {
    @Test
    public void testSendRussianTextForRussianIP() {
        GeoService geoServiceMock = mock(GeoService.class);
        LocalizationService localizationServiceMock = mock(LocalizationService.class);

        Mockito.when(geoServiceMock.byIp("172.123.12.19")).thenReturn(
                new Location("Kazan", Country.RUSSIA, "Baumana", 25)
        );
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock,localizationServiceMock);

        HashMap<String,String> header = new HashMap<>();
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        String result = messageSender.send(header);
        assertEquals("Добро пожаловать",result);

        Mockito.verify(localizationServiceMock, Mockito.times(2)).locale(Country.RUSSIA);
    }
}