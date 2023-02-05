package app.community.global.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultResultResponse {

    private String message;

    private boolean success;

    @Builder
    public DefaultResultResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}