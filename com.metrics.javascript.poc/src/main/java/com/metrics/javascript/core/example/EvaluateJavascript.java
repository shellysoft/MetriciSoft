package com.metrics.javascript.core.example;

import com.metrics.javascript.core.example.util.ApplicationUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvaluateJavascript implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateJavascript.class);

    private final String javascript;

    public EvaluateJavascript(String javascript) {
        this.javascript = javascript;
    }

    public void run() {
        final ScriptEngine scriptEngine = ApplicationUtils.initJavascriptEngine();

        LOGGER.debug("Javascript engine used: {}", scriptEngine.getClass().getSimpleName());

        final Object result;
        try {
            scriptEngine.put("aNumber", ApplicationUtils.generateRandomInt(1, 100));
            result = scriptEngine.eval(
                    javascript);
            LOGGER.info("Result of script evaluation is {}", result);
        } catch (ScriptException e) {
            LOGGER.error("An exception occurred while evaluating the javascript file", e);
        }
    }
}
