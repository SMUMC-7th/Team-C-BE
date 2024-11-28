package umc.teamc.youthStepUp.auth.dto;

public record NewMemberResponseDTO(
        boolean isOriginMember,
        Long memberId,
        String nickName,
        String profileImgUrl
) {
}
