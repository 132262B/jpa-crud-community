package app.community.global.model.dto;

import lombok.*;

@Getter
@Setter
public class DefaultResultResponse {

    private String message;

    private boolean success;

    public DefaultResultResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}