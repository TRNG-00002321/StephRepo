package com.revature.demo;

public class WeatherService
{
    private WeatherAPIClient wac;

    public WeatherService(WeatherAPIClient wac) {
        this.wac = wac;
    }

    public String getWeatherMessage(String city){
        double temp = wac.fetchTemperature(city);
        if (temp>30){
            return "It's hot in " + city;
        } else if (temp >15) {
            return "It's warm in " + city;
        }
        return "It's average in " +city;
    }

    public void refresh(String city){
        wac.fetchTemperature(city);
    }






}
