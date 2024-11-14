package iisi.example.gia3.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class EmpVO {
    private Integer empno;
    private String ename;
    private String job;
    private Date hiredate;
    private BigDecimal sal;
    private Integer deptno;
    private String dname;
}
