package com.metrics.optimization.core.example.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationUtils.class);

    private static final String RHINO_SCRIPT_ENGINE_FACTORY = "de.christophkraemer.rhino.javascript.RhinoScriptEngineFactory";
    private static final String NASHORN_SCRIPT_ENGINE_FACTORY = "jdk.nashorn.api.scripting.NashornScriptEngineFactory";

    public static String getResourceContent(String resourceName) {

        String result = "";

        ClassLoader classLoader = ApplicationUtils.class.getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(resourceName), Charset.forName("UTF-8"));
        } catch (IOException e) {
            LOGGER.error("An error occurred while reading the resource {}", resourceName, e);
        }

        return result;
    }

    public static ExecutorService initExecutorService() {
        return new ThreadPoolExecutor(0, 100,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static ScriptEngine initJavascriptEngine() {
        final ScriptEngineManager scriptEngineManager
                = new ScriptEngineManager();
        return scriptEngineManager.getEngineByName("JavaScript");
    }

    public static ScriptEngine initRhinoJavascriptEngine() {

        final ScriptEngineFactory scriptEngineFactory;
        try {
            scriptEngineFactory = (ScriptEngineFactory) Class.forName(RHINO_SCRIPT_ENGINE_FACTORY).newInstance();
            return scriptEngineFactory.getScriptEngine();
        } catch (Exception e) {
            LOGGER.error("An exception occurred while initializing the Rhino javascript engine", e);
            e.printStackTrace();
        }
        return null;
    }

    public static ScriptEngine initNashornJavascriptEngine() {

        final ScriptEngineFactory scriptEngineFactory;
        try {
            scriptEngineFactory = (ScriptEngineFactory) Class.forName(NASHORN_SCRIPT_ENGINE_FACTORY).newInstance();
            return scriptEngineFactory.getScriptEngine();
        } catch (Exception e) {
            LOGGER.error("An exception occurred while initializing the Nashorn javascript engine", e);
            e.printStackTrace();
        }
        return null;
    }
}
