package com.metrics.javascript.example;

import com.metrics.javascript.core.example.JavascriptEvaluator;
import com.metrics.javascript.core.example.util.ApplicationUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JavascriptEvaluatorTest {


    @Test
    public void testJavascriptEvaluator() {

        final String javascript = ApplicationUtils.getResourceContent("example.js");
        final Map<String, Object> javascriptParams = new HashMap<String, Object>();

        final JavascriptEvaluator javascriptEvaluator = new JavascriptEvaluator();
        javascriptParams.put("aNumber", 10);
        final Object result = javascriptEvaluator.evaluate(javascript, javascriptParams);

        Assert.assertEquals(result, true);
    }
}
