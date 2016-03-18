package com.md.test.testmd.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public class Weather implements Serializable{

    @SerializedName("HeWeather data service 3.0")@Expose
    public List<WeatherInfoEntity> WeatherInfo;

    public static class WeatherInfoEntity {

        @Override
        public String toString() {
            return "WeatherInfoEntity{" +
                    "aqi=" + aqi +
                    ", basic=" + basic +
                    ", now=" + now +
                    ", status='" + status + '\'' +
                    ", suggestion=" + suggestion +
                    ", dailyForecast=" + dailyForecast +
                    ", hourlyForecast=" + hourlyForecast +
                    '}';
        }

        @SerializedName("aqi")
        public AqiEntity aqi;
        @SerializedName("basic")
        public BasicEntity basic;
        @SerializedName("now")
        public NowEntity now;
        @SerializedName("status")
        public String status;
        @SerializedName("suggestion")
        public SuggestionEntity suggestion;
        @SerializedName("daily_forecast")
        public List<DailyForecastEntity> dailyForecast;
        @SerializedName("hourly_forecast")
        public List<HourlyForecastEntity> hourlyForecast;

        public static class AqiEntity {
            @SerializedName("city")
            public CityEntity city;

            public static class CityEntity {
                @SerializedName("aqi")
                public String aqi;
                @SerializedName("co")
                public String co;
                @SerializedName("no2")
                public String no2;
                @SerializedName("o3")
                public String o3;
                @SerializedName("pm10")
                public String pm10;
                @SerializedName("pm25")
                public String pm25;
                @SerializedName("qlty")
                public String qlty;
                @SerializedName("so2")
                public String so2;
            }
        }

        public static class BasicEntity {
            @SerializedName("city")
            public String city;
            @SerializedName("cnty")
            public String cnty;
            @SerializedName("id")
            public String id;
            @SerializedName("lat")
            public String lat;
            @SerializedName("lon")
            public String lon;
            @SerializedName("update")
            public UpdateEntity update;

            public static class UpdateEntity {
                @SerializedName("loc")
                public String loc;
                @SerializedName("utc")
                public String utc;
            }
        }

        public static class NowEntity {
            @SerializedName("cond")
            public CondEntity cond;
            @SerializedName("fl")
            public String fl;
            @SerializedName("hum")
            public String hum;
            @SerializedName("pcpn")
            public String pcpn;
            @SerializedName("pres")
            public String pres;
            @SerializedName("tmp")
            public String tmp;
            @SerializedName("vis")
            public String vis;
            @SerializedName("wind")
            public WindEntity wind;

            public static class CondEntity {
                @SerializedName("code")
                public String code;
                @SerializedName("txt")
                public String txt;
            }

            public static class WindEntity {
                @SerializedName("deg")
                public String deg;
                @SerializedName("dir")
                public String dir;
                @SerializedName("sc")
                public String sc;
                @SerializedName("spd")
                public String spd;
            }
        }

        public static class SuggestionEntity {
            @SerializedName("comf")
            public ComfEntity comf;
            @SerializedName("cw")
            public CwEntity cw;
            @SerializedName("drsg")
            public DrsgEntity drsg;
            @SerializedName("flu")
            public FluEntity flu;
            @SerializedName("sport")
            public SportEntity sport;
            @SerializedName("trav")
            public TravEntity trav;
            @SerializedName("uv")
            public UvEntity uv;

            public static class ComfEntity {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class CwEntity {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class DrsgEntity {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class FluEntity {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class SportEntity {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class TravEntity {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class UvEntity {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }
        }

        public static class DailyForecastEntity {
            @SerializedName("astro")
            public AstroEntity astro;
            @SerializedName("cond")
            public CondEntity cond;
            @SerializedName("date")
            public String date;
            @SerializedName("hum")
            public String hum;
            @SerializedName("pcpn")
            public String pcpn;
            @SerializedName("pop")
            public String pop;
            @SerializedName("pres")
            public String pres;
            @SerializedName("tmp")
            public TmpEntity tmp;
            @SerializedName("vis")
            public String vis;
            @SerializedName("wind")
            public WindEntity wind;

            public static class AstroEntity {
                @SerializedName("sr")
                public String sr;
                @SerializedName("ss")
                public String ss;
            }

            public static class CondEntity {
                @SerializedName("code_d")
                public String codeD;
                @SerializedName("code_n")
                public String codeN;
                @SerializedName("txt_d")
                public String txtD;
                @SerializedName("txt_n")
                public String txtN;
            }

            public static class TmpEntity {
                @SerializedName("max")
                public String max;
                @SerializedName("min")
                public String min;
            }

            public static class WindEntity {
                @SerializedName("deg")
                public String deg;
                @SerializedName("dir")
                public String dir;
                @SerializedName("sc")
                public String sc;
                @SerializedName("spd")
                public String spd;
            }
        }

        public static class HourlyForecastEntity {
            @SerializedName("date")
            public String date;
            @SerializedName("hum")
            public String hum;
            @SerializedName("pop")
            public String pop;
            @SerializedName("pres")
            public String pres;
            @SerializedName("tmp")
            public String tmp;
            @SerializedName("wind")
            public WindEntity wind;

            public static class WindEntity {
                @SerializedName("deg")
                public String deg;
                @SerializedName("dir")
                public String dir;
                @SerializedName("sc")
                public String sc;
                @SerializedName("spd")
                public String spd;
            }
        }
    }
}
