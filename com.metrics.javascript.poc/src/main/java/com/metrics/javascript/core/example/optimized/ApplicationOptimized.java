package com.metrics.javascript.core.example.optimized;

import com.metrics.javascript.core.example.Application;
import com.metrics.javascript.core.example.util.ApplicationUtils;

import javax.script.ScriptEngine;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationOptimized {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {

        LOGGER.info("Started evaluating Javascript");

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final String javascript = ApplicationUtils.getResourceContent("example.js");
        final ScriptEngine scriptEngine = ApplicationUtils.initJavascriptEngine();
        final Map<String, Object> javascriptParams = new HashMap<String, Object>();

        final OptimizedJavascriptEvaluator javascriptEvaluator = new OptimizedJavascriptEvaluator();

        for (int i = 0; i < 10000; i++) {
            javascriptParams.put("aNumber", ApplicationUtils.generateRandomInt(1, 100));
            javascriptEvaluator.evaluate(scriptEngine, javascript, javascriptParams);
        }
        stopWatch.stop();

        LOGGER.info("Finished evaluating Javascript in {} seconds", stopWatch.getTime(TimeUnit.SECONDS));
    }


}

