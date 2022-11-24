# 社会评估风险

## 技术工具

* 前端：Vue
* 后端：Java jdk8 + SpringCloud Alibaba (Nacos 、Gateway、Sentinel) + MySQL + Redis + RabbitMQ

## 模块结构
* risk-gateway：网关（统一认证，转发服务等）
* risk-third-part：集成第三方服务

* risk-algorithms：算法服务放在这里 
  - 读取json文件，调用算法  # 调度方法完成了
    - 算法文件位置 
    - 算法描述 
    - 数据库 
    - 本地文件 
  - 保存数据
  - 当算法处理到预警值的时候，向后台发起服务调用，传递（指标相关数据）给后台进行调用，后台调用rabbitmq服务，然后广播给相关人员（存到数据库中）

* risk-common：公共依赖(大部分项目的共同部分，少部分就不要放进去了)

* risk-indicator：处理指标相关的，比如添加指标，删除指标，查询指标
  * 完成了指标的初步：可以按EXCEL表进行分析，然后存储表；还能够为每个表附上标识，查询每个表，以json形式返回数据。

* risk-auth：登录认证、用户等
  * 就是判断url是否能够由当前账号进行访问

* risk-sys：用户-角色-权限相关api，为了方便调用，统一放在redis中
  * 用户-角色-权限相关api
  * 设置用户能够访问哪些页面
  * 权限表部分设置了权限类型
    * 页面类型，用于前端
    * api类型，用于后端


## 文件
* auth.sql：与用户相关数据库表
* indicator.sql：与指标相关数据库表


## 其他
用来生成jwt.jks文件
`keytool -genkey -alias jwt -keyalg RSA -keypass 123456 -keystore jwt.jks -storepass 123456`
