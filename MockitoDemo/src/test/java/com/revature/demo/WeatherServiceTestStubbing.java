package com.revature.demo;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherServiceTestStubbing
{
    @Test
    public void testWeatherServiceWithStubing()
    {
        //Arrange
        WeatherAPIClient ac = Mockito.mock(WeatherAPIClient.class);

        //Stubbing: define what the mock should return
        Mockito.when(ac.fetchTemperature("Plano")).thenReturn(35.0);

        WeatherService service = new WeatherService(ac);

        String mes = service.getWeatherMessage("Plano");
        assertEquals("It's hot in Plano", mes);

    }
}
