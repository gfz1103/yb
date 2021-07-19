ALTER TABLE `gpo_ddmx`
    ADD COLUMN `bzzh` int NULL COMMENT '转换包装系数，入库数量=采购数量/转换包装系数' AFTER `C_UNAME`;