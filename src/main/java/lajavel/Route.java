package lajavel;


import io.javalin.http.Context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Route {

    public static void register(HttpVerb httpVerb, String routeName, Class<?> actionClass, Class<?> responderClass) {
        Application app = Application.getInstance();

        try {
            switch (httpVerb) {
                case GET -> app.server.get(routeName, context -> {
                    invokeAction(context, actionClass, responderClass);
                });
                case POST -> app.server.post(routeName, context -> {
                    invokeAction(context, actionClass, responderClass);
                });
                case PUT -> app.server.put(routeName, context -> {
                    invokeAction(context, actionClass, responderClass);
                });
                case DELETE -> app.server.delete(routeName, context -> {
                    invokeAction(context, actionClass, responderClass);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void register(HttpVerb httpVerb, String routeName, Class<?> controllerClass, String methodName) {
        Application app = Application.getInstance();

        try {
            switch (httpVerb) {
                case GET -> app.server.get(routeName, context -> {
                    invokeController(context, controllerClass, methodName);
                });
                case POST -> app.server.post(routeName, context -> {
                    invokeController(context, controllerClass, methodName);
                });
                case PUT -> app.server.put(routeName, context -> {
                    invokeController(context, controllerClass, methodName);
                });
                case DELETE -> app.server.delete(routeName, context -> {
                    invokeController(context, controllerClass, methodName);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void invokeController(Context context, Class<?> controllerClass, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Controller controller = (Controller) controllerClass.getDeclaredConstructor().newInstance();
        Method controllerMethod = controllerClass.getMethod(methodName, Context.class);
        controllerMethod.invoke(controller, context);
    }

    protected static void invokeAction(Context context, Class<?> actionClass, Class<?> responderClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Responder responder = (Responder) responderClass.getDeclaredConstructor().newInstance();
        Action action = (Action) actionClass.getDeclaredConstructor().newInstance();
        action.execute(context, responder);
    }

}