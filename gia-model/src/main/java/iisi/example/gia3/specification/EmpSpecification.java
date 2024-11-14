package iisi.example.gia3.specification;
import iisi.example.gia3.dto.EmpDTO;
import iisi.example.gia3.entity.DeptDO;
import iisi.example.gia3.entity.EmpDO;
//import iisi.example.gia3.vo.request.EmpRequest;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmpSpecification {
    public static Specification<EmpDO> dynamicQuery(EmpDTO requestDTO) {
        return (Root<EmpDO> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (requestDTO.getEmpno() != null) {
                predicates.add(cb.equal(root.get("empno"), requestDTO.getEmpno()));
            }

            if (requestDTO.getEname() != null && !requestDTO.getEname().isEmpty()) {
                predicates.add(cb.like(root.get("ename"), "%" + requestDTO.getEname() + "%"));
            }

            if (requestDTO.getJob() != null && !requestDTO.getJob().isEmpty()) {
                predicates.add(cb.equal(root.get("job"), requestDTO.getJob()));
            }

            if (requestDTO.getStartDate() != null && requestDTO.getEndDate() != null) {
                predicates.add(cb.between(root.get("hiredate"), requestDTO.getStartDate(), requestDTO.getEndDate()));
            }

            if (requestDTO.getMinSal() != null && requestDTO.getMaxSal() != null) {
                predicates.add(cb.between(root.get("sal"), requestDTO.getMinSal(), requestDTO.getMaxSal()));
            } else if (requestDTO.getMinSal() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("sal"), requestDTO.getMinSal()));
            } else if (requestDTO.getMaxSal() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("sal"), requestDTO.getMaxSal()));
            }

            if (requestDTO.getDeptno() != null) {
                predicates.add(cb.equal(root.get("deptno"), requestDTO.getDeptno()));
            }

            if (requestDTO.getDname() != null && !requestDTO.getDname().isEmpty()) {
                Join<EmpDO, DeptDO> deptJoin = root.join("dept2Entity", JoinType.INNER);
                predicates.add(cb.like(deptJoin.get("dname"), "%" + requestDTO.getDname() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
