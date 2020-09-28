package cadastrocursos.config;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {

    public static final String SECRET = "ragirfernando";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "  /users/sign-up";
    public static final long  EXPIRATION_TIME = 86400000L;

    /*public static void main(String[] args) {
        System.out.println(TimeUnit.MILLISECONDS.convert(1 ,TimeUnit.DAYS));
    }*/
}
