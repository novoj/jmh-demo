package net.novoj;

import org.openjdk.jmh.profile.SafepointsProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/**
 * A class that will execute all the benchmarks.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
public class BenchmarkRunner {

	public static void main(String[] args) throws Exception {
		Options opt = new OptionsBuilder()
			.include(JavaUuidTest.class.getName())
			.forks(1)
			.threads(1)
			.warmupTime(TimeValue.seconds(10))
			.warmupIterations(1)
			.timeout(TimeValue.seconds(10))
			.measurementIterations(1)
			.addProfiler(SafepointsProfiler.class)
			.build();

		new Runner(opt).run();
	}

}
