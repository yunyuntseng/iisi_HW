package iisi.example.gia3.task;

import iisi.example.gia3.repository.Dept2Repository;
import iisi.example.gia3.entity.EmpDO;
import iisi.example.gia3.vo.EmpVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Slf4j
public class EmpItemProcessor implements ItemProcessor<EmpVO, EmpDO> {

    @Autowired
    private Dept2Repository dept2Repository;

    // 定義 SimpleDateFormat 用於解析日期
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public EmpDO process(EmpVO item) throws Exception {
        log.info("Processing item: {}", item);

        // 驗證 deptno 是否存在
        if (item.getDeptno() == null) {
            log.error("Deptno is null for item: {}", item);
            throw new IllegalArgumentException("Deptno cannot be null for item: " + item);
        }

        EmpDO empDO = new EmpDO();
        empDO.setEname(item.getEname());
        empDO.setJob(item.getJob());
        empDO.setHiredate(simpleDateFormat.parse(item.getHiredate()));
        empDO.setSal(BigDecimal.valueOf(item.getSal()));
        empDO.setComm(item.getComm() != null ? BigDecimal.valueOf(item.getComm()) : null);
//        empDO.setDeptDO(dept2Repository.findById(item.getDeptno()).orElseThrow());



        // 查詢部門，處理找不到部門的情況
        empDO.setDeptDO(
                dept2Repository.findById(item.getDeptno())
                        .orElseThrow(() -> {
                            log.error("Invalid deptno: {}", item.getDeptno());
                            return new IllegalArgumentException("Invalid deptno: " + item.getDeptno());
                        })
        );

        return empDO;

//        if ("manager".equals(item.getJob())) {
//            return empDO;
//        }
//        return null;
    }
}
