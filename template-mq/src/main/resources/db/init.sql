DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `u_name` varchar(35),
  `u_phone` varchar(35),
  `is_deleted` BOOLEAN NOT NULL DEFAULT '0' COMMENT '1: deleted, 0: normal',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
);

-- CREATE TABLE IF NOT EXISTS `ha_switch_record` (
--   `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `ha_name` varchar(255) NOT NULL DEFAULT 'default' COMMENT '集群名称',
--   `virtual_ip` varchar(32) NOT NULL DEFAULT 'default' COMMENT '虚拟VIP',
--   `switch_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '集群状态：0-正常',
--   `switch_desc` varchar(32) NOT NULL DEFAULT 'default' COMMENT '切换描述',
--   `switch_result` tinyint(2) NOT NULL DEFAULT '0' COMMENT '切换结果类型',
--   `is_deleted` BOOLEAN NOT NULL DEFAULT '0' COMMENT '1: deleted, 0: normal',
--   `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `last_modified_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
--   PRIMARY KEY (`id`)
-- );