package org.xiangbalao.jsontest.bean;

/**
 * Created by longtaoge on 2015/11/4.
 */
public class JsonData   {


    /**
     * count : 44
     * cuxiao : 优唻，优来，又来，由来，友来
     * dizhi : 建外SOHO东区9号楼底商
     * gz_counts : 53
     * id : 1
     * isgz : 0
     * isopen : 1
     * jd : 116.412895000000
     * juli : 106140
     * linkphone : 13121376887
     * p_sale : 8
     * pic : http://123.57.69.84:8888/images/ijdj@2x.png
     * pingfen : 10.0
     * pj_counts : 7
     * shopname : DailyClick
     * wd : 39.898591000000
     * xinxi :
     * yingye : 早6点-晚9点
     */

    private DataEntity data;
    /**
     * data : {"count":"44","cuxiao":"优唻，优来，又来，由来，友来","dizhi":"建外SOHO东区9号楼底商","gz_counts":"53","id":"1","isgz":"0","isopen":"1","jd":"116.412895000000","juli":"106140","linkphone":"13121376887","p_sale":"8","pic":"http://123.57.69.84:8888/images/ijdj@2x.png","pingfen":"10.0","pj_counts":"7","shopname":"DailyClick","wd":"39.898591000000","xinxi":"","yingye":"早6点-晚9点"}
     * state : 1
     */

    private String state;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setState(String state) {
        this.state = state;
    }

    public DataEntity getData() {
        return data;
    }

    public String getState() {
        return state;
    }

    public static class DataEntity {
        private String count;
        private String cuxiao;
        private String dizhi;
        private String gz_counts;
        private String id;
        private String isgz;
        private String isopen;
        private String jd;
        private String juli;
        private String linkphone;
        private String p_sale;
        private String pic;
        private String pingfen;
        private String pj_counts;
        private String shopname;
        private String wd;
        private String xinxi;
        private String yingye;

        public void setCount(String count) {
            this.count = count;
        }

        public void setCuxiao(String cuxiao) {
            this.cuxiao = cuxiao;
        }

        public void setDizhi(String dizhi) {
            this.dizhi = dizhi;
        }

        public void setGz_counts(String gz_counts) {
            this.gz_counts = gz_counts;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIsgz(String isgz) {
            this.isgz = isgz;
        }

        public void setIsopen(String isopen) {
            this.isopen = isopen;
        }

        public void setJd(String jd) {
            this.jd = jd;
        }

        public void setJuli(String juli) {
            this.juli = juli;
        }

        public void setLinkphone(String linkphone) {
            this.linkphone = linkphone;
        }

        public void setP_sale(String p_sale) {
            this.p_sale = p_sale;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setPingfen(String pingfen) {
            this.pingfen = pingfen;
        }

        public void setPj_counts(String pj_counts) {
            this.pj_counts = pj_counts;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public void setWd(String wd) {
            this.wd = wd;
        }

        public void setXinxi(String xinxi) {
            this.xinxi = xinxi;
        }

        public void setYingye(String yingye) {
            this.yingye = yingye;
        }

        public String getCount() {
            return count;
        }

        public String getCuxiao() {
            return cuxiao;
        }

        public String getDizhi() {
            return dizhi;
        }

        public String getGz_counts() {
            return gz_counts;
        }

        public String getId() {
            return id;
        }

        public String getIsgz() {
            return isgz;
        }

        public String getIsopen() {
            return isopen;
        }

        public String getJd() {
            return jd;
        }

        public String getJuli() {
            return juli;
        }

        public String getLinkphone() {
            return linkphone;
        }

        public String getP_sale() {
            return p_sale;
        }

        public String getPic() {
            return pic;
        }

        public String getPingfen() {
            return pingfen;
        }

        public String getPj_counts() {
            return pj_counts;
        }

        public String getShopname() {
            return shopname;
        }

        public String getWd() {
            return wd;
        }

        public String getXinxi() {
            return xinxi;
        }

        public String getYingye() {
            return yingye;
        }
    }
}
