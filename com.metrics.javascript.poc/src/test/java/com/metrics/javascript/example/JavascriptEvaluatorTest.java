package com.metrics.javascript.example;

import com.metrics.javascript.core.example.JavascriptEvaluator;
import com.metrics.javascript.core.example.util.ApplicationUtils;
import com.metrics.javascript.model.Message;
import com.metrics.javascript.model.Tx;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

public class JavascriptEvaluatorTest {


    @Test
    public void testFilterJavascript() {

        final String javascript = ApplicationUtils.getResourceContent("testMessage.js");
        final Map<String, Object> javascriptParams = new HashMap<>();

        final JavascriptEvaluator javascriptEvaluator = new JavascriptEvaluator();
        final Message message = new Message();
        final Tx tx = new Tx();
        tx.setMessageTypeId("104");
        tx.setTransactionType("pain");
        tx.setCounterpartyBank("TET1");
        tx.setCustomerCountryCode("DE");
        message.setBody(tx);
        javascriptParams.put("request", message);
        final Object result = javascriptEvaluator.evaluate(javascript, javascriptParams);

        Assert.assertEquals(true, result);
    }

    @Test
    public void testNoFilterJavascript() {

        final String javascript = ApplicationUtils.getResourceContent("testMessage.js");
        final Map<String, Object> javascriptParams = new HashMap<>();

        final JavascriptEvaluator javascriptEvaluator = new JavascriptEvaluator();
        final Message message = new Message();
        final Tx tx = new Tx();
        tx.setMessageTypeId("104");
        tx.setTransactionType("pain");
        tx.setCounterpartyBank("TEST1");
        tx.setCustomerCountryCode("DE");
        message.setBody(tx);
        javascriptParams.put("request", message);
        final Object result = javascriptEvaluator.evaluate(javascript, javascriptParams);

        Assert.assertEquals(false, result);
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
