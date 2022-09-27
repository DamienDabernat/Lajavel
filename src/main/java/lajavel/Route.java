package lajavel;


import io.javalin.http.Context;

public class Route {

    public static void get(String routeName, String actionName, String responderName) {
        Application app = Application.getInstance();

        try {
            app.server.get(routeName, context -> {
                //Log.info();
                Class<?> clazz = Class.forName(responderName);
                Responder responder = (Responder) clazz.getDeclaredConstructor(Context.class).newInstance(context);
                Action action = getAction(actionName, context, responder);
                action.execute(context);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void post(String routeName, String actionName, String responderName) {
        Application app = Application.getInstance();

        try {
            app.server.post(routeName, context -> {
                Class<?> clazz = Class.forName(responderName);
                Responder responder = (Responder) clazz.getDeclaredConstructor(Context.class).newInstance(context);
                Action action = getAction(actionName, context, responder);
                action.execute(context);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    protected static Action getAction(String className, Context context, Responder responder) {
        try {
            Class<?> clazz = Class.forName(className);
            return (Action) clazz.getDeclaredConstructor(Responder.class, Context.class).newInstance(responder, context);
        } catch (Exception e) {
            throw new RuntimeException("Action Not found : " + e.getMessage());
        }
    }
}