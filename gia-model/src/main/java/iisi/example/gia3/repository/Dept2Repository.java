package iisi.example.gia3.repository;

import iisi.example.gia3.entity.DeptDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Dept2Repository extends JpaRepository<DeptDO,Integer> {


}
