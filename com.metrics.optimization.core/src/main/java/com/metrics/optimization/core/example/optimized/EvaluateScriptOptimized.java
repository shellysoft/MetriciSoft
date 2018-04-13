package com.metrics.optimization.core.example.optimized;

import com.metrics.optimization.core.example.EvaluateJavascript;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvaluateScriptOptimized implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateJavascript.class);

    private final ScriptEngine scriptEngine;

    private final String javascript;

    public EvaluateScriptOptimized(ScriptEngine scriptEngine, String javascript) {
        this.scriptEngine = scriptEngine;
        this.javascript = javascript;
    }

    public void run() {
        final Object result;
        try {
            result = scriptEngine.eval(
                    javascript);
            LOGGER.info("Result of script evaluation is {}", result);
        } catch (ScriptException e) {
            LOGGER.error("An exception occurred while evaluating the javascript file", e);
        }
    }
}
