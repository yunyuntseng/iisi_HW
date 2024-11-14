package iisi.example.gia3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "emp2")
@Getter
@Setter
@ToString
public class EmpDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empno;
    private String ename;
    private String job;
    private Date hiredate;
    private BigDecimal sal;
    private BigDecimal comm;
    private Integer deptno;

    @ManyToOne
    @JoinColumn(name = "deptno", insertable = false, updatable = false)
    private DeptDO deptDO;

    // 可以透過 dept2Entity 來獲取 dname
    public String getDname() {
        return deptDO != null ? deptDO.getDname() : null;
    }

    public EmpDO() {

    }

    public EmpDO(Integer empno, String ename, String job, Date hiredate, BigDecimal sal, BigDecimal comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }


}
