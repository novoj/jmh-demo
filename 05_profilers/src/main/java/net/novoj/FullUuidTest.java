package net.novoj;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;

import java.util.Random;
import java.util.UUID;

/**
 * Minimal JMH benchmark.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
public class FullUuidTest {

	@State(Scope.Thread)
	public static class UuidHolder {
		final RandomBasedGenerator randomBasedGenerator = Generators.randomBasedGenerator(new Random());
		final TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	}

	@Threads(1)
	@Benchmark
	public UUID singleThreadSecureRandomUUID() {
		return UUID.randomUUID();
	}

	@Threads(-1)
	@Benchmark
	public UUID multiThreadSecureRandomUUID() {
		return UUID.randomUUID();
	}

	@Threads(1)
	@Benchmark
	public UUID singleThreadRandomUUID(UuidHolder holder) {
		return holder.randomBasedGenerator.generate();
	}

	@Threads(-1)
	@Benchmark
	public UUID multiThreadRandomUUID(UuidHolder holder) {
		return holder.randomBasedGenerator.generate();
	}

	@Threads(1)
	@Benchmark
	public UUID singleThreadTimeUUID(UuidHolder holder) {
		return holder.timeBasedGenerator.generate();
	}

	@Threads(-1)
	@Benchmark
	public UUID multiThreadTimeUUID(UuidHolder holder) {
		return holder.timeBasedGenerator.generate();
	}

}
