# InfectStatistic
实现某次疫情统计可视化：https://edu.cnblogs.com/campus/fzu/2020SpringW/homework/10456

结对：221701438与221701429
+ 本次作业采用熟悉的JSP框架
+ 后端src部分的代码结构如下：
    + 1.constant：ProvinceName接口提供省份列表
    + 2.dao：数据访问层，重点负责数据库访问，完成持久化功能
        + ProvinceDAO接口：从数据库中读取数据
        + ProvinceDAOlmol：ProvinceDAO接口功能的实现
    + 3.log：存放助教提供的静态数据文件
    + 4.pojo：Province省份类，iP，sp，cure，dead以及相应的get和set方法
    + 5.servlet：Servlet类实现核心业务逻辑，事务控制也在这一层实现
    + 6.textProcess：Process类对log下文件内容正则匹配处理并写入数据库
    + 7.util：DBUtill类封装数据库连接与关闭方法
+ 先运行infectstatistic.textProcess.Process.main;
  将静态数据写入数据库后，再把项目部署到tomcat
  
+ 数据库
```
    CREATE TABLE `infectstatistic`.`province` (
      `id` INT NOT NULL,
      `name` VARCHAR(45)  NULL,
      `date` VARCHAR(45) NULL,
      `ip` INT NULL,
      `sp` INT NULL,
      `cure` INT NULL,
      `dead` INT NULL);
```
    
