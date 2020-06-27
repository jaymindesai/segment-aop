package com.segment.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class that encapsulates application properties
 *
 * @author  Jaymin Desai
 */
// TODO: Add test cases
public class PropUtils {
    private static final String ENV_PROPERTY_PLACEHOLDER_PATTERN = "\\$\\{(\\w+)\\}|\\$(\\w+)";
    private static final String PROP_FILE = "application.properties";
    private static final Properties PROPS;

    static {
        PROPS = new Properties();
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_FILE)) {
            PROPS.load(input);
            PROPS.forEach(PropUtils::resolveEnvironmentVariables);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void resolveEnvironmentVariables(Object key, Object value) {
        if (value != null) {
            Pattern pattern = Pattern.compile(ENV_PROPERTY_PLACEHOLDER_PATTERN);
            Matcher matcher = pattern.matcher((String) value);
            StringBuffer buffer = new StringBuffer();
            while (matcher.find()) {
                String envName = matcher.group(1) == null ? matcher.group(2) : matcher.group(1);
                String envValue = System.getenv(envName);
                matcher.appendReplacement(buffer, envValue == null ? "" : Matcher.quoteReplacement(envValue));
            }
            matcher.appendTail(buffer);
            PROPS.setProperty(String.valueOf(key), buffer.toString());
        }
    }

    public static String getProperty(String key) {
        return PROPS.getProperty(key);
    }
}
