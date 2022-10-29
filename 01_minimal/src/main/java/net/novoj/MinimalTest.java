package net.novoj;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.UUID;

/**
 * Minimal JMH benchmark.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
public class MinimalTest {

	@Benchmark
	public UUID testUUID() {
		return UUID.randomUUID();
	}

}
