package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        for (Method method : Address.class.getDeclaredMethods()) {

            Class<?> returnType = method.getReturnType();
            if (method.isAnnotationPresent(Inspect.class)) {
                System.out.println(
                        "Method " + method.getName() + " returns a value of type " + returnType.getSimpleName()
                );
            }
        }
    }
}
