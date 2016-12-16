package com.duyb.pi.task;

public class TaskGenerator {
	private final long n;
	private final long taskLength;
	
	private long currentIndex = 0;
	
	public TaskGenerator(long n, long numOfTasks) {
		if(n < 0) {
			throw new IllegalArgumentException("n must be not negative");
		}
		
		if(numOfTasks <= 0) {
			throw new IllegalArgumentException("Must have at least one task");
		}
		
		this.n = n;
		long _taskLength = (n + 1) / numOfTasks;
		if(_taskLength == 0) {
			_taskLength = 1;
		}
		this.taskLength = _taskLength;
		
	}
	
	public synchronized Task get() {
		if(currentIndex >= n) {
			return null;
		}
		
		long begin = currentIndex;
		currentIndex += taskLength;
		long end = currentIndex;
		
		if(end > n) {
			end = n;
		}		
		
		return new Task(begin, end);
	}
	
	public synchronized boolean isExpired() {
		return currentIndex >= n;
	}
}
