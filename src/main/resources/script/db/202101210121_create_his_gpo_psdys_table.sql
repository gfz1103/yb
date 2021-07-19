CREATE TABLE `gpo_psdys` (
                             `XH` int NOT NULL COMMENT '配送单明细验收',
                             `YQBM` varchar(50) DEFAULT NULL COMMENT '药企编码 必填 市药事系统对药企的统编代码',
                             `PSMXBH` varchar(50) DEFAULT NULL COMMENT '配送明细编号 必填 市药事系统可唯一标识的配送明细编号',
                             `PSDTM` varchar(50) DEFAULT NULL COMMENT '配送单条码 必填 配送单条码',
                             `ZXSPBM` varchar(50) DEFAULT NULL COMMENT '商品统编代码 必填 统一发布的药品商品唯一标识编码',
                             `PSL` varchar(10) DEFAULT NULL COMMENT '配送数量 必填 该次配送的同一装箱且同一货品且同一生产批号按药品基础信息中包装单位名称所界定的货品配送数量',
                             `SCPH` varchar(100) DEFAULT NULL COMMENT '生产批号',
                             `YSJG` varchar(2) DEFAULT NULL COMMENT '验收结果 0验收不通过  1验收通过',
                             `YSJGBZ` varchar(255) DEFAULT NULL COMMENT '验收结果备注',
                             `C_TIME` datetime NOT NULL,
                             `C_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                             `C_UNAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                             PRIMARY KEY (`XH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;