package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.CityNotFoundException;
import exercise.dto.CityDto;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/cities/{id}")
    public String getWeatherInCity(@PathVariable long id, HttpServletResponse response) {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new CityNotFoundException("City with such id not found"));
        response.addHeader("Content-Type", "application/json");
        return weatherService.getWeather(city.getName());
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> getWeatherInCities(@RequestParam(required = false) String name) {
        Iterable<City> cities = (name == null ? cityRepository.findAllOrderByName()
                : cityRepository.findByNameStartingWithIgnoreCase(name));
        List<Map<String, String>> response = new ArrayList<>();
        for (City c : cities) {
            CityDto cityDto = new CityDto(c.getName(), c.getCountry());
            String weather = weatherService.getWeather(cityDto.name());
            Map<String, String> weatherMap;
            try {
                weatherMap = mapper.readValue(weather, new TypeReference<>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Map<String,String> mapEntry=new HashMap<>();
            mapEntry.put("temperature", weatherMap.get("temperature"));
            mapEntry.put("name",c.getName());
            response.add(mapEntry);
        }
        return response;
    }
    // END
}

