package iisi.example.gia3.repository;

import iisi.example.gia3.dto.EmpDTO;
import iisi.example.gia3.entity.EmpDO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Emp2Repository extends JpaRepository<EmpDO,Integer>, JpaSpecificationExecutor<EmpDO> {

    @Query("SELECT DISTINCT e.job FROM EmpDO e")
    List<String> findDistinctJobs();

    // 查找 empno 最大的那筆記錄
    EmpDO findTopByOrderByEmpnoDesc();

//    @EntityGraph(attributePaths = {"deptDO"})
//    List<EmpDO> findAll(Specification<EmpDO> specification, Sort sort);
//
//    @Query("SELECT new iisi.example.gia3.dto.EmpDTO(e.empno, e.ename, e.job, e.hiredate, e.sal, e.deptno, d.dname) " +
//            "FROM EmpDO e JOIN e.deptDO d")
//    List<EmpDTO> findAllEmpDTOs();
//
//    @Query("SELECT e FROM EmpDO e JOIN FETCH e.deptDO ORDER BY e.empno")
//    List<EmpDO> findAllWithDept(Specification<EmpDO> specification);


}
