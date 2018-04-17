package com.metrics.javascript.example;

import com.metrics.javascript.core.example.JavascriptEvaluator;
import com.metrics.javascript.core.example.util.ApplicationUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

public class JavascriptEvaluatorTest {


    @Test
    public void testJavascriptEvaluator() {

        final String javascript = ApplicationUtils.getResourceContent("test.js");
        final Map<String, Object> javascriptParams = new HashMap<>();

        final JavascriptEvaluator javascriptEvaluator = new JavascriptEvaluator();
        javascriptParams.put("aNumber", 10);
        final Object result = javascriptEvaluator.evaluate(javascript, javascriptParams);

        Assert.assertEquals(result, true);
    }

    @Test
    public void loadTestJavascriptEvaluator() {

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final String javascript = ApplicationUtils.getResourceContent("test.js");
        final Map<String, Object> javascriptParams = new HashMap<>();

        final JavascriptEvaluator javascriptEvaluator = new JavascriptEvaluator();

        for (int i = 0; i < 1000; i++) {
            javascriptParams.put("aNumber", ApplicationUtils.generateRandomInteger(1, 100));
            javascriptEvaluator.evaluate(javascript, javascriptParams);
        }
        stopWatch.stop();

        Assert.assertTrue(stopWatch.getTime(TimeUnit.SECONDS) <= 10);

    }
}
