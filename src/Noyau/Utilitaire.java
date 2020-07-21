package Noyau;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Utilitaire {
    public static boolean isSetter(Method method, String attribut) {
        boolean result = (method.getName().startsWith("set"))
                && (method.getName().toLowerCase().substring(3).equals(attribut.toLowerCase()))
                && (method.getParameterTypes().length == 1) && Modifier.isPublic(method.getModifiers()) &&
                method.getReturnType().equals(void.class);
        return result;
    }
}


