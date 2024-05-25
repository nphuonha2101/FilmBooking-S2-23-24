package com.filmbooking.utils.annotation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class AnnotationResult {
    private Annotation annotation;
    private Map<String, Object> annotationValues;

    public AnnotationResult(Annotation annotation, Map<String, Object> annotationValues) {
        this.annotation = annotation;
        this.annotationValues = annotationValues;
    }

    public AnnotationResult(Annotation annotation) {
        this.annotation = annotation;
        annotationValues = new HashMap<>();
    }
}
