-- 然後刪除表
DROP TABLE IF EXISTS emp2;
DROP TABLE IF EXISTS dept2;

-- 創建表
CREATE TABLE dept2 (
    deptno INT AUTO_INCREMENT NOT NULL,
    dname  VARCHAR(14),
    loc    VARCHAR(13),
    CONSTRAINT dept2_primary_key PRIMARY KEY (deptno)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE emp2 (
    empno    INT AUTO_INCREMENT NOT NULL,
    ename    VARCHAR(10),
    job      VARCHAR(9),
    hiredate DATE,
    sal      FLOAT,
    comm     FLOAT,
    deptno   INT                NOT NULL,
    CONSTRAINT emp2_deptno_fk FOREIGN KEY (deptno) REFERENCES dept2 (deptno),
    CONSTRAINT emp2_empno_pk PRIMARY KEY (empno)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
