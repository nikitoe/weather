package zerobase.weather.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.WeatherApplication;
import zerobase.weather.domain.DateWeather;
import zerobase.weather.domain.Diary;
import zerobase.weather.error.InvalidDate;
import zerobase.weather.repository.DateWeatherRepository;
import zerobase.weather.repository.DiaryRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {

    @Value("${openweathermap.key}") // 스프링 부트에 미리 지정한 변수들
    private String apiKey;

    private final DiaryRepository diaryRepository;
    private final DateWeatherRepository dateWeatherRepository;

    private static final Logger logger = LoggerFactory.getLogger(WeatherApplication.class);
    private static final String SEARCH_JSON_OBJECT = "weather";
    private static final String SEARCH_JSON_OBJECT_MAIN = "main";
    private static final String SEARCH_JSON_OBJECT_ICON = "icon";
    private static final String SEARCH_JSON_OBJECT_TEMP = "temp";


    @Transactional
    @Scheduled(cron = "0 0 1 * * *")
    public void saveWeatherDate() {
        dateWeatherRepository.save(getWeatherFromApi());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Diary createDiary(LocalDate date, String text) {

        logger.info("started to create diary");

        // 날씨 데이터 가져오기 (API or 우리 DB)
        DateWeather dateWeather = getDateWeather(date);

        // 우리 db에 넣기
        Diary nowDiary = new Diary();
        nowDiary.setDateWeather(dateWeather);
        nowDiary.setText(text);

        logger.info("end to create diary");

        return diaryRepository.save(nowDiary);
    }

    private DateWeather getWeatherFromApi() {
        // open weather map에서 날씨 데이터 가져오기
        String weatherData = getWeatherString();

        // 받아온 날씨 json 파싱하기
        Map<String, Object> parsedWeather = parseWeather(weatherData);

        DateWeather dateWeather = new DateWeather();
        dateWeather.setDate(LocalDate.now());
        dateWeather.setWeather(parsedWeather.get(SEARCH_JSON_OBJECT_MAIN).toString());
        dateWeather.setIcon(parsedWeather.get(SEARCH_JSON_OBJECT_ICON).toString());
        dateWeather.setTemperature((Double) parsedWeather.get(SEARCH_JSON_OBJECT_TEMP));

        return dateWeather;
    }

    private DateWeather getDateWeather(LocalDate date) {
        List<DateWeather> dateWeatherListDB = dateWeatherRepository.findAllByDate(date);
        if (dateWeatherListDB.isEmpty()) {
            // 새로 api에서 날씨정보를 가져와야 함
            return getWeatherFromApi();
        } else {
            return dateWeatherListDB.get(0);
        }
    }

    @Transactional(readOnly = true)
    public List<Diary> readDiary(LocalDate date) {

        if (date.isAfter(LocalDate.ofYearDay(3050, 1))) {
            throw new InvalidDate();
        }
        return diaryRepository.findAllByDate(date);
    }

    @Transactional(readOnly = true)
    public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Diary updateDiary(LocalDate date, String text) {
        Diary nowDiary = diaryRepository.getFirstByDate(date);
        nowDiary.setText(text);

        return diaryRepository.save(nowDiary);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteDiary(LocalDate date) {
        diaryRepository.deleteAllByDate(date);

    }


    private String getWeatherString() {

        String apiUrl =
            "https://api.openweathermap.org/data/2.5/weather?q=souel&appid=" + apiKey;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if (responseCode == HttpStatus.OK.value()) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            return response.toString();

        } catch (Exception e) {
            return "failed to get response";
        }


    }

    private Map<String, Object> parseWeather(String jsonString) {
        // json parser을 이용해 파싱을
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);

        } catch (ParseException e) {
            throw new RuntimeException(e);

        }

        Map<String, Object> resultMap = new HashMap<>();

        JSONArray weatherArray = (JSONArray) jsonObject.get(SEARCH_JSON_OBJECT);
        JSONObject weatherData = (JSONObject) weatherArray.get(0);
        resultMap.put(SEARCH_JSON_OBJECT_MAIN, weatherData.get(SEARCH_JSON_OBJECT_MAIN));
        resultMap.put(SEARCH_JSON_OBJECT_ICON, weatherData.get(SEARCH_JSON_OBJECT_ICON));

        JSONObject mainData = (JSONObject) jsonObject.get(SEARCH_JSON_OBJECT_MAIN);
        resultMap.put(SEARCH_JSON_OBJECT_TEMP, mainData.get(SEARCH_JSON_OBJECT_TEMP));

        return resultMap;
    }


}
