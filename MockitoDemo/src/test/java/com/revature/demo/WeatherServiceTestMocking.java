package com.revature.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherServiceTestMocking
{
    @Test
    public void testWeatherServiceWithMocking()
    {
        //Arrange
        WeatherAPIClient ac = Mockito.mock(WeatherAPIClient.class);

        WeatherService service = new WeatherService(ac);
//
//        String mes = service.getWeatherMessage("Plano");
//        assertEquals("It's hot in Plano", mes);
        service.refresh("Frisco");
        //Mocking
        Mockito.verify(ac,Mockito.times(1)).fetchTemperature("Frisco");


    }
}
