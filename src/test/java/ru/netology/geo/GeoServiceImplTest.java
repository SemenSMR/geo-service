package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void TestLocalHost(){
        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp("127.0.0.1");

        Assertions.assertEquals(null, result.getCity());
        Assertions.assertEquals(null, result.getCountry());
        Assertions.assertEquals(null, result.getStreet());
        Assertions.assertEquals(0, result.getBuiling());

    }
    @Test
    void TestMOSCOW_IP(){
        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp("172.0.32.11");

        Assertions.assertEquals("Moscow", result.getCity());
        Assertions.assertEquals(Country.RUSSIA, result.getCountry());
        Assertions.assertEquals("Lenina" , result.getStreet());
        Assertions.assertEquals(15, result.getBuiling());

    }
    @Test
    void TestNEW_YORK_IP(){
        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp("96.44.183.149");

        assertEquals("New York", result.getCity());
        assertEquals(Country.USA, result.getCountry());
        assertEquals(" 10th Avenue", result.getStreet());
        assertEquals(32, result.getBuiling());

    }

}