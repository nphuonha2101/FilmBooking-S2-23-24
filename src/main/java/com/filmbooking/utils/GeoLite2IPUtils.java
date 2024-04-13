package com.filmbooking.utils;

import com.filmbooking.enumsAndConstants.constants.PathConstant;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Continent;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Postal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

public class GeoLite2IPUtils {
    private static GeoLite2IPUtils instance = null;
    private final DatabaseReader databaseReader;

    private GeoLite2IPUtils() throws IOException {
        InputStream geoLite2CityDB = this.getClass().getResourceAsStream(PathConstant.GEOLITE2_CITY_DB_PATH_DEPLOYMENT);
        assert geoLite2CityDB != null;
        if (geoLite2CityDB.available() == 0) {
            throw new RuntimeException(new FileNotFoundException("GeoLite2-City.mmdb not available"));
        }

        databaseReader = new DatabaseReader.Builder(geoLite2CityDB).build();
    }

    public static GeoLite2IPUtils getInstance()  {
        try {
            if (instance == null) {
                instance = new GeoLite2IPUtils();
            }
            return instance;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get country response from ip using GeoLite2-City.mmdb
     * @param ip ip address
     * @return CountryResponse
     */
    private CityResponse getCityResponse(String ip)  {
        try {
            return databaseReader.city(InetAddress.getByName(ip));
        } catch (IOException | GeoIp2Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Country getCountry(String ip) {
        return getCityResponse(ip).getCountry();
    }

    public Postal getPostal(String ip) {
        return getCityResponse(ip).getPostal();
    }

    public Continent getContinent(String ip) {
        return getCityResponse(ip).getContinent();
    }

    public City getCity(String ip) {
        return getCityResponse(ip).getCity();
    }


    public static void main(String[] args) {
        GeoLite2IPUtils geoLite2IPUtils = GeoLite2IPUtils.getInstance();
        System.out.println(geoLite2IPUtils.getCountry("2402:800:fc96:2db5:28b5:ef27:f50e:ce6a"));
        System.out.println(geoLite2IPUtils.getCity("2402:800:fc96:2db5:28b5:ef27:f50e:ce6a"));
        System.out.println(geoLite2IPUtils.getContinent("2402:800:fc96:2db5:28b5:ef27:f50e:ce6a"));
        System.out.println(geoLite2IPUtils.getPostal("2402:800:fc96:2db5:28b5:ef27:f50e:ce6a"));

    }
}
