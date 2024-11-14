package iisi.example.gia3.task;

import iisi.example.gia3.entity.EmpDO;
import iisi.example.gia3.vo.EmpVO;
import org.springframework.batch.item.ItemProcessor;

public class EmpProcessor implements ItemProcessor<EmpDO, EmpVO> {

    @Override
    public EmpVO process(EmpDO empDO) throws Exception {
        System.out.println("Processing: " + empDO);
        EmpVO empVO = new EmpVO();
        empVO.setEmpno(empDO.getEmpno());
        empVO.setEname(empDO.getEname());
        empVO.setJob(empDO.getJob());
        empVO.setHiredate(empDO.getHiredate());
        empVO.setSal(empDO.getSal());
        empVO.setDeptno(empDO.getDeptno());
        empVO.setDname(empDO.getDeptDO() != null ? empDO.getDeptDO().getDname() : null);
        return empVO;
    }
}
