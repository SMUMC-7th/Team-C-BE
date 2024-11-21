package umc.teamc.youthStepUp.policy.dto;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.StringReader;

@Getter
@ToString
@XmlRootElement(name = "youthPolicyList")
public class PolicyDetailRequest {

    private int pageIndex; //조회 페이지 기본 값 1
    private int totalCnt; //총 건수
    private Emp emp; //각 emp 객체 리스트

    @XmlElement(name = "youthPolicy")
    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    @Data
    @ToString
    public static class Emp {
        private int rnum; // row 번호
        private String bizId; // 정책 ID
        private String polyBizSecd; // 기관 코드
        private String polyBizTy; // 기관 및 지자체 구분
        private String polyBizSjnm; // 정책명
        private String polyItcnCn; // 정책소개
        private String lcyTpNm; // 정책 유형
        private String sporScvl; // 지원 규모
        private String sporCn; // 지원 내용
        private String ageInfo; // 연령 정보
        private String empmSttsCn; // 취업 상태 내용
        private String bizPrdCn; // 사업 운영 기간 내용
        private String prdRpttSecd; // 사업 신청 기간 반복 구분 코드
        private String rqutPrdCn; // 사업 신청 기간 내용
        private String majrRqisCn; // 전공 요건 내용
        private String splzRlmRqisCn; // 특화 분야 내용
        private String accrRqisCn; // 학력 요건 내용
        private String prcpCn; // 거주지 및 소득 조건 내용
        private String aditRscn; // 추가 단서 사항 내용
        private String prcpLmttTrgtCn; // 참여 제한 대상 내용
        private String rqutProcCn; // 신청 절차 내용
        private String pstnPaprCn; // 제출 서류 내용
        private String jdgnPresCn; // 심사 발표 내용
        private String rqutUrla; // 신청 사이트 주소
        private String rfcSiteUrla1; // 참고 사이트 URL 주소 1
        private String rfcSiteUrla2; // 참고 사이트 URL 주소 2
        private String mngtMson; // 주관 부처명
        private String mngtMrofCherCn; // 주관 부처 담당자 이름
        private String cherCtpcCn; // 주관 부처 담당자 연락처
        private String cnsgNmor; // 운영 기관명
        private String tintCherCn; // 운영 기관 담당자 이름
        private String tintCherCtpcCn; // 운영 기관 담당자 연락처
        private String etct; // 기타 사항
        private String polyRlmCd; // 정책 분야 코드
    }

    public static PolicyDetailRequest unmarshal(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PolicyDetailRequest.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (PolicyDetailRequest) unmarshaller.unmarshal(new StringReader(xml));
    }
}
