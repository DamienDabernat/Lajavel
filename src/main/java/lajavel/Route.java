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
                case PATCH -> app.server.patch(routeName, context -> {
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
                case PATCH -> app.server.patch(routeName, context -> {
                    invokeController(context, controllerClass, methodName);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void invokeController(Context context, Class<?> controllerClass, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Response response = new Response(context);

        Controller controller = (Controller) controllerClass.getDeclaredConstructor().newInstance();
        Method controllerMethod = controllerClass.getMethod(methodName, Response.class);
        controllerMethod.invoke(controller, response);

    }

    protected static void invokeAction(Context context, Class<?> actionClass, Class<?> responderClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response(context);

        Responder responder = (Responder) responderClass.getDeclaredConstructor().newInstance();
        Action action = (Action) actionClass.getDeclaredConstructor(Responder.class).newInstance(responder);
        action.execute(response);
    }

}