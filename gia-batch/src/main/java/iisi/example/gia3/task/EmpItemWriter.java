package iisi.example.gia3.task;

import iisi.example.gia3.entity.EmpDO;
import iisi.example.gia3.repository.Emp2Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EmpItemWriter implements ItemWriter<EmpDO> {

    @Autowired
    private Emp2Repository emp2Repository;

    @Override
    public void write(Chunk<? extends EmpDO> chunk) throws Exception {
//        log.info("Writing chunk: {}", chunk);
//        chunk.getItems().forEach(item -> {
////            log.info("Writing item: {}", item);
//            emp2Repository.save(item);
//        });
        log.info("Writing chunk: {}", chunk);

        for (EmpDO item : chunk.getItems()) {
            // 檢查 deptDO 是否為空
            if (item.getDeptDO() == null) {
                log.warn("Skipping item with null deptDO: {}", item);
                continue; // 跳過該數據
            }

            // 正常寫入資料庫
            emp2Repository.save(item);
            log.info("Successfully wrote item: {}", item);
        }
    }
}
