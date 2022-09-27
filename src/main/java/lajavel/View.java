package lajavel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {


    public static String make(String viewName) {
        return View.make(null, viewName);
    }

    // https://stackoverflow.com/a/70189780
    public static String make(Object obj, String viewName) {

        String viewContent = getViewContent(viewName);

        if(obj == null) {
            return viewContent;
        }

        Matcher m = Pattern.compile("\\{\\{([^{{}}]*)\\}\\}").matcher(viewContent);

        // Creating the target string buffer
        StringBuilder sb = new StringBuilder();

        while (m.find()) {
            String oneProperty = m.group(1).replaceAll("\\s+","");
            Object propertyValue = getProperty(Object.class, obj, oneProperty);
            if(propertyValue != null) {
                m.appendReplacement(sb, propertyValue.toString());
            } else {
                m.appendReplacement(sb, getMethod(String.class, obj, oneProperty));
            }

        }

        m.appendTail(sb);

        return sb.toString();
    }

    public static String getViewContent(String viewName) {

        // Get the file url, not working in JAR file.
        URL resource = View.class.getClassLoader().getResource("views/" + viewName + ".view");

        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            // failed if files have whitespaces or special characters
            try {
                File file = new File(resource.toURI());
                // Java 11 added the readString() method to read small files as a String, preserving line terminators:
                return Files.readString(file.toPath(), StandardCharsets.UTF_8);
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> T getProperty(Class<T> clazz, Object obj, String property) {
        Object returnValue = null;

        try {
            Field field = obj.getClass().getDeclaredField(property);
            field.setAccessible(true);
            returnValue = clazz.cast(field.get(obj));
        }
        catch (Exception e) {
            // Do nothing, we'll return the default value
        }

        return clazz.cast(returnValue);
    }

    public static <T> T getMethod(Class<T> clazz, Object obj, String methodName) {
        Object returnValue = null;

        try {
            Method method = obj.getClass().getMethod(methodName);
            method.setAccessible(true);
            returnValue = clazz.cast(method.invoke(obj));
        }
        catch (Exception e) {
            // Do nothing, we'll return the default value
        }

        return clazz.cast(returnValue);
    }
}
