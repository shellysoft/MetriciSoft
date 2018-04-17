# MetriciSoft

This Application is a Proof of concept of how we can evaluate JavaScript with Java 7.

It uses Rhino JavaScript Engine ( https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino )

It can be run with Java 8 and in this case some issues can be noticed:
- The ScriptEngineManager provides 2 Javascript engines Rhino and Nashorn (part of Java 8).
- The execution time is doubled

The project contains an optimized version.

