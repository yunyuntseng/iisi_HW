package iisi.example.gia3.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DeptVO implements Serializable {
    private Integer deptno;
    private String dname;
    private String loc;
    private List<EmpVO> empVOs;
}
