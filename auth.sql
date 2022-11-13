create database IF NOT EXISTS auth charset=utf8;

use auth;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS powers;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role_power;

-- 用户表
CREATE TABLE IF NOT EXISTS users(
    user_id     INT(10) PRIMARY KEY COMMENT '用户ID' ,
    user_name   VARCHAR(30) COMMENT "用户姓名" ,
    password   VARCHAR(30) COMMENT "用户密码" ,
    phone       CHAR(11) COMMENT "手机号" ,
    create_time DATETIME COMMENT "创建时间"  DEFAULT CURRENT_TIMESTAMP
)CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用户表';

INSERT INTO users(USER_ID, USER_NAME, PASSWORD, PHONE) VALUES (1,"胡桃","123456","12345678902");
INSERT INTO users(USER_ID, USER_NAME, PASSWORD, PHONE) VALUES (2,"天海","123456","12345678903");
INSERT INTO users(USER_ID, USER_NAME, PASSWORD, PHONE) VALUES (3,"妖刀姬","123456","12345678904");


-- 角色表
CREATE TABLE IF NOT EXISTS roles(
    role_id     INT(10) PRIMARY KEY COMMENT '角色ID',
    role_name   VARCHAR(30) COMMENT '角色名称',
    create_time DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '角色表';

INSERT INTO roles(role_id, role_name) VALUES (1,"super");
INSERT INTO roles(role_id, role_name) VALUES (2,"admin");
INSERT INTO roles(role_id, role_name) VALUES (3,"custom");

-- 权限表
CREATE TABLE IF NOT EXISTS powers(
    power_id    INT(10) PRIMARY KEY COMMENT '权限ID',
    power_name  VARCHAR(30) COMMENT '权限名称',
    power_url  VARCHAR(30) COMMENT '权限URL',
    power_icon  VARCHAR(30) COMMENT '权限图标',
    power_parent_id  int(10) default -1 COMMENT '父权限ID'
)CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '权限表';

INSERT INTO powers(power_id, power_name, power_url,power_icon) VALUES (1,"工作台","/dashboard","mdi:monitor-dashboard");
INSERT INTO powers(power_id, power_name, power_url,power_icon,power_parent_id) VALUES (2,"评估分析","/analysis","mdi:apps-box",1);
INSERT INTO powers(power_id, power_name, power_url,power_icon,power_parent_id) VALUES (3,"预测分析","/forecast","ph:align-bottom",1);
INSERT INTO powers(power_id, power_name, power_url,power_icon,power_parent_id) VALUES (4,"时间线","/time","ph:alarm",1);

-- 用户角色表
CREATE TABLE IF NOT EXISTS user_role(
    user_id     INT(10) NOT NULL COMMENT '用户ID',
    role_id     INT(10) NOT NULL COMMENT '角色ID'
)CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用户角色（关系）表';

INSERT INTO user_role(user_id, role_id) VALUES (1,1);
INSERT INTO user_role(user_id, role_id) VALUES (2,2);
INSERT INTO user_role(user_id, role_id) VALUES (3,3);

-- 角色权限表
CREATE TABLE IF NOT EXISTS role_power(
    role_id  INT(10) NOT NULL COMMENT '角色ID',
    power_id INT(10) NOT NULL COMMENT '权限ID'
)CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT  '角色权限（关系）表';

INSERT INTO role_power(role_id, power_id) VALUES (1,1);
INSERT INTO role_power(role_id, power_id) VALUES (1,2);
INSERT INTO role_power(role_id, power_id) VALUES (1,3);
INSERT INTO role_power(role_id, power_id) VALUES (1,4);