package ru.netology.i18n;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    public void TestRussianText() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", result);
    }

    @Test
    public void TestUSAText() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.USA);
        assertEquals("Welcome", result);
    }
}