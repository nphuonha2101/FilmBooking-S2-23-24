package com.filmbooking.enumsAndConstants.constants;

public class PathConstant {
    public static final String HTML_EMAILS_VI_PATH = "/templates/emails/vi/";
    public static final String HTML_EMAILS_EN_PATH = "/templates/emails/en/";
    public static final String GEOLITE2_CITY_DB_PATH = "src/main/resources/geoLite2Databases/GeoLite2-City.mmdb";

    public static String FACEBOOK_APP_ID = "2705451709613164";
    public static String FACEBOOK_APP_SECRET = "4ecec19046e49c89f0eecda5a20a882e";
    public static String FACEBOOK_REDIRECT_URL = "http://localhost:8080/facebook/login";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
}
