package net.novoj;

import org.openjdk.jmh.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Minimal JMH benchmark.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
@Measurement(iterations = 1)
@Warmup(iterations = 1, time = 5)
@Timeout(time = 10)
@Fork(1)
@Threads(1)
public class TestWithLifecycle {

	@State(Scope.Benchmark)
	public static class RandomSource {
		@Param({"10", "100", "1000"})
		private int intCount;
		private FileInputStream stream;

		@Setup(Level.Trial)
		public void setUpRandomSource() throws IOException {
			stream = new FileInputStream(Path.of("/dev/random").toFile());
		}

		@TearDown(Level.Trial)
		public void tearDown() throws IOException {
			stream.close();
		}
	}

	@State(Scope.Thread)
	public static class DataToSort {
		private int[] randomData;

		@Setup(Level.Iteration)
		public void setUpData(RandomSource source) throws IOException {
			byte[] futureInt = new byte[4];
			final int[] randomIntegers = randomData == null ? new int[source.intCount] : randomData;
			for (int i = 0; i < source.intCount; i++) {
				source.stream.read(futureInt);
				randomIntegers[i] = bytesToInt(futureInt);
			}
			randomData = randomIntegers;
		}

		private static int bytesToInt(byte[] futureInt) {
			int theInt = 0;
			for (byte b : futureInt) {
				theInt = (theInt << 8) + (b & 0xFF);
			}
			return theInt;
		}
	}

	@Benchmark
	public void quickSort(DataToSort data) {
		Quicksort.quickSort(Arrays.copyOfRange(data.randomData, 0, data.randomData.length));
	}

	@Benchmark
	public void mergeSort(DataToSort data) {
		MergeSort.mergeSort(Arrays.copyOfRange(data.randomData, 0, data.randomData.length));
	}

	@Benchmark
	public void shellSort(DataToSort data) {
		ShellSort.shellSort(Arrays.copyOfRange(data.randomData, 0, data.randomData.length));
	}

}
