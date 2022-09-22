package zerobase.weather.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.weather.service.DiaryService;
import zerobase.weather.type.ResponseResult;

@RestController
@RequiredArgsConstructor
@Api(tags = "날씨 일기 정보 Controller")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/create/diary")
    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장", notes = "이것은 노트")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공")
        , @ApiResponse(code = 201, message = "작성됨")
        , @ApiResponse(code = 204, message = "콘텐츠 없음")
        , @ApiResponse(code = 400, message = "잘못된 요청")
        , @ApiResponse(code = 401, message = "권한 없음")
        , @ApiResponse(code = 403, message = "금지됨")
        , @ApiResponse(code = 404, message = "찾을 수 없음")
        , @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    public ResponseEntity<?> createDiary(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-MM-dd", example = "2022-02-22") LocalDate date,
        @RequestBody String text
    ) {

        return ResponseEntity.ok(
            ResponseResult.res(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                "정상적으로 처리되었습니다.", true,
                diaryService.createDiary(date, text)));


    }


    @GetMapping("/read/diary")
    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져 옴")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공")
        , @ApiResponse(code = 201, message = "작성됨")
        , @ApiResponse(code = 204, message = "콘텐츠 없음")
        , @ApiResponse(code = 400, message = "잘못된 요청")
        , @ApiResponse(code = 401, message = "권한 없음")
        , @ApiResponse(code = 403, message = "금지됨")
        , @ApiResponse(code = 404, message = "찾을 수 없음")
        , @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    public ResponseEntity<?> readDiary(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-MM-dd", example = "2022-02-22") LocalDate date) {

        return ResponseEntity.ok(
            ResponseResult.res(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                "정상적으로 처리되었습니다.", true, diaryService.readDiary(date)));
    }


    @GetMapping("/read/diaries")
    @ApiOperation("선택한 기간 중에 모든 일기 데이터를 가져 옴")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공")
        , @ApiResponse(code = 201, message = "작성됨")
        , @ApiResponse(code = 204, message = "콘텐츠 없음")
        , @ApiResponse(code = 400, message = "잘못된 요청")
        , @ApiResponse(code = 401, message = "권한 없음")
        , @ApiResponse(code = 403, message = "금지됨")
        , @ApiResponse(code = 404, message = "찾을 수 없음")
        , @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    public ResponseEntity<?> readDiaries(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 첫번째 날", example = "2022-02-22") LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 마지막 날", example = "2022-02-22") LocalDate endDate
    ) {
        return ResponseEntity.ok(
            ResponseResult.res(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                "정상적으로 처리되었습니다.", true, diaryService.readDiaries(startDate, endDate)));

    }


    @PutMapping("/update/diary")
    @ApiOperation("선택 날짜의 일기 데이터 정보를 수정 함")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공")
        , @ApiResponse(code = 201, message = "작성됨")
        , @ApiResponse(code = 204, message = "콘텐츠 없음")
        , @ApiResponse(code = 400, message = "잘못된 요청")
        , @ApiResponse(code = 401, message = "권한 없음")
        , @ApiResponse(code = 403, message = "금지됨")
        , @ApiResponse(code = 404, message = "찾을 수 없음")
        , @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    public ResponseEntity<?> updateDiary(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-MM-dd", example = "2022-02-22") LocalDate date,
        @RequestBody String text
    ) {
        return ResponseEntity.ok(
            ResponseResult.res(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                "정상적으로 처리되었습니다.", true, diaryService.updateDiary(date, text)));

    }


    @DeleteMapping("/delete/diary")
    @ApiOperation("선택 날짜의 모든 일기 데이터를 삭제 함")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공")
        , @ApiResponse(code = 201, message = "작성됨")
        , @ApiResponse(code = 204, message = "콘텐츠 없음")
        , @ApiResponse(code = 400, message = "잘못된 요청")
        , @ApiResponse(code = 401, message = "권한 없음")
        , @ApiResponse(code = 403, message = "금지됨")
        , @ApiResponse(code = 404, message = "찾을 수 없음")
        , @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    public ResponseEntity<?> deleteDiary(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-MM-dd", example = "2022-02-22") LocalDate date) {

        return ResponseEntity.ok(
            ResponseResult.res(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                "정상적으로 처리되었습니다.", true));
    }
}
