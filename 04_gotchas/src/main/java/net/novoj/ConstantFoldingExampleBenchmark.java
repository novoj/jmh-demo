package net.novoj;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Inspired by: https://github.com/openjdk/jmh/blob/master/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_10_ConstantFold.java
 *
 * Benchmark                                           Mode  Cnt  Score   Error  Units
 * ConstantFoldingExampleBenchmark.correct             avgt       0.640          ns/op
 * ConstantFoldingExampleBenchmark.correctAlternative  avgt       0.681          ns/op
 * ConstantFoldingExampleBenchmark.wrong               avgt       0.409          ns/op
 */
@Measurement(iterations = 1)
@Warmup(iterations = 1, time = 5)
@Timeout(time = 10)
@Fork(value = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ConstantFoldingExampleBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        private final Random rnd = new Random();
        public int a = rnd.nextInt();
        public int b = rnd.nextInt();
    }

    @Benchmark
    public void wrong(MyState state) {
        int sum = state.a + state.b;
    }

    @Benchmark
    public int correct(MyState state) {
        return state.a + state.b;
    }

    @Benchmark
    public void correctAlternative(Blackhole blackhole, MyState state) {
        blackhole.consume(state.a + state.b);
    }

}
