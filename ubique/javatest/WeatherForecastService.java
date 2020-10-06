/*Edit the WeatherForecastService, FakeThermometer, and the Config classes so that:
•	The Spring container should always return a new instance of FakeThermometer.
•	Config class will configure Spring scheduling.
•	takeTemperatureMeasurement method should be executed every 50 milliseconds, using Spring scheduling.*/

package com.ubique.javatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@FunctionalInterface
interface TemperatureMeasurementCallback {
	public void temperatureMeasured(int temperature);
}

interface Thermometer {
	public int measure();
}

@Configuration
@Import({ FakeThermometer.class, WeatherForecastService.class })
class Config1 {

	@Bean
	public TemperatureMeasurementCallback callback() {
		return (temperature) -> System.out.println(temperature);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}

@Component
class FakeThermometer implements Thermometer {
	private int currentTemperature = 21;

	public int measure() {
		return currentTemperature++;
	}
}

@Service
public class WeatherForecastService {

	@Autowired
	private Thermometer thermometer;

	@Autowired
	private TemperatureMeasurementCallback callback;

	@Autowired
	public WeatherForecastService(Thermometer thermometer) {
		this.thermometer = thermometer;
	}

	@Scheduled(fixedDelay = 10000)
	public void takeTemperatureMeasurement() {
		int temperature = thermometer.measure();
		callback.temperatureMeasured(temperature);
	}
}
