package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResp {
    private boolean success;
    private String message;
    private String accessToken;

    public TokenResp(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

