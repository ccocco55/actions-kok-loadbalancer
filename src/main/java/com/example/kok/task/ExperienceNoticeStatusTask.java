package com.example.kok.task;

import com.example.kok.service.ConsoleAdService;
import com.example.kok.service.ConsoleExperienceNoticeService;
import com.example.kok.service.ConsoleInternNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExperienceNoticeStatusTask {
    private final ConsoleExperienceNoticeService consoleExperienceNoticeService;
    private final ConsoleInternNoticeService consoleInternNoticeService;
    private final ConsoleAdService consoleAdService;
    /*
     *   0 * * * * * : 매 분 0초마다
     *   0/1 * * * * : 매 1초 간격
     *   0 0/1 * * * : 매 1분 간격
     *   0 0/5 * ? : 매 5분 간격
     *   0 0 0/1 * * : 매 1시간 간격
     *   0 0 0 * * ? : 매일 0시 마다
     *   0 0 0 1 * ? : 매월 1일 마다
     *   * 10-13 * * * * : 매 10, 11, 12, 13분에 동작한다.
     * */
//    매일 12시
    @Scheduled(cron="0 0/1 * * * *")
    public void task(){
        consoleExperienceNoticeService.closeNotice();
        consoleInternNoticeService.closeNotice();
        consoleAdService.closeAd();
    }
}