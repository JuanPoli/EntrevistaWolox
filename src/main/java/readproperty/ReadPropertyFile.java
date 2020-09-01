package readproperty;

import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;
import static java.lang.System.getenv;

public enum ReadPropertyFile {
    TEST_PROPERTIES,
    ;
    private Properties properties = new Properties();
    private static final String ENVIRONMENT = "environment";
    private static final String DEFAULT_ENVIRONMENT = "qa";
    private static final String BASE_URL = "base.url";
    private static final String IMPLICIT_WAIT = "implicit.wait";
    private static final String EXPLICIT_WAIT = "explicit.wait";
    private static final String PAGE_LOAD_TIMEOUT = "page.load.timeout";
    private static final String SCRIPT_TIMEOUT = "script.timeout";
    private static final String USER_ENVIRONMENT = "user";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "pass";
    private static final String IMAGE_PATH = "imagepath";

    public String getBaseUrl() {
        return getTestProperty(BASE_URL, DEFAULT_ENVIRONMENT);
    }

    public Long getImplicitWait() {
        return Long.valueOf(getTestProperty(IMPLICIT_WAIT, DEFAULT_ENVIRONMENT));
    }

    public Long getExplicitWait() {
        return Long.valueOf(getTestProperty(EXPLICIT_WAIT, DEFAULT_ENVIRONMENT));
    }

    public Long getPageLoadTimeout() {
        return Long.valueOf(getTestProperty(PAGE_LOAD_TIMEOUT, DEFAULT_ENVIRONMENT));
    }

    public Long getScriptTimeout() {
        return Long.valueOf(getTestProperty(SCRIPT_TIMEOUT, DEFAULT_ENVIRONMENT));
    }

    public String getUsername() {
        return getTestProperty(USERNAME, USER_ENVIRONMENT);
    }

    public String getPassword() {
        return getTestProperty(PASSWORD, USER_ENVIRONMENT);
    }

    public String getImagePath() {
        return getTestProperty(IMAGE_PATH, USER_ENVIRONMENT);
    }

    private String getTestProperty(String propertyName, String file) {
        try {
            StringBuilder propertyFile = new StringBuilder();
            String environment = get(ENVIRONMENT);
            if (environment == null) {
                environment = file;
            }
            propertyFile.append(environment).append(".properties");
            properties.clear();
            properties.load(this.getClass().getClassLoader().getResourceAsStream(propertyFile.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(propertyName);
    }

    private String get(String key) {
        String environment = getProperty(key);
        if (environment == null) {
            environment = getenv(key);
        }
        return environment;
    }


}
