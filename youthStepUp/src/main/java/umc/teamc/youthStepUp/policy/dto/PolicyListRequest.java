package umc.teamc.youthStepUp.policy.dto;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@XmlRootElement(name = "youthPolicyList")
public class PolicyListRequest {
    @XmlElement(name = "pageIndex")
    private int pageIndex; //조회 페이지 기본 값 1
    @XmlElement(name = "totalCnt")
    private int totalCnt; //총 건수
    private List<Emp> emp; //각 emp 객체 리스트

    @XmlElement(name = "youthPolicy")
    public List<Emp> getEmp() {
        return emp;
    }

    public void setEmp(List<Emp> emp) {
        this.emp = emp;
    }

    @Data
    @ToString
    public static class Emp {
        private String bizId; // 정책 ID
        private String polyBizSjnm; // 정책명
        private String rqutPrdCn; // 사업 신청 기간 내용
    }

    public static PolicyListRequest unmarshal(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PolicyListRequest.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (PolicyListRequest) unmarshaller.unmarshal(new StringReader(xml));
    }
}
