package lajavel;

import io.javalin.http.Context;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class Responder {
    private final Map<String, Object> objects = new HashMap<>();

    /**
     * The method to get the object from the responder
     * the object must be defined before by the define method
     * @param clazz The name of the class
     * @return The object
     */
    @SuppressWarnings("unchecked")
    public <T> T fetch(Class<T> clazz) {
        Object callable = objects.get(clazz.getName());
        try {
            return (T) callable;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * The methods retain objects that will be passed to the view
     * @param object The object to be passed to the view
     */
    public void share(Object object) {
        objects.put(object.getClass().getName(), object);
    }

    public void share(Object... objects) { Arrays.stream(objects).forEach(this::share); }

    public abstract void respond(Context context);

}
