package com.filmbooking.utils.annotation;

import com.filmbooking.annotations.TableIdName;
import com.filmbooking.model.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClazzAnnotationProcessorTest {

    @Test
    void getAnnotationResults() {
        ClazzAnnotationProcessor clazzAnnotationProcessor = ClazzAnnotationProcessor.getInstance(User.class);
        assertNotNull(clazzAnnotationProcessor.getAnnotationResults());
    }

    @Test
    void getAnnotationResult() {
        ClazzAnnotationProcessor clazzAnnotationProcessor = ClazzAnnotationProcessor.getInstance(User.class);
        assertNotNull(clazzAnnotationProcessor.getAnnotationResult(TableIdName.class));
    }

    @Test
    void getAnnotationValues() {
        ClazzAnnotationProcessor clazzAnnotationProcessor = ClazzAnnotationProcessor.getInstance(User.class);

        Map<String, Object> expectValues = new HashMap<>();
        expectValues.put("value", "username");

        assertEquals(expectValues, clazzAnnotationProcessor.getAnnotationValues(TableIdName.class));
    }

    @Test
    void getAnnotationValue() {
        ClazzAnnotationProcessor clazzAnnotationProcessor = ClazzAnnotationProcessor.getInstance(User.class);
        assertEquals("username", clazzAnnotationProcessor.getAnnotationValue(TableIdName.class, "value"));
    }
}