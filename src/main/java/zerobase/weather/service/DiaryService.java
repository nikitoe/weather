package zerobase.weather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiaryService {

    @Value("${openweathermap.key}") // 스프링 부트에 미리 지정한 변수들
    private String apiKey;

    public void createDiary(LocalDate date, String text) {
        getWeatherString();

    }

    private String getWeatherString() {

        String apiUrl =
                "https://api.openweathermap.org/data/2.5/weather?q=souel&appid=" + apiKey;

        System.out.println(apiUrl);

        return "";
    }
}