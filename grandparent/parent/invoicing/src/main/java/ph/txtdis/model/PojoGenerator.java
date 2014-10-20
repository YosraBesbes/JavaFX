package ph.txtdis.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class PojoGenerator {

    @SuppressWarnings("rawtypes")
    public static Class generate(String classname, Map<String, Class<?>> properties) throws NotFoundException,
            CannotCompileException {

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = // pool.makeClass(className);

        pool.get("ph.txtdis.model." + classname);

        for (Entry<String, Class<?>> entry : properties.entrySet()) {

            cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));

            // add getter
            cc.addMethod(generateGetter(cc, entry.getKey(), entry.getValue()));

            // add setter
            cc.addMethod(generateSetter(cc, entry.getKey(), entry.getValue()));
        }

        return cc.toClass();
    }

    @SuppressWarnings("rawtypes")
    private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass)
            throws CannotCompileException {

        String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        StringBuffer sb = new StringBuffer();
        sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
                .append("return this.").append(fieldName).append(";").append("}");
        return CtMethod.make(sb.toString(), declaringClass);
    }

    @SuppressWarnings("rawtypes")
    private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class fieldClass)
            throws CannotCompileException {

        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        StringBuffer sb = new StringBuffer();
        sb.append("public void ").append(setterName).append("(").append(fieldClass.getName()).append(" ")
                .append(fieldName).append(")").append("{").append("this.").append(fieldName).append("=")
                .append(fieldName).append(";").append("}");
        return CtMethod.make(sb.toString(), declaringClass);
    }

    @SuppressWarnings("rawtypes")
    private static CtClass resolveCtClass(Class clazz) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        return pool.get(clazz.getName());
    }

    public static void main(String[] args) throws Exception {

        Map<String, Class<?>> props = new HashMap<>();
        props.put("S41", BigDecimal.class);
        props.put("S42", BigDecimal.class);
        props.put("S43", BigDecimal.class);
        props.put("S44", BigDecimal.class);
        props.put("S45", BigDecimal.class);
        props.put("PMS1", BigDecimal.class);
        props.put("PMS2", BigDecimal.class);
        props.put("PMS3", BigDecimal.class);

        Class<?> clazz = PojoGenerator.generate("Summary", props);

        Object obj = clazz.newInstance();

        System.out.println("Clazz: " + clazz);
        System.out.println("Object: " + obj);
        System.out.println("Serializable? " + (obj instanceof Serializable));

        for (final Method method : clazz.getDeclaredMethods()) {
            System.out.println(method);
        }

        // set property
        int i = 1;
        for (String name : props.keySet())
            clazz.getMethod("set" + name, BigDecimal.class).invoke(obj, new BigDecimal(i++));

        // get property
        for (String name : props.keySet())
            System.out.println("Value: " + clazz.getMethod("get" + name).invoke(obj));
    }

}
