package cloud.devyard.rbapi.utils;

public final class ApiPaths {

    private ApiPaths() {} // prevent instantiation

    public static final String AUTH = "/api/auth";
    public static final String REGISTER = AUTH + "/register";
    public static final String VERIFY_EMAIL = AUTH + "/verify-email";
}
