USE mybatis;

-- SP
-- 第一個存儲過程
DROP PROCEDURE IF EXISTS select_user_by_id;
DELIMITER ;;
CREATE PROCEDURE select_user_by_id(
	IN userId BIGINT,
    OUT userName VARCHAR(50),
    OUT userPassword VARCHAR(50),
    OUT userEmail VARCHAR(50),
    OUT userInfo TEXT,
    OUT headImg BLOB,
    OUT createTime DATETIME
)
BEGIN
SELECT user_name, user_password, user_email, user_info, head_img, create_time
INTO   userName, userPassword, userEmail, userInfo, headImg, createTime
FROM sys_user
WHERE id = userId;
END
;;
DELIMITER ;

-- 第二個存儲過程
DROP PROCEDURE IF EXISTS select_user_page;
DELIMITER ;;
CREATE PROCEDURE select_user_page(
	IN userName VARCHAR(50),
    IN _offset BIGINT,
    IN _limit BIGINT,
    OUT total BIGINT
)
BEGIN
-- 查詢數據總數
select count(*) INTO total
from sys_user
where user_name like concat('%', userName,'%');
-- 分頁數據查詢
select *
from sys_user
where user_name like concat('%', userName,'%')
limit _offset, _limit;
END
;;
DELIMITER ;

-- 第三個存儲過程
DROP PROCEDURE IF EXISTS insert_user_and_roles;
DELIMITER ;;
CREATE PROCEDURE insert_user_and_roles(
	OUT userId BIGINT,
    IN userName VARCHAR(50),
    IN userPassword VARCHAR(50),
    IN userEmail VARCHAR(50),
    IN userInfo TEXT,
    IN headImg BLOB,
    OUT createTime DATETIME,
    IN roleIds VARCHAR(200)
)
BEGIN
-- 設置當前時間
SET createTime = NOW();
-- 插入數據 
INSERT INTO sys_user( user_name, user_password, user_email, user_info, head_img, create_time )
VALUES( userName, userPassword, userEmail, userInfo, headImg, createTime );
-- 獲取自增主键
SELECT LAST_INSERT_ID() INTO userId;
-- 保存用戶和角色的關聯數據
SET roleIds = CONCAT(',',roleIds, ',');
INSERT INTO sys_user_role(user_id, role_id)
SELECT userId, id FROM sys_role
WHERE INSTR(roleIds, CONCAT(',',id, ',')) > 0;
END
;;
DELIMITER ;

-- 第四個存儲過程
DROP PROCEDURE IF EXISTS delete_user_by_id;
DELIMITER ;;
CREATE PROCEDURE delete_user_by_id(
	IN userId BIGINT
)
BEGIN
DELETE FROM sys_user_role WHERE user_id = userId;
DELETE FROM sys_user WHERE id = userId;
END
;;
DELIMITER ;


