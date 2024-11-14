package iisi.example.gia3.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class EmpDTO {

    private Integer empno;
    private String ename;
    private String job;
    private Date hiredate;
    private BigDecimal sal;
    private  Integer deptno;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private BigDecimal minSal;
    private BigDecimal maxSal;
    private String dname;

    public EmpDTO(Integer empno, String ename, String job, Date hiredate, BigDecimal sal, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.hiredate = hiredate;
        this.sal = sal;
        this.deptno = deptno;

    }

    public EmpDTO(Integer empno, String ename, String job, Date hiredate, BigDecimal sal,  String dname) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.hiredate = hiredate;
        this.sal = sal;
        this.dname = dname;
    }

    public EmpDTO() {

    }

    //用於ZK日期轉換
    @Getter
    @Setter
    private String hireDateFormatted;


}
