package com.szc.weather.bean;

import java.util.List;

/**
 * Created by szc on 17-2-25.
 */

public class WeatherBean {

    /**
     * desc : OK
     * status : 1000
     * data : {"wendu":"12","ganmao":"昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。","forecast":[{"fengxiang":"北风","fengli":"微风级","high":"高温 13℃","type":"多云","low":"低温 4℃","date":"25日星期六"},{"fengxiang":"北风","fengli":"微风级","high":"高温 16℃","type":"晴","low":"低温 6℃","date":"26日星期天"},{"fengxiang":"北风","fengli":"微风级","high":"高温 16℃","type":"多云","low":"低温 8℃","date":"27日星期一"},{"fengxiang":"北风","fengli":"微风级","high":"高温 12℃","type":"阵雨","low":"低温 6℃","date":"28日星期二"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 16℃","type":"多云","low":"低温 6℃","date":"1日星期三"}],"yesterday":{"fl":"微风","fx":"东北风","high":"高温 10℃","type":"阴","low":"低温 4℃","date":"24日星期五"},"aqi":"87","city":"长沙"}
     */

    private String desc;
    private int status;
    private DataBean data;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * wendu : 12
         * ganmao : 昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。
         * forecast : [{"fengxiang":"北风","fengli":"微风级","high":"高温 13℃","type":"多云","low":"低温 4℃","date":"25日星期六"},{"fengxiang":"北风","fengli":"微风级","high":"高温 16℃","type":"晴","low":"低温 6℃","date":"26日星期天"},{"fengxiang":"北风","fengli":"微风级","high":"高温 16℃","type":"多云","low":"低温 8℃","date":"27日星期一"},{"fengxiang":"北风","fengli":"微风级","high":"高温 12℃","type":"阵雨","low":"低温 6℃","date":"28日星期二"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 16℃","type":"多云","low":"低温 6℃","date":"1日星期三"}]
         * yesterday : {"fl":"微风","fx":"东北风","high":"高温 10℃","type":"阴","low":"低温 4℃","date":"24日星期五"}
         * aqi : 87
         * city : 长沙
         */

        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private String aqi;
        private String city;
        private List<ForecastBean> forecast;

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * fl : 微风
             * fx : 东北风
             * high : 高温 10℃
             * type : 阴
             * low : 低温 4℃
             * date : 24日星期五
             */

            private String fl;
            private String fx;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }

        public static class ForecastBean {
            /**
             * fengxiang : 北风
             * fengli : 微风级
             * high : 高温 13℃
             * type : 多云
             * low : 低温 4℃
             * date : 25日星期六
             */

            private String fengxiang;
            private String fengli;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
