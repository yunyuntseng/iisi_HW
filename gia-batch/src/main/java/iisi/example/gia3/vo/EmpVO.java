package iisi.example.gia3.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class EmpVO implements Serializable {


    private String ename;

    private String job;

    private String hiredate;

    private Double sal;

    private Double comm;

    private Integer deptno;
}
