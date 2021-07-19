CREATE TABLE `gpo_mryq` (
   `TBDM` varchar(50)  COMMENT '药品统编代码',
   `YQBM` varchar(50) DEFAULT NULL COMMENT '药企编码',
   `YQMC` varchar(250) DEFAULT NULL COMMENT '药企名称',
   PRIMARY KEY (`TBDM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;