package com.example.news.news.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewsSearchCond {
    @Pattern(regexp = "^[가-힣a-z0-9]{1,50}", message = "타이틀 검색은 1~50글자의 한글, 영어, 숫자로 이루어져야 합니다.")
    private String newsTitle;
    @Pattern(regexp = "^[가-힣a-z0-9]{1,10}", message = "기자 검색은 1~10글자의 한글, 영어, 숫자로 이루어져야 합니다.")

    private String reporter;
    private String category;

    @Builder
    public NewsSearchCond(String newsTitle, String reporter,
                          String category){
        this.newsTitle = newsTitle;
        this.reporter = reporter;
        this.category = category;
    }
}
