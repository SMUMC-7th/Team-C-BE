package umc.teamc.youthStepUp.firebase.service;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import umc.teamc.youthStepUp.firebase.domain.Alarm;
import umc.teamc.youthStepUp.firebase.dto.MessagePushRequest;
import umc.teamc.youthStepUp.firebase.dto.MessagePushServiceRequest;
import umc.teamc.youthStepUp.firebase.error.FcmErrorCode;
import umc.teamc.youthStepUp.firebase.error.exception.FcmException;
import umc.teamc.youthStepUp.firebase.repository.FCMRepository;
import umc.teamc.youthStepUp.global.error.GeneralErrorCode;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.dto.response.AlarmResponseDTO;
import umc.teamc.youthStepUp.profile.dto.response.AlarmResponseDTO.AlarmDTO;

@Service
@RequiredArgsConstructor
public class FCMService {
    private final ObjectMapper objectMapper;
    private final FCMRepository fcmRepository;
    @Value("${fcm.file_path}")
    private String FIREBASE_CONFIG_PATH;

    @Value("${fcm.url}")
    private String FIREBASE_API_URI;

    @Value("${fcm.google_api}")
    private String GOOGLE_API_URI;

    public AlarmResponseDTO getMyAlarm(Long memberId, Long cursorId, int offset) {
        Pageable pageable = PageRequest.of(0, offset, Sort.by(Sort.Direction.DESC, "createdAt"));

        // 커서 조건 확인
        Slice<Alarm> sliceAlarms = fcmRepository.findAllByMemberIdAndIdLessThanOrderByCreatedAtDesc(
                memberId, cursorId, pageable);

        List<AlarmDTO> alarms = sliceAlarms.getContent().stream()
                .map(alarm -> new AlarmDTO(alarm.getTitle(), alarm.getBody()))
                .toList();

        // 다음 페이지의 커서 설정 (마지막 Id)
        Long nextCursorId = sliceAlarms.hasNext()
                ? sliceAlarms.getContent().get(sliceAlarms.getNumberOfElements() - 1).getId()
                : 0;

        return new AlarmResponseDTO(alarms, sliceAlarms.hasNext(), nextCursorId);
    }

    public void pushMessage(Member member, MessagePushServiceRequest request) {
        fcmRepository.save(Alarm.builder().title(request.title()).body(request.body()).member(member)
                .build());
        RestClient.create()
                .post()
                .uri(FIREBASE_API_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .body(makeMessage(request))
                .header(AUTHORIZATION, "Bearer " + getAccessToken())
                .header(ACCEPT, "application/json; UTF-8")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (fcmRequest, fcmResponse) -> {
                    throw new FcmException(FcmErrorCode.INVALID_REQUEST_MESSAGE);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (fcmRequest, fcmResponse) -> {
                    throw new FcmException(GeneralErrorCode.INTERNAL_SERVER_ERROR_500);
                })
                .toBodilessEntity();
    }

    private String makeMessage(MessagePushServiceRequest request) {
        try {
            MessagePushRequest message = MessagePushRequest.of(request);
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException exception) {
            throw new FcmException(FcmErrorCode.INVALID_REQUEST_MESSAGE);
        }
    }

    //access Token
    private String getAccessToken() { // FCM의 API에 요청하기 위한 Access Token 발급
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(
                            "youthstepup-29a94-firebase-adminsdk-rx9v3-c8488edfc9.json").getInputStream())
                    .createScoped(List.of(GOOGLE_API_URI));
            googleCredentials.refreshIfExpired();
            return googleCredentials.getAccessToken().getTokenValue();
        } catch (IOException exception) {
            throw new FcmException(FcmErrorCode.INVALID_REQUEST_MESSAGE);
        }
    }


}
