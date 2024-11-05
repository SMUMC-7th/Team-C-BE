package umc.teamc.youthStepUp.domain.calendar.recode.response;

public record BookmarkResponseByDateRecord(Long id, String name, String policyPeriod, boolean isCompleted,
                                           Long policyId) {
}
