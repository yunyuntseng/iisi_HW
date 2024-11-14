package iisi.example.gia3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "dept2")
@Getter
@Setter
public class DeptDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptno;
    private String dname;
    private String loc;

}
