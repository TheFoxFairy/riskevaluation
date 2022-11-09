# 社会评估风险

## 模块结构
* risk-gateway：网关（统一认证，转发服务等）
* risk-third-part：集成第三方服务
* risk-algorithms：算法服务放在这里
* risk-common：公共依赖(大部分项目的共同部分，少部分就不要放进去了)
* risk-indicator：处理指标相关的，比如添加指标，删除指标，查询指标
