package de.jo.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

/**
 * @author CommandJoo 25.04.2023
 * @Project ProgrammingLanguage
 */
public class Reflections {

    public static <T>T construct(Class<T> clazz, Class<?>[] types, Object[] params) {
        try {
            Constructor<T> constr = clazz.getConstructor(types);
            if(!constr.isAccessible()) {
                constr.setAccessible(true);

                T obj = constr.newInstance(params);
                constr.setAccessible(false);
                return obj;
            }else{
                return constr.newInstance(params);
            }
        } catch(Exception ex) {
            Error.call("Could not construct object '"+clazz.getSimpleName()+"'!");
        }
        return null;
    }

    /**
     * The function returns an array of all the public fields of a given class.
     *
     * @param clazz The parameter "clazz" is a Class object that represents a Java class. The method "getFields" returns an
     * array of Field objects that represent the public fields of the specified class or interface.
     * @return The method `getFields` returns an array of `Field` objects representing the public fields of the specified
     * class or interface, including those declared by the class or interface and those inherited from superclasses and
     * superinterfaces.
     */
    public static Field[] getFields(Class<?> clazz) {
        return clazz.getFields();
    }

    /**
     * The function returns an array of all the declared fields of a given class.
     *
     * @param clazz The parameter "clazz" is a Class object that represents a Java class. The method "getDeclaredFields"
     * returns an array of Field objects that represent the declared fields of the specified class.
     * @return The method `getDeclaredFields` returns an array of `Field` objects that represent the declared fields of the
     * class or interface represented by the specified `Class` object.
     */
    public static Field[] getDeclaredFields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    /**
     * The function returns an array of all the public methods of a given class.
     *
     * @param clazz The parameter "clazz" is a Class object that represents a Java class. The method "getMethods" returns
     * an array of Method objects that represent all the public methods of the class or interface represented by the Class
     * object "clazz".
     * @return The method `getMethods` returns an array of `Method` objects representing all the public methods of the
     * class or interface represented by the specified `Class` object, including those declared by the class or interface
     * and those inherited from superclasses and superinterfaces.
     */
    public static Method[] getMethods(Class<?> clazz) {
        return clazz.getMethods();
    }

    /**
     * The function returns an array of all the declared methods in a given class.
     *
     * @param clazz The parameter "clazz" is a Class object that represents a Java class. The method "getDeclaredMethods"
     * returns an array of Method objects that represent the declared methods of the specified class.
     * @return The method `getDeclaredMethods` returns an array of `Method` objects that represent all the declared methods
     * of the class or interface represented by the specified `Class` object.
     */
    public static Method[] getDeclaredMethods(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }

    /**
     * This function returns a field with the given name from the specified class.
     *
     * @param clazz The parameter "clazz" is a Class object representing the class whose field is being accessed.
     * @param name The name of the field that you want to retrieve from the given class.
     * @return This method returns a Field object representing the public field with the specified name in the specified
     * class.
     */
    public static Field getField(Class<?> clazz, String name) {
        try {
            return clazz.getField(name);
        } catch (NoSuchFieldException e) {
            Error.call(e);
            return null;
        }
    }

    /**
     * This function returns a declared field with the given name from the specified class.
     *
     * @param clazz The parameter "clazz" is a Class object representing the class whose field is being accessed.
     * @param name The name of the field that you want to retrieve from the given class.
     * @return This method returns a Field object representing the public field with the specified name in the specified
     * class.
     */
    public static Field getDeclaredField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            Error.call(e);
            return null;
        }
    }

    /**
     * This function returns a method with the specified name and parameters from a given class, or null if it does not
     * exist.
     *
     * @param clazz The class object for which we want to retrieve the method.
     * @param name The name of the method that you want to retrieve.
     * @return The method `getMethod` returns a `Method` object that represents the specified method of the given class,
     * with the specified parameter types. If the method does not exist, it returns `null` and logs the error using the
     * `Error.call` method.
     */
    public static Method getMethod(Class<?> clazz, String name, Class<?>... params) {
        try {
            return clazz.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            Error.call(e);
            return null;
        }
    }

    /**
     * This function returns a declared method of a class with the given name and parameter types, or null if it does not
     * exist.
     *
     * @param clazz The class object on which the method is declared.
     * @param name The name of the method that you want to retrieve.
     * @return The method `getDeclaredMethod` returns a `Method` object that represents the method with the specified name
     * and parameter types declared by the class or interface represented by the `Class` object `clazz`. If the method is
     * not found, it returns `null` after logging the error using the `Error.call` method.
     */
    public static Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... params) {
        try {
            return clazz.getDeclaredMethod(name, params);
        } catch (NoSuchMethodException e) {
            Error.call(e);
            return null;
        }
    }

    /**
     * The function returns an array of parameters for a given method.
     *
     * @param method The "method" parameter is a java.lang.reflect.Method object representing a method in a class. It is
     * used to retrieve information about the method, such as its name, return type, and parameters.
     * @return The method `getParameters` returns an array of `Parameter` objects, which represent the parameters of the
     * given `Method` object.
     */
    public static Parameter[] getParameters(Method method) {
        return method.getParameters();
    }

    public static ArrayList<String> getStringParameters(Method method) {
        ArrayList<String> params = new ArrayList<>();
        for(Parameter param : getParameters(method)) {
            params.add(param.getName());
        }
        return params;
    }

    /**
     * This function returns the value of a field in an object.
     *
     * @param f The parameter "f" is a Field object, which represents a field of a class. It can be used to get or set the
     * value of the field, as well as to get information about the field such as its name, type, and modifiers.
     * @param obj The "obj" parameter is an instance of the class that contains the field "f". The method "getFieldValue"
     * is used to get the value of the field "f" from the object "obj".
     * @return The method `getFieldValue` returns the value of the field `f` in the object `obj`.
     */
    public static Object getFieldValue(Field f, Object obj) {
        try {
            boolean isAccessible = f.isAccessible();
            if(!isAccessible) {
                f.setAccessible(true);
            }
            Object ret = f.get(obj);
            if(!isAccessible) {
                f.setAccessible(false);
            }
            return ret;
        } catch (IllegalAccessException e) {
            Error.call(e);
            return null;
        }
    }

    /**
     * This function sets the value of a field in an object using reflection in Java.
     *
     * @param f The Field object representing the field whose value needs to be set. A Field object provides information
     * about, and dynamic access to, a single field of a class or an interface.
     * @param obj The "obj" parameter is an object instance on which the field value is being set.
     * @param value The value that we want to set for the given field.
     */
    public static void setFieldValue(Field f, Object obj, Object value) {
        try {
            boolean isAccessible = f.isAccessible();
            if(!isAccessible) {
                f.setAccessible(true);
            }
            f.set(obj, value);
            if(!isAccessible) {
                f.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            Error.call(e);
        }
    }

}
