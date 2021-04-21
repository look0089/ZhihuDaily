package org.jzs.mybaseapp.section.main.entity;

import org.jzs.entity.BaseEntity;

import java.util.List;

/**
 * Created by Jzs on 2017/9/21 0021.
 */

public class WeatherEntity extends BaseEntity{

    public List<HeWeather5entity> HeWeather5;

    public static class HeWeather5entity {
        /**
         * basic : {"city":"厦门","cnty":"中国","id":"CN101230201","lat":"24.49047470","lon":"118.11022186","update":{"loc":"2017-09-21 15:46","utc":"2017-09-21 07:46"}}
         * now : {"cond":{"code":"101","txt":"多云"},"fl":"31","hum":"71","pcpn":"0","pres":"1007","tmp":"29","vis":"10","wind":{"deg":"106","dir":"东南风","sc":"3-4","spd":"12"}}
         * status : ok
         */

        public Basicentity basic;
        public Nowentity now;
        public String status;

        public static class Basicentity {
            /**
             * city : 厦门
             * cnty : 中国
             * id : CN101230201
             * lat : 24.49047470
             * lon : 118.11022186
             * update : {"loc":"2017-09-21 15:46","utc":"2017-09-21 07:46"}
             */

            public String city;
            public String cnty;
            public String id;
            public String lat;
            public String lon;
            public Updateentity update;

            public static class Updateentity {
                /**
                 * loc : 2017-09-21 15:46
                 * utc : 2017-09-21 07:46
                 */

                public String loc;
                public String utc;
            }
        }

        public static class Nowentity {
            /**
             * cond : {"code":"101","txt":"多云"}
             * fl : 31
             * hum : 71
             * pcpn : 0
             * pres : 1007
             * tmp : 29
             * vis : 10
             * wind : {"deg":"106","dir":"东南风","sc":"3-4","spd":"12"}
             */

            public Condentity cond;
            public String fl;
            public String hum;
            public String pcpn;
            public String pres;
            public String tmp;
            public String vis;
            public Windentity wind;

            public static class Condentity {
                /**
                 * code : 101
                 * txt : 多云
                 */

                public String code;
                public String txt;
            }

            public static class Windentity {
                /**
                 * deg : 106
                 * dir : 东南风
                 * sc : 3-4
                 * spd : 12
                 */

                public String deg;
                public String dir;
                public String sc;
                public String spd;
            }
        }
    }
}
