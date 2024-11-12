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
import java.util.List;

@Getter
@ToString
@XmlRootElement(name = "youthPolicyList")
public class PolicyRandomRequest {

    private int pageIndex; //조회 페이지 기본 값 1
    private int totalCnt; //총 건수
    private List<Emp> emp; //각 emp 객체 리스트
    @XmlElement(name = "youthPolicy")
    public List<Emp> getEmp(){
        return emp;
    }
    public void setEmp(List<Emp> emp) {
        this.emp = emp;
    }
    @Data
    @ToString
    public static class Emp{
        private String bizId; // 정책 ID
        private String polyBizSjnm; // 정책명
        private String rqutPrdCn; // 사업 신청 기간 내용
    }
    public static PolicyRandomRequest unmarshal(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PolicyRandomRequest.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (PolicyRandomRequest) unmarshaller.unmarshal(new StringReader(xml));
    }
}
