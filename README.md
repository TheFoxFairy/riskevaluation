# 社会评估风险

## 模块结构
* risk-gateway：网关（统一认证，转发服务等）
* risk-third-part：集成第三方服务

* risk-algorithms：算法服务放在这里 
  - 读取json文件，调用算法 
    - 算法文件位置 
    - 算法描述 
    - 数据库 
    - 本地文件 
  - 保存数据

* risk-common：公共依赖(大部分项目的共同部分，少部分就不要放进去了)

* risk-indicator：处理指标相关的，比如添加指标，删除指标，查询指标

* risk-auth：登录认证、用户等
  * 目前已经完成了鉴权认证部分了
    * 剩下的工作就是与gateway整合
    * 写一个auth的文档，便于维护

## 文件
* auth.sql：与用户相关数据库表
* indicator.sql：与指标相关数据库表


## 其他
用来生成jwt.jks文件
`keytool -genkey -alias jwt -keyalg RSA -keypass 123456 -keystore jwt.jks -storepass 123456`
