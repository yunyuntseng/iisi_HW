package iisi.example.gia3.service;

import iisi.example.gia3.entity.DeptDO;
import iisi.example.gia3.repository.Dept2Repository;
//import iisi.example.gia3.vo.request.DeptRequest;
//import iisi.example.gia3.vo.response.DeptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Dept2Service {

    @Autowired
    private Dept2Repository dept2Repository;

    public List<DeptDO> getAll(){
        return dept2Repository.findAll();

    }

    //新增
    @Transactional
    public DeptDO addDept2(DeptDO deptDo) {
        DeptDO dept2 = new DeptDO();
        dept2.setLoc(deptDo.getLoc());
        dept2.setDname(deptDo.getDname());
        dept2Repository.save(dept2);
//        int i = 1 / 0;
        deptDo.setDeptno(dept2.getDeptno());
        return deptDo;
    }

    //刪除
    public void deleteDept2(Integer deptno) {
        dept2Repository.deleteById(deptno);
    }

    //修改
    @Transactional
    public DeptDO updateDept2(Integer deptno, DeptDO deptDo) {
        DeptDO dept2 = dept2Repository.findById(deptno).orElseThrow(() -> new RuntimeException("Dept2 not found"));
        dept2.setDeptno(deptDo.getDeptno());
        dept2.setDname(deptDo.getDname());
        dept2.setLoc(deptDo.getLoc());
//        dept2Repository.save(dept2);
        return deptDo;
    }

//    //新增的beforeAdd
//    public DeptDO beforeAdd(DeptRequest deptRequest) {
//        DeptDO deptDo = new DeptDO();
//        deptDo.setDname(deptRequest.getDname());
//        deptDo.setLoc(deptRequest.getLoc());
//        return dept2Repository.save(deptDo);
//    }
//
//    //新增的afterAdd
//    public DeptResponse afterAdd(DeptDO beforeAdd) {
//        DeptResponse deptResponse = new DeptResponse();
//        deptResponse.setDeptno(beforeAdd.getDeptno());
//        deptResponse.setDname(beforeAdd.getDname());
//        deptResponse.setLoc(beforeAdd.getLoc());
//        return deptResponse;
//
//    }
}
