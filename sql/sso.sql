#系统表
CREATE TABLE `civism-sso`.`sso_product`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45) NOT NULL,
    `grd_delete`   TINYINT(1)  NOT NULL COMMENT '逻辑删除 0 正常1删除',
    `gmt_create`   DATETIME    NOT NULL,
    `gmt_modified` DATETIME    NOT NULL,
    PRIMARY KEY (`id`)
);

#用户表
CREATE TABLE `civism-sso`.`sso_user`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45) NOT NULL,
    `password`     VARCHAR(45) NOT NULL,
    `grd_delete`   TINYINT(1)  NOT NULL,
    `gmt_create`   DATETIME    NOT NULL,
    `gmt_modified` DATETIME    NOT NULL,
    PRIMARY KEY (`id`)
);
#菜单表
CREATE TABLE `civism-sso`.`sso_menu`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45) NOT NULL COMMENT '菜单名称',
    `grd_delete`   TINYINT(1)  NOT NULL,
    `gmt_create`   DATETIME    NOT NULL,
    `gmt_modified` DATETIME    NOT NULL,
    PRIMARY KEY (`id`)
);
#功能表
CREATE TABLE `civism-sso`.`sso_function`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45) NOT NULL,
    `url`          VARCHAR(45) NOT NULL,
    `method`       VARCHAR(45) NOT NULL,
    `menu_id`      INT(11)     NULL,
    `grd_delete`   TINYINT(1)  NOT NULL,
    `gmt_create`   DATETIME    NOT NULL,
    `gmt_modified` DATETIME    NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `civism-sso`.`sso_user`(`name`, `password`, `grd_delete`, `gmt_create`, `gmt_modified`) VALUES ('admin', '123456', 0, now(),now());
