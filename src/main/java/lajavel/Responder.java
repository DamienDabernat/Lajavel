package lajavel;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public abstract class Responder {

    protected Context context;

    private final Map<String, Object> objects = new HashMap<>();

    /**
     * The method to get the object from the responder
     * the object must be defined before by the define method
     * @param clazz The name of the class
     * @return The object
     */
    @SuppressWarnings("unchecked")
    public <T> T call(Class<T> clazz) {
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
    public void define(Object object) {
        objects.put(object.getClass().getName(), object);
    }

    public Responder(Context context) {
        this.context = context;
    }

    public abstract void respond();

}
