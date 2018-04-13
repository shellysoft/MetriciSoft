package com.metrics.optimization.core.example;

import com.metrics.optimization.core.example.util.ApplicationUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {

        LOGGER.info("Started evaluating Javascript");

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final ExecutorService executorService = ApplicationUtils.initExecutorService();

        final String javascript = ApplicationUtils.getResourceContent("example.js");

        for (int i = 0; i < 10000; i++) {
            executorService.execute(new EvaluateJavascript(javascript));
        }
        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.MINUTES);

        stopWatch.stop();

        LOGGER.info("Finished evaluating Javascript in {} seconds", stopWatch.getTime(TimeUnit.SECONDS));
    }
}

