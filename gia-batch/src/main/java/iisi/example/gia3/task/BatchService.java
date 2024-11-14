package iisi.example.gia3.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BatchService {

//    // Spring Batch
    private final JobLauncher jobLauncher;
    private final Job importEmpJob;

    public BatchService(JobLauncher jobLauncher, Job importEmpJob) {
        this.jobLauncher = jobLauncher;
        this.importEmpJob = importEmpJob;
    }

    @Scheduled(fixedRate = 60000,initialDelay = 5000) // 每隔60秒執行一次
    public void runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(importEmpJob, jobParameters);
    }
}
