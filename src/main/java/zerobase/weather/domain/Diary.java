package zerobase.weather.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty(example = "날씨 정보")
    private String weather;

    @ApiModelProperty(example = "날씨 정보 그림")
    private String icon;

    @ApiModelProperty(example = "날씨 온도")
    private double temperature;

    @ApiModelProperty(example = "일기 본문")
    private String text;

    @ApiModelProperty(example = "날짜")
    private LocalDate date;

    public void setDateWeather(DateWeather dateWeather) {
        this.date = dateWeather.getDate();
        this.weather = dateWeather.getWeather();
        this.icon = dateWeather.getIcon();
        this.temperature = dateWeather.getTemperature();

    }

}
