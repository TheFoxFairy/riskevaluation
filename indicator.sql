create database IF NOT EXISTS indicator charset = utf8;
use indicator;

DROP TABLE IF EXISTS `indexes`;
DROP TABLE IF EXISTS index_property;
DROP TABLE IF EXISTS colling_indexes;
DROP TABLE IF EXISTS colling;

-- 指标表
CREATE TABLE IF NOT EXISTS `indexes` # 有5级指标，最后一层指标是观测指标
(
    index_id    INT(10) PRIMARY KEY COMMENT '指标ID',
    index_type  VARCHAR(255) COMMENT '指标类型',# 一级指标、二级指标、三级指标、四级指标、观测指标
    index_name  VARCHAR(255) COMMENT '指标名称',
    index_parent_id       INT(11) COMMENT '指标父ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' # 同一张表的时间最好是一样的，因此在创建表的时候，需要自行设置时间，而不是使用默认时间
) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '指标表';


-- 指标属性表
CREATE TABLE IF NOT EXISTS index_property
(
    index_property_id     INT(10) PRIMARY KEY COMMENT '指标属性ID' auto_increment,
    index_id INT(10)  COMMENT '指标ID', # 注意这里一定是观测指标ID

    identification VARCHAR(255) COMMENT '用于表示是否是同一张表',

    truth_value VARCHAR(255) COMMENT '真实值',
    standard_value VARCHAR(1000) COMMENT '标准值',
    standard_value_supplement VARCHAR(1000) COMMENT '标准值补充/修正',
    extra VARCHAR(1000) COMMENT '可备注',

    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '指标属性表';


-- 指标依据表
CREATE TABLE IF NOT EXISTS colling
(
    colling_id  INT(10) PRIMARY KEY COMMENT '指标依据ID',
    colling_name  VARCHAR(1000) COMMENT '指标依据名称',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '指标依据表';

-- 指标依据与指标表
CREATE TABLE IF NOT EXISTS colling_indexes
(
    colling_id  INT(10)  COMMENT '指标依据ID' ,
    index_id    INT(10)  COMMENT '指标ID'
) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '指标依据与指标表';

-- 异常表
CREATE TABLE IF NOT EXISTS error_index
(
    error_index_id INT(10) PRIMARY KEY COMMENT '异常指标ID' auto_increment,
    index_id    INT(10)  COMMENT '指标ID',
    level VARCHAR(255) COMMENT '风险等级',
    status VARCHAR(255) COMMENT '指标状态',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '指标依据与指标表';
