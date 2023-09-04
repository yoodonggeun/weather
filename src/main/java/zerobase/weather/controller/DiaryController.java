package zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.Domain.Diary;
import zerobase.weather.Service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }
    @Tag(name= "다이어리 쓰기", description = "일기를 작성해봐요!")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(name = "작성할 날짜를 적어주세요.", example = "2020-02-02") LocalDate date, @RequestBody String text) {
        diaryService.createDiary(date, text);
    }
    @Tag(name = "다이어리 읽기",description = "특정일의 일기를 불러와요!")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(name = "조회할 날짜를 적어주세요.", example = "2020-02-02") LocalDate date) {
        return diaryService.readDiary(date);
    }
    @Tag(name = "다이어리 읽기", description = "특정 기간의 일기를 불러와요!")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(name = "조회할 기간의 첫번째날", example = "2020-02-02")  LocalDate startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(name = "조회할 기간의 마지막날", example = "2020-02-02") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);

    }
    @Tag(name = "다이어리 업데이트", description = "일기를 고쳐요!")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(name = "업데이트할 날짜를 적어주세요.", example = "2020-02-02") LocalDate date, @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }
    @Tag(name = "다이어리 삭제", description = "지우고싶은 일기를 삭제해요!")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
        diaryService.deleteDiary(date);
    }
}
