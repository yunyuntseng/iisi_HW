package iisi.example.gia3.config;

import iisi.example.gia3.entity.EmpDO;
import iisi.example.gia3.vo.EmpVO;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
//@EnableBatchProcessing
public class Batch2Configuration {

    /*
    // JPA Reader: 從資料庫讀取資料
    @Bean
    public JpaPagingItemReader<EmpDO> empReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<EmpDO>()
                .name("empReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT e FROM EmpDO e JOIN FETCH e.deptDO") // 加入部門關聯查詢
                .pageSize(10)
                .build();
    }

    // Processor: 處理資料
    @Bean
    public ItemProcessor<EmpDO, EmpVO> empProcessor() {
        return empDO -> {
            EmpVO empVO = new EmpVO();
            empVO.setEmpno(empDO.getEmpno());
            empVO.setEname(empDO.getEname());
            empVO.setJob(empDO.getJob());
            empVO.setHiredate(empDO.getHiredate());
            empVO.setSal(empDO.getSal());
            empVO.setDeptno(empDO.getDeptno());
            empVO.setDname(empDO.getDeptDO() != null ? empDO.getDeptDO().getDname() : null); // 部門名稱
            return empVO;
        };
    }


    // Writer: 寫入 CSV 文件
    @Bean
    public FlatFileItemWriter<EmpVO> empWriter(@Value("${output.file.path}") String outputPath) {
        return new FlatFileItemWriterBuilder<EmpVO>()
                .name("empWriter")
                .resource(new FileSystemResource(outputPath))
                .delimited()
                .delimiter(",")
                .names("empno", "ename", "job", "hiredate", "sal", "deptno", "dname") // CSV 欄位
                .build();
    }


    // Step 配置
    @Bean
    public Step empStep(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager,
                        JpaPagingItemReader<EmpDO> empReader,
                        ItemProcessor<EmpDO, EmpVO> empProcessor,
                        FlatFileItemWriter<EmpVO> empWriter) {
        return new StepBuilder("empStep", jobRepository)
                .<EmpDO, EmpVO>chunk(10, transactionManager)
                .reader(empReader)
                .processor(empProcessor)
                .writer(empWriter)
                .build();
    }

    // Job 配置
    @Bean
    public Job empJob(JobRepository jobRepository, Step empStep) {
        return new JobBuilder("empJob", jobRepository)
                .start(empStep)
                .build();
    }
     */

    @Bean
    public CommandLineRunner jobRunner(JobLauncher jobLauncher, Job empJob) {
        return args -> {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(empJob, jobParameters);
        };
    }

}

