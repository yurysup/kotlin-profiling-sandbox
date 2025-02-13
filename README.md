## Profiling

### async-profiler

Download [async-profiler](https://github.com/async-profiler/async-profiler) and follow official instruction.

Simple way to run profiler for 30s:</p>
`$ asprof -d 30 -f flamegraph.html <PID>`

### jvisualvm

Download [VisualVM](https://visualvm.github.io/download.html).
Extract the profiler and start GUI.

### jcmd
Find your JVM process `<PID>` by running `$ jps`.</p>
Execute `$ jcmd <PID> help` to see a list of allowed commands.

### jfr + jmc
Download [JDK Mission Control](https://www.oracle.com/java/technologies/javase/products-jmc9-downloads.html).</p>
Find your JVM process `<PID>` by running `$ jps`.</p>
Execute `$ jcmd <PID> JFR.start name=myrec filename=recording.jfr settings=profile` to start JFR record.</p>
Run `$ jcmd <PID> JFR.stop name=myrec` to stop it and analyze using JDK Mission Control GUI later.

### jstat
A `jstat` [command](https://docs.oracle.com/javase/7/docs/technotes/tools/share/jstat.html) is mainly useful for memory/GC analysis.

### jstack
A `jstack` command is useful to [analyse java threads](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr016.html).
