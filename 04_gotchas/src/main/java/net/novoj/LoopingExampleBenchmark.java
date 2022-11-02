/*
 * Copyright (c) 2005, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package net.novoj;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Copy of: https://github.com/openjdk/jmh/blob/master/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_11_Loops.java
 *
 * Benchmark                                    Mode  Cnt  Score   Error  Units
 * LoopingExampleBenchmark.measureRight         avgt       0.691          ns/op
 * LoopingExampleBenchmark.measureWrong_1       avgt       0.705          ns/op
 * LoopingExampleBenchmark.measureWrong_10      avgt       0.083          ns/op
 * LoopingExampleBenchmark.measureWrong_100     avgt       0.047          ns/op
 * LoopingExampleBenchmark.measureWrong_1000    avgt       0.029          ns/op
 * LoopingExampleBenchmark.measureWrong_10000   avgt       0.025          ns/op
 * LoopingExampleBenchmark.measureWrong_100000  avgt       0.027          ns/op
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 1)
@Warmup(iterations = 1)
@Fork(value = 1)
public class LoopingExampleBenchmark {

	/*
	 * It would be tempting for users to do loops within the benchmarked method.
	 * (This is the bad thing Caliper taught everyone). These tests explain why
	 * this is a bad idea.
	 *
	 * Looping is done in the hope of minimizing the overhead of calling the
	 * test method, by doing the operations inside the loop instead of inside
	 * the method call. Don't buy this argument; you will see there is more
	 * magic happening when we allow optimizers to merge the loop iterations.
	 */

	/*
	 * Suppose we want to measure how much it takes to sum two integers:
	 */

	int x = 1;
	int y = 2;

	/*
	 * This is what you do with JMH.
	 */

	@Benchmark
	public int measureRight() {
		return (x + y);
	}

	@Benchmark
	@OperationsPerInvocation(1)
	public int measureWrong_1() {
		return reps(1);
	}

	/*
	 * We would like to measure this with different repetitions count.
	 * Special annotation is used to get the individual operation cost.
	 */

	@Benchmark
	@OperationsPerInvocation(10)
	public int measureWrong_10() {
		return reps(10);
	}

	@Benchmark
	@OperationsPerInvocation(100)
	public int measureWrong_100() {
		return reps(100);
	}

	@Benchmark
	@OperationsPerInvocation(1_000)
	public int measureWrong_1000() {
		return reps(1_000);
	}

	@Benchmark
	@OperationsPerInvocation(10_000)
	public int measureWrong_10000() {
		return reps(10_000);
	}

	@Benchmark
	@OperationsPerInvocation(100_000)
	public int measureWrong_100000() {
		return reps(100_000);
	}

	/*
	 * The following tests emulate the naive looping.
	 * This is the Caliper-style benchmark.
	 */
	private int reps(int reps) {
		int s = 0;
		for (int i = 0; i < reps; i++) {
			s += (x + y);
		}
		return s;
	}

}
