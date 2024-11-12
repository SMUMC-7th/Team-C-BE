package umc.teamc.youthStepUp.policy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final String authToken;
    public AuthenticationService(@Value("${OPEN_API_KEY}") String authToken) { this.authToken = authToken; }

    public String getAuthToken() {
        return this.authToken;
    }
}
