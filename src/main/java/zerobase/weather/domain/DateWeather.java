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
@Entity(name="date_weather")
public class DateWeather {
    @Id
    @ApiModelProperty(example = "날짜")
    private LocalDate date;

    @ApiModelProperty(example = "날씨 정보")
    private String weather;

    @ApiModelProperty(example = "날씨 정보 그림")
    private String icon;

    @ApiModelProperty(example = "날씨 온도")
    private double temperature;
}
