package com.metrics.javascript.core.example.optimized;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimizedJavascriptEvaluator {
    private static final Logger LOGGER = LoggerFactory.getLogger(OptimizedJavascriptEvaluator.class);

    public OptimizedJavascriptEvaluator() {
    }

    public Object evaluate(final ScriptEngine scriptEngine, final String javascript, final Map<String, Object> parameters) {
        LOGGER.debug("Javascript engine used: {}", scriptEngine.getClass().getSimpleName());

        Object result = null;
        try {
            scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE).putAll(parameters);
            result = scriptEngine.eval(
                    javascript);
            LOGGER.info("Result of script evaluation is {}", result);
        } catch (ScriptException e) {
            LOGGER.error("An exception occurred while evaluating the javascript file", e);
        }
        return result;
    }
}
