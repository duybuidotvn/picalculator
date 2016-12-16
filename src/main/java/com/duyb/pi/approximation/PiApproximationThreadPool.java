package com.duyb.pi.approximation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.duyb.pi.formula.Formula;
import com.duyb.pi.task.Task;
import com.duyb.pi.task.TaskGenerator;

class PiApproximationThreadPool implements PiApproximation {
	@Override
	public double approximate(long n, final Formula f) {
		int numOfThreads = Runtime.getRuntime().availableProcessors();
		TaskGenerator taskGenerator = new TaskGenerator(n, numOfThreads);
		List<Future<Double>> results = new ArrayList<>();
		
		ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
		while(!taskGenerator.isExpired()) {
			final Task task = taskGenerator.get();
			results.add(executor.submit(new Callable<Double>() {
				@Override
				public Double call() {
					return task.compute(f);
				}
			}));
		}
		
		
		double result = 0;
		for(Future<Double> future : results) {
			try {
				result += future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
		
		return result;
	}
}
