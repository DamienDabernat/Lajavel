package lajavel;

import app.domain.entity.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {


    public static String make(String viewName) {
        return View.make(viewName, (Map.Entry<String, Object>) null);
    }

    // https://stackoverflow.com/a/70189780
    @SafeVarargs
    public static String make(String viewName, Map.Entry<String, Object>... entries) {

        String viewContent = getViewContent(viewName);

        if(entries == null) {
            return viewContent;
        }

        Matcher m = Pattern.compile("\\{\\{([^{{}}]*)\\}\\}").matcher(viewContent);

        // Creating the target string buffer
        StringBuffer sb = new StringBuffer();

        while (m.find()) {

            String rawStringOfAnObject = m.group(1).replaceAll("\\s+",""); // remove space
            String[] objectAndProperty = rawStringOfAnObject.split("\\."); // split by dot

            if(objectAndProperty.length <= 1) {
                throw new RuntimeException("You must specify an object and a property in your html");
            }

            String objectName = objectAndProperty[0]; //Dans le HTML on a {{toto.firstname}} -> toto
            String propertyName = objectAndProperty[1]; //Dans le HTML on a {{toto.firstname}} -> firstname

            for (Map.Entry<String, Object> entry : entries) {
                if(entry.getKey().equals(objectName)) {
                    m.appendReplacement(sb, View.getValueOf(propertyName, entry.getValue()));
                    break;
                }
            }
        }

        m.appendTail(sb);

        return sb.toString();
    }

    public static String getValueOf(String propertyName, Object object) {

            boolean isMethod = false;
            if(propertyName.endsWith("()")) {
                isMethod = true;
                propertyName = propertyName.replace("()", "");
            }

            if(!isMethod) {
                Object propertyValue = getProperty(Object.class, object, propertyName);
                return propertyValue.toString();
            } else { // it's a method not a field
                return getMethod(String.class, object, propertyName);
            }
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
            throw new RuntimeException(e);
        }

        return clazz.cast(returnValue);
    }



}
