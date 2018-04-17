package com.metrics.javascript.example;

import com.metrics.javascript.core.example.util.ApplicationUtils;
import de.christophkraemer.rhino.javascript.RhinoScriptEngine;
import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptEngine;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class ApplicationUtilsTest {

    @Test
    public void testGetResourceContentOk() {
        final String javascript = ApplicationUtils.getResourceContent("test.js");
        Assert.assertEquals(javascript != "", true);
    }

    @Test
    public void testGetResourceContentNotFound() {
        final String javascript = ApplicationUtils.getResourceContent("tet.js");
        Assert.assertEquals(javascript == "", true);
    }

    @Test
    public void testInitJavascriptEngine() {
        final ScriptEngine scriptEngine = ApplicationUtils.initJavascriptEngine();
        Assert.assertTrue(scriptEngine instanceof RhinoScriptEngine);
    }
}
