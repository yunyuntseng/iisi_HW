package iisi.example.gia3.service;

import iisi.example.gia3.entity.DeptDO;
import iisi.example.gia3.dto.EmpDTO;
import iisi.example.gia3.repository.Dept2Repository;
import iisi.example.gia3.entity.EmpDO;
import iisi.example.gia3.repository.Emp2Repository;
import iisi.example.gia3.specification.EmpSpecification;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;

@Slf4j
@Service
public class Emp2Service {

    @Autowired
    private Emp2Repository emp2Repository;

    @Autowired
    private Dept2Repository dept2Repository;

    //=================以下為DO、DTO轉換=================//
//    // 將 DO列表 轉換為 DTO列表
//    public List<EmpDTO> getAllEmployees() {
//        List<EmpDO> employees = emp2Repository.findAll();
//        return employees.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }

//    // 轉換 單一DO -> 單一DTO
    private EmpDTO convertToDTO(EmpDO vo) {
        EmpDTO dto = new EmpDTO();
        BeanUtils.copyProperties(vo, dto);
        return dto;
    }

//    // 將 Request DTO 轉換為 DO
//    public void createEmployee(EmpRequest empRequest) {
//        EmpDO empDO = new EmpDO();
//        BeanUtils.copyProperties(empRequest, empDO);
//        emp2Repository.save(empDO);
//    }

    //=================以下為動態查詢與分頁==================//
    public Page<EmpDTO> searchEmployees(EmpDTO empDTO, Pageable pageable) {
        Specification<EmpDO> specification = EmpSpecification.dynamicQuery(empDTO);
        Page<EmpDO> empDOPage = emp2Repository.findAll(specification, pageable);

        // 將 DO 轉換為 DTO
        return empDOPage.map(this::convertToDTO);
    }

    // 查詢所有部門
    public List<String> getAllJobs() {
        return emp2Repository.findDistinctJobs();  // 自定義方法
    }

    // 查詢所有部門
    public List<DeptDO> getAllDepts() {
        return dept2Repository.findAll();  // 查詢所有部門
    }



    //=================以下為刪除功能==================//
    // 刪除員工
    public void deleteEmployee(Integer empno) {
        EmpDO empDO = emp2Repository.findById(empno).orElseThrow(() -> new IllegalArgumentException("員工編號不存在: " + empno));
        emp2Repository.delete(empDO);
    }

    //=================以下為編輯功能==================//
    // 根據員工編號查詢員工，並返回 DTO
    public EmpDTO getEmployeeByEmpno(Integer empno) {
        EmpDO empDO = emp2Repository.findById(empno)
                .orElseThrow(() -> new IllegalArgumentException("員工編號不存在: " + empno));

        // 將 empDO 轉換為 EmpDTO
        EmpDTO empDTO = new EmpDTO();
        BeanUtils.copyProperties(empDO, empDTO);
        return empDTO;
    }

    // 更新員工資料
    public void updateEmployee(EmpDTO empDTO) {
        // 查詢員工
        EmpDO empDO = emp2Repository.findById(empDTO.getEmpno())
                .orElseThrow(() -> new IllegalArgumentException("員工編號不存在: " + empDTO.getEmpno()));

        // 將 DTO 的屬性拷貝到 DO 中，忽略 empno
        BeanUtils.copyProperties(empDTO, empDO, "empno", "deptno");

        // 保存更新後的資料
        emp2Repository.save(empDO);
    }

    //=================以下為新增功能==================//
    // 新增員工
    public void addEmployee(EmpDTO empDTO) {
        // 將 DTO 轉換為 DO
        EmpDO empDO = new EmpDO();
        BeanUtils.copyProperties(empDTO, empDO);

        // 保存到資料庫
        emp2Repository.save(empDO);
    }

    //用於gia-rest
    public EmpDTO getLatestAddedEmployee() {
        EmpDO latestEmpDO = emp2Repository.findTopByOrderByEmpnoDesc();
        if (latestEmpDO == null) {
            throw new NoSuchElementException("沒有找到最新的員工記錄");
        }
        EmpDTO empDTO = new EmpDTO();
        BeanUtils.copyProperties(latestEmpDO, empDTO);
        return empDTO;
    }

    //用於ZK測試
    public List<EmpDTO> searchEmployeesForZK(EmpDTO empDTO) {
        Specification<EmpDO> specification = EmpSpecification.dynamicQuery(empDTO);
        List<EmpDO> empDOList = emp2Repository.findAll(specification, Sort.by(Sort.Direction.ASC, "empno")); // 指定排序
        return empDOList.stream().map(this::convertToDTOZK).collect(Collectors.toList());
    }

    private EmpDTO convertToDTOZK(EmpDO empDO) {
        EmpDTO empDTO = new EmpDTO();
//        BeanUtils.copyProperties(empDO, empDTO);

        // 手動設置常規屬性
        empDTO.setEmpno(empDO.getEmpno());
        empDTO.setEname(empDO.getEname());
        empDTO.setJob(empDO.getJob());
        empDTO.setHiredate(empDO.getHiredate());
        empDTO.setSal(empDO.getSal());
        empDTO.setDeptno(empDO.getDeptno());

        // 格式化雇用日期
        if (empDO.getHiredate() != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            empDTO.setHireDateFormatted(dateFormat.format(empDO.getHiredate()));
        }

        // 手動設置懶加載屬性
        if (empDO.getDeptDO() != null) {
            try {
                empDTO.setDname(empDO.getDeptDO().getDname()); // 需要在 Session 打開時訪問
            } catch (LazyInitializationException e) {
                empDTO.setDname("N/A"); // 設置默認值或處理異常
            }
        } else {
            empDTO.setDname(null);
        }

        return empDTO;
    }

    // 獲取所有部門編號列表
    public List<Integer> getAllDeptNos() {
        // 提取所有部門的部門編號
        return dept2Repository.findAll().stream()
                .map(DeptDO::getDeptno)
                .collect(Collectors.toList());
    }

}
