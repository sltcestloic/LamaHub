package fr.taeron.lamahub.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;

public class NMSUtils{
	
    private static String version;
    
    static {
        NMSUtils.version = getVersion();
    }
    
    public static String getVersion() {
        if (NMSUtils.version != null) {
            return NMSUtils.version;
        }
        final String name = Bukkit.getServer().getClass().getPackage().getName();
        final String version = String.valueOf(name.substring(name.lastIndexOf(46) + 1)) + ".";
        return version;
    }
    
    public static Object getHandle(final Object o) {
        try {
            return getMethod(o.getClass(), "getHandle", (Class<?>[])new Class[0]).invoke(o, new Object[0]);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }
    
    public static Method getMethod(final Class<?> clazz, final String name, final Class<?>... args) {
        Method[] methods;
        for (int length = (methods = clazz.getMethods()).length, i = 0; i < length; ++i) {
            final Method m = methods[i];
            if (m.getName().equals(name) && (args.length == 0 || ClassListEqual(args, m.getParameterTypes()))) {
                m.setAccessible(true);
                return m;
            }
        }
        Method[] declaredMethods;
        for (int length2 = (declaredMethods = clazz.getDeclaredMethods()).length, j = 0; j < length2; ++j) {
            final Method m = declaredMethods[j];
            if (m.getName().equals(name) && (args.length == 0 || ClassListEqual(args, m.getParameterTypes()))) {
                m.setAccessible(true);
                return m;
            }
        }
        return null;
    }
    
    public static Field getField(final Class<?> clazz, final String name) {
        Field[] fields;
        for (int length = (fields = clazz.getFields()).length, i = 0; i < length; ++i) {
            final Field f = fields[i];
            if (f.getName().equalsIgnoreCase(name)) {
                f.setAccessible(true);
                return f;
            }
        }
        Field[] declaredFields;
        for (int length2 = (declaredFields = clazz.getDeclaredFields()).length, j = 0; j < length2; ++j) {
            final Field f = declaredFields[j];
            if (f.getName().equalsIgnoreCase(name)) {
                f.setAccessible(true);
                return f;
            }
        }
        try {
            throw new Exception("No such field > " + name + " in class " + clazz.getSimpleName());
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static boolean ClassListEqual(final Class<?>[] l1, final Class<?>[] l2) {
        boolean equal = true;
        if (l1.length != l2.length) {
            return false;
        }
        for (int i = 0; i < l1.length; ++i) {
            if (l1[i] != l2[i]) {
                equal = false;
                break;
            }
        }
        return equal;
    }
    
    public static void setField(final Object instance, final Field f, final Object value) {
        try {
            f.set(instance, value);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public static Object invokeMethod(final Object instance, final Method m, final Object... args) {
        try {
            return m.invoke(instance, args);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }
    
    public static Object invokeMethod(final Object instance, final Method m) {
        try {
            return m.invoke(instance, new Object[0]);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }
    
    public static Class<?> getNMSClass(final String name) {
        try {
            return getClassWithException(name);
        }
        catch (ClassNotFoundException e2) {
            try {
                return getCraftClassWithException(name);
            }
            catch (ClassNotFoundException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }
    
    private static Class<?> getClassWithException(final String name) throws ClassNotFoundException {
        final String classname = "net.minecraft.server." + getVersion() + name;
        return Class.forName(classname);
    }
    
    private static Class<?> getCraftClassWithException(final String name) throws ClassNotFoundException {
        final String classname = "org.bukkit.craftbukkit." + getVersion() + name;
        return Class.forName(classname);
    }
    
    public static Class<?> getMojangClass(final String name) {
        try {
            final String classname = "com.mojang.authlib." + name;
            return Class.forName(classname);
        }
        catch (ClassNotFoundException e2) {
            final String classname2 = "net.minecraft.util.com.mojang.authlib." + name;
            try {
                return Class.forName(classname2);
            }
            catch (ClassNotFoundException e1) {
                e1.printStackTrace();
                e2.printStackTrace();
                return null;
            }
        }
    }
}
