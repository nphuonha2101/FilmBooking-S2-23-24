package com.filmbooking.utils.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClazzAnnotationProcessor {
    private static ClazzAnnotationProcessor instance;
    private static List<AnnotationResult> annotationResults;

    private ClazzAnnotationProcessor(Class<?> clazz) {
        annotationResults = new ArrayList<>();
        processAnnotations(clazz);
    }

    public static ClazzAnnotationProcessor getInstance(Class<?> clazz) {
        if (instance == null) {
            instance = new ClazzAnnotationProcessor(clazz);
        } else {
            annotationResults.clear();
            processAnnotations(clazz);
        }
        return instance;
    }

    private static void processAnnotations(Class<?> clazz) {
        // Get annotations from the class
        Annotation[] classAnnotations = clazz.getAnnotations();
        for (Annotation annotation : classAnnotations) {

            // Get the annotation values
            Map<String, Object> values = new HashMap<>();
            Method[] methods = annotation.annotationType().getDeclaredMethods();
            for (Method method : methods) {
                try {
                    values.put(method.getName(), method.invoke(annotation));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e.getCause());
                }
            }

            // Add the annotation value to the list
            annotationResults.add(new AnnotationResult(annotation, values));
        }

    }

    /**
     * Get the list of {@link AnnotationResult}
     * @return List of annotation results
     */
    public List<AnnotationResult> getAnnotationResults() {
        return annotationResults;
    }

    /**
     * Get the {@link AnnotationResult} of a specific annotation
     * @param annotationClass The annotation class
     * @return The annotation result
     */
    public AnnotationResult getAnnotationResult(Class<? extends Annotation> annotationClass) {
        for (AnnotationResult annotationResult : annotationResults) {
            if (annotationResult.getAnnotation().annotationType().equals(annotationClass)) {
                return annotationResult;
            }
        }
        return null;
    }

    /**
     * Get the values of a specific annotation
     * @param annotationClass The annotation class
     * @return The annotation values
     */
    public Map<String, Object> getAnnotationValues(Class<? extends Annotation> annotationClass) {
        AnnotationResult annotationResult = getAnnotationResult(annotationClass);
        if (annotationResult != null) {
            return annotationResult.getAnnotationValues();
        }
        return null;
    }

    /**
     * Get the value of a specific key in the annotation
     * @param annotationClass The annotation class
     * @param key The key
     * @return The value
     */
    public Object getAnnotationValue(Class<? extends Annotation> annotationClass, String key) {
        Map<String, Object> values = getAnnotationValues(annotationClass);
        if (values != null) {
            return values.get(key);
        }
        return null;
    }

    /**
     * Check if the class has a specific annotation
     * @param annotationClass The annotation class
     * @return <code>true</code> if the class has the annotation, <code>false</code> otherwise
     */
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return getAnnotationResult(annotationClass) != null;
    }
}
