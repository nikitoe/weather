package zerobase.weather.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

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
    void createDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-MM-dd", example = "2022-02-22") LocalDate date,
            @RequestBody String text
    ) {
        diaryService.createDiary(date, text);

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
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-MM-dd", example = "2022-02-22") LocalDate date) {

        return diaryService.readDiary(date);
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
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 첫번째 날", example = "2022-02-22") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 마지막 날", example = "2022-02-22") LocalDate endDate
    ) {
        return diaryService.readDiaries(startDate, endDate);

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
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-MM-dd", example = "2022-02-22") LocalDate date,
                     @RequestBody String text
    ) {
        diaryService.updateDiary(date, text);

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
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-MM-dd", example = "2022-02-22") LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
