package com.buit.his.shyb.upload.status;

/**
 * @Author weijing
 * @Date 2020-09-23 19:10
 * @Description
 **/
public class UpLoadStatus {
    public enum lyfs {
        /*** 离院方式*/
        LYFS_YZLY("1","医嘱离院"),
        LYFS_YZZY("2","医嘱转院"),
        LYFS_YZZWSY("3","医嘱转社区卫生服务机构/乡镇卫生院"),
        LYFS_FYZLY("4","非医嘱离院"),
        LYFS_SW("5","死亡"),
        LYFS_QT("9","其他"),
        ;
        /*** 字段key值*/
        private String code;

        /*** 字段文本值*/
        private String name;

        lyfs(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    public enum Flbm {
        /*** 文件分类编码 */
        BASY("A","病案首页"),
        ZYZF_YBFSS("B","住院自费及医保非实时病人交易明细数据"),
        CYXJ("C","出院小结"),
        CYYZXX("D","出院医嘱信息"),
        MZZF_YBFSS("E","门诊自费及医保非实时病人交易明细数据"),
        MZCF("F","门诊处方信息"),
        SYSJYBG("G","实验室检验报告"),
        YXYXJCBG("H","医学影像检查报告"),
        ;
        /*** 字段key值*/
        private String code;

        /*** 字段文本值*/
        private String name;

        Flbm(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
