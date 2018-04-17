package com.metrics.javascript.core.example;

import com.metrics.javascript.core.example.util.ApplicationUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        LOGGER.info("Started evaluating Javascript");

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final String javascript = ApplicationUtils.getResourceContent("example.js");
        final Map<String, Object> javascriptParams = new HashMap<String, Object>();

        final JavascriptEvaluator javascriptEvaluator = new JavascriptEvaluator();

        for (int i = 0; i < 10000; i++) {
            javascriptParams.put("aNumber", ApplicationUtils.generateRandomInt(1, 100));
            javascriptEvaluator.evaluate(javascript, javascriptParams);
        }
        stopWatch.stop();

        LOGGER.info("Finished evaluating Javascript in {} seconds", stopWatch.getTime(TimeUnit.SECONDS));
    }


}

