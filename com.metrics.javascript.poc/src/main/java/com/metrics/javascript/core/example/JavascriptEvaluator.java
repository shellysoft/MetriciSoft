package com.metrics.javascript.core.example;

import com.metrics.javascript.core.example.util.ApplicationUtils;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavascriptEvaluator {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavascriptEvaluator.class);

    public JavascriptEvaluator() {
    }

    public Object evaluate(final String javascript, final Map<String, Object> parameters) {
        final ScriptEngine scriptEngine = ApplicationUtils.initJavascriptEngine();

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
