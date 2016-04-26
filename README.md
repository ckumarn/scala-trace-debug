# scala-trace-debug
Make multithreaded bug tracing and prevention easier than ever with scala trace debug. 

Provides human-friendly prints, traces, assertions, container printing, source code printing, and log output.

____________________________________________________________________________________________________________________

### Getting started:

Just add these two lines to your "build.sbt" file:

```scala
resolvers += "johnreed2 bintray" at "http://dl.bintray.com/content/johnreed2/maven"

libraryDependencies += "scala-trace-debug" %% "scala-trace-debug" % "1.2.10"
```

Or get the jar file located in the [target/scala-2.11](target/scala-2.11) folder. 

____________________________________________________________________________________________________________________

### Is this the right tool for me?

1. Am I using an IDE (or does my team use an IDE)?

2. Do I find myself searching the file system (Ctr-F) for the location of log or print statements?

If you answered yes to both of these questions, this tool is for you.

____________________________________________________________________________________________________________________

### Examples:

#### With logger:

![Logger](http://i.imgur.com/MNNkYXe.png)

^ The left side in parenthesis is the name of a variable; the right side (after "->") is the contents. ^

#### Without logger:

![Demo](http://i.imgur.com/EFkBppw.png)

^ Traces and Asserts now come with jar file names in stacktrace. Ex. `[scalatest_2.11-2.2.6.jar]` ^

^ Note: `Debug.assert` kills the application with exit code 7. `Debug.assertNonFatal` never kills any part of the application, not even the current thread. Disable `Debug.assert` with `Debug.fatalAssertOff_!`.

^ * Note: `Debug.assertNonFatal` has been replaced with `Debug.check` in 1.2.9+. For older version, use 0.2.9 *

____________________________________________________________________________________________________________________

### Requirements:

- Scala 2.10.4 or higher
- Some sort of IDE that supports stack trace highlighting

____________________________________________________________________________________________________________________

### Logger Incorporation:

`Log.find` is designed to be used with a logger. Does not incur the overhead of a full stack trace.

`Debug` methods can be used without a logger, but all calls to `Debug.trace`, `Debug.assert`, etc. return a String that can be passed into a logger. 

You can disable printing to standard out and standard error via `Debug.disableEverything_!`. `Debug` methods will still return a String that you can pass into a logger. 

____________________________________________________________________________________________________________________

### Master Shutoff Switch:

If you set the environment variable `ENABLE_TRACE_DEBUG` to `false`, it will disable all printing and assertions.
A system property may also be used. "The system property takes precedence over the environment variable". The preprocessor will also replace all calls to `Log.find` with an empty String at compile time.
____________________________________________________________________________________________________________________

### Container Printing:

![ContainerExample](http://i.imgur.com/P8mlz0C.png)

^ Note the jar file name, `scalatest_2.11`, in the stack trace. ^

^ Container printing works for any Scala container. To pass in Java containers, [import scala.collection.JavaConversions._](http://stackoverflow.com/questions/9638492/conversion-of-scala-map-containing-boolean-to-java-map-containing-java-lang-bool) ^

____________________________________________________________________________________________________________________

### Cheat Sheet / Examples:

[Methods available through implicit conversion](http://ec2-52-87-157-20.compute-1.amazonaws.com/#info.collaboration_station.debug.package$$ImplicitTrace)

[Methods available through the Debug object](http://ec2-52-87-157-20.compute-1.amazonaws.com/#info.collaboration_station.debug.Debug$)

Example functions: http://pastebin.com/2e1JN1De

^ For more examples, see [Main.scala](src/test/scala/main/Main.scala), which you can run with `sbt test:run`

____________________________________________________________________________________________________________________

### Method Chaining:

Add-on methods available through implicit conversion return the object they were called upon so that you can use them inside an expression or chain them together.

```scala

import info.collaboration_station.debug.implicitlyTraceable
...
val foo = true
if( foo.trace ) { ... }

import info.collaboration_station.debug.implicitlyPrintable
...
val foobar = "foo".trace().concat("bar").println() // Chaining.

```

____________________________________________________________________________________________________________________


### Instructions (for IntelliJ IDE):

1. Add the library dependency (in sbt) or grab the jar file from the [target/scala-2.11](target/scala-2.11) folder.

2. import [info.collaboration_station.debug._](src/main/scala/info/collaboration_station/debug/package.scala)

3. Go to: Run > Edit Configurations > Add New Configuration (green plus sign).

4. Pick either "Application" (with a Main class) or "SBT Task" ("run", "test", or "test:run").

5. Place some calls to scala trace debug and click the green 'Debug' (Shift+F9) button and follow the stack traces in the console. 
 
6. Use the IntelliJ console arrows to navigate up and down the stack traces.

![IntelliJ console](http://s29.postimg.org/ud0knou1j/debug_Screenshot_Crop.png)

____________________________________________________________________________________________________________________

### More features:

#### _Desugared macro expression tracing:_

![Example](http://i.imgur.com/LvB8lOd.png)

######^ Useful if you have a line like "object method object param" and you can't find where the dot and parenthesis go ^

#### _Code tracing and assertions:_

![Example2](http://i.imgur.com/pdey7Jk.png)

######^ Useful if you do not want to repeat the name of a variable in a print statement. ^

____________________________________________________________________________________________________________________

### Benefits:

- Easy to locate print statements. Gives you an idea of what each thread is doing.
- Easy to locate and remove trace statements (Ctr-R find-and-replace or set ENABLE_TRACE_DEBUG to "false")
- Customizable features including stack trace length and enabling/disabling of assertions and traces.

____________________________________________________________________________________________________________________

### Use in practice:

For use in practice, see [this link](USE_WITH_IDE.md)

- To only add prints, `import info.collaboration_station.debug.implicitlyPrintable`
- To only add traces, `import info.collaboration_station.debug.implicitlyTraceable`
- If only add asserts, `import info.collaboration_station.debug.implicitlyAssertable`
- To add prints, traces, and asserts, `import info.collaboration_station.debug._`

____________________________________________________________________________________________________________________

### Performance:

No overhead for no stack trace.

```scala
"foo".trace(0) // no call to Thread.currentThread.getStackTrace()
```

Note that calls to `Log.find` are faster than calls to `Debug.trace`, but `Log.find` is limited to one line.

____________________________________________________________________________________________________________________

#### More info:

See [ScalaDoc](http://ec2-52-87-157-20.compute-1.amazonaws.com/) in source code for in detail documentation.

See also: http://stackoverflow.com/questions/36194905/how-can-we-trace-expressions-print-statements-with-line-numbers-in-scala/36194986#36194986

[http://stackoverflow.com/questions/4272797/debugging-functional-code-in-scala/36287172#36287172](http://stackoverflow.com/questions/4272797/debugging-functional-code-in-scala/36287172#36287172)

Old version of this library: [https://www.reddit.com/r/scala/comments/4aeqvh/debug_trace_library_needs_users_review/](https://www.reddit.com/r/scala/comments/4aeqvh/debug_trace_library_needs_users_review/)

Current version of this library: [https://www.reddit.com/r/scala/comments/4fap0r/making_debugging_easier/](https://www.reddit.com/r/scala/comments/4fap0r/making_debugging_easier/)

____________________________________________________________________________________________________________________

#### Code layout:

Currently all the actual printing is done in [`Printer.scala`](src/main/scala/info/collaboration_station/debug/internal/Printer.scala), all the implicit conversions are in [`package.scala`](src/main/scala/info/collaboration_station/debug/package.scala), and all the calls to the "Debug" object are in [`Debug.scala`](src/main/scala/info/collaboration_station/debug/Debug.scala)
