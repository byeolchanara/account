package com.nhn.sadari.minidooray.account.domain;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> {
    private Header header;
    private List<T> result;
    private int totalCount;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Header {
        private boolean isSuccessful;
        private int resultCode;
        private String resultMessage;
    }

    public int getTotalCount() {
        return result == null? 0 : result.size();
    }
}