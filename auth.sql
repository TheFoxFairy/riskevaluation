create
database IF NOT EXISTS auth charset=utf8;

use
auth;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS powers;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role_power;

-- 用户表
CREATE TABLE IF NOT EXISTS users
(
    user_id INT
(
    10
) PRIMARY KEY COMMENT '用户ID' ,
    user_name VARCHAR
(
    30
) COMMENT "用户姓名" ,
    password VARCHAR
(
    255
) COMMENT "用户密码" ,
    phone CHAR
(
    11
) COMMENT "手机号" ,
    status INT
(
    2
) COMMENT "状态" ,
    create_time DATETIME COMMENT "创建时间" DEFAULT CURRENT_TIMESTAMP
    ) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用户表';

INSERT INTO users(USER_ID, USER_NAME, PASSWORD, PHONE, STATUS)
VALUES (1, "胡桃", "$2a$10$01XIZKT..21zEPYKQDWD6.Z7LBqB0mvDwq4qSr3prO5pVTTGIgvma", "12345678902", 1);
INSERT INTO users(USER_ID, USER_NAME, PASSWORD, PHONE, STATUS)
VALUES (2, "天海", "$2a$10$01XIZKT..21zEPYKQDWD6.Z7LBqB0mvDwq4qSr3prO5pVTTGIgvma", "12345678903", 1);
INSERT INTO users(USER_ID, USER_NAME, PASSWORD, PHONE, STATUS)
VALUES (3, "妖刀姬", "$2a$10$01XIZKT..21zEPYKQDWD6.Z7LBqB0mvDwq4qSr3prO5pVTTGIgvma", "12345678904", 1);


-- 角色表
CREATE TABLE IF NOT EXISTS roles
(
    role_id INT
(
    10
) PRIMARY KEY COMMENT '角色ID',
    role_name VARCHAR
(
    30
) COMMENT '角色名称',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
    ) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '角色表';

INSERT INTO roles(role_id, role_name)
VALUES (1, "super");
INSERT INTO roles(role_id, role_name)
VALUES (2, "admin");
INSERT INTO roles(role_id, role_name)
VALUES (3, "custom");

-- 权限表
CREATE TABLE IF NOT EXISTS powers
(
    power_id INT
(
    10
) PRIMARY KEY COMMENT '权限ID',
    power_type VARCHAR
(
    30
)  COMMENT '权限类型',
    power_name VARCHAR
(
    30
) COMMENT '权限名称',
    power_url VARCHAR
(
    30
) COMMENT '权限URL',
    power_icon VARCHAR
(
    30
) COMMENT '权限图标',
    power_parent_id int
(
    10
) default -1 COMMENT '父权限ID'
    ) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '权限表';

INSERT INTO powers(power_id, power_name,power_type, power_url, power_icon)
VALUES (1, "工作台", "页面", "/dashboard", "mdi:monitor-dashboard");
INSERT INTO powers(power_id, power_name,power_type, power_url, power_icon, power_parent_id)
VALUES (2, "评估分析","页面",  "/analysis", "mdi:apps-box", 1);
INSERT INTO powers(power_id, power_name,power_type, power_url, power_icon, power_parent_id)
VALUES (3, "预测分析","页面", "/forecast", "ph:align-bottom", 1);
INSERT INTO powers(power_id, power_name,power_type, power_url, power_icon, power_parent_id)
VALUES (4, "时间线","页面", "/time", "ph:alarm", 1);

INSERT INTO powers(power_id, power_name,power_type, power_url)
VALUES (5, "用户API","api", "/sys/users");
INSERT INTO powers(power_id, power_name,power_type, power_url)
VALUES (6, "角色API","api", "/sys/roles");
INSERT INTO powers(power_id, power_name,power_type, power_url)
VALUES (7, "权限API","api", "/sys/powers");
INSERT INTO powers(power_id, power_name,power_type, power_url)
VALUES (8, "用户角色API","api", "/sys/userrole");
INSERT INTO powers(power_id, power_name,power_type, power_url)
VALUES (9, "角色权限API","api", "/sys/rolepower");
INSERT INTO powers(power_id, power_name,power_type, power_url)
VALUES (10, "用户权限API","api", "/sys/users/power/type");

-- 用户角色表
CREATE TABLE IF NOT EXISTS user_role
(
    user_id INT
(
    10
) NOT NULL COMMENT '用户ID',
    role_id INT
(
    10
) NOT NULL COMMENT '角色ID'
    ) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用户角色（关系）表';

INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (2, 2);
INSERT INTO user_role(user_id, role_id)
VALUES (3, 3);

-- 角色权限表
CREATE TABLE IF NOT EXISTS role_power
(
    role_id INT
(
    10
) NOT NULL COMMENT '角色ID',
    power_id INT
(
    10
) NOT NULL COMMENT '权限ID'
    ) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '角色权限（关系）表';

INSERT INTO role_power(role_id, power_id)
VALUES (1, 1);
INSERT INTO role_power(role_id, power_id)
VALUES (1, 2);
INSERT INTO role_power(role_id, power_id)
VALUES (1, 3);
INSERT INTO role_power(role_id, power_id)
VALUES (1, 4);
INSERT INTO role_power(role_id, power_id)
VALUES (1, 5);
INSERT INTO role_power(role_id, power_id)
VALUES (1, 6);
INSERT INTO role_power(role_id, power_id)
VALUES (1, 7);
INSERT INTO role_power(role_id, power_id)
VALUES (1, 8);
INSERT INTO role_power(role_id, power_id)
VALUES (1, 9);
INSERT INTO role_power(role_id, power_id)
VALUES (1, 10);


-- CREATE TABLE sys_oauth_client_details
-- (
--     client_id               varchar(50) NOT NULL,
--     resource_ids            varchar(255) DEFAULT NULL,
--     client_secret           varchar(255) DEFAULT NULL,
--     scope                   varchar(255) DEFAULT NULL,
--     authorized_grant_types  varchar(255) DEFAULT NULL,
--     web_server_redirect_uri varchar(255) DEFAULT NULL,
--     authorities             varchar(255) DEFAULT NULL,
--     access_token_validity   int(11) DEFAULT NULL,
--     refresh_token_validity  int(11) DEFAULT NULL,
--     additional_information  text,
--     autoapprove             varchar(255) DEFAULT NULL,
--     PRIMARY KEY (client_id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='认证客户端';