package com.revature.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherServiceTestStubAndMock
{
    @Test
    public void testWeatherServiceWithStubAndMock()
    {
        //Arrange
        WeatherAPIClient ac = Mockito.mock(WeatherAPIClient.class);

        //Stubbing: define what the mock should return
        Mockito.when(ac.fetchTemperature("Plano")).thenReturn(35.0);

        WeatherService service = new WeatherService(ac);

        String mes = service.getWeatherMessage("Plano");
        assertEquals("It's hot in Plano", mes);

        //Mocking
        Mockito.verify(ac,Mockito.times(1)).fetchTemperature("Plano");

    }
}
