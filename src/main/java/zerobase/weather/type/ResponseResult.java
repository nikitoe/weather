package zerobase.weather.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ResponseResult {

    private int status;
    private String statusMsg;

    private String message;
    private boolean result;
    private Object data;

    public ResponseResult() {
        this.status = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
    }


    public static ResponseResult res(int status, String statusMsg, String message, boolean result,
        Object data) {
        return ResponseResult.builder()
            .status(status)
            .statusMsg(statusMsg)
            .message(message)
            .result(result)
            .data(data)
            .build();
    }

    public static ResponseResult res(int status, String statusMsg, String message, boolean result) {
        return ResponseResult.builder()
            .status(status)
            .statusMsg(statusMsg)
            .message(message)
            .result(result)
            .build();
    }
}
