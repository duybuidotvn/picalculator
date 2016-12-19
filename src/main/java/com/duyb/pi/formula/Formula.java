package com.duyb.pi.formula;

public enum Formula {
	LEIBNIZ(Long.MAX_VALUE) {
		@Override
		protected double valueAt(long index) {
			return index % 2 == 0 ? 4d / (2 * index + 1) : -4d / (2 * index + 1);
		}
	},
	
	BBP(Long.MAX_VALUE) {
		@Override
		protected double valueAt(long index) {
		return (1d/Math.pow(16,index))*((4d/((8*index)+1))-(2d/((8*index)+4))-(1d/((8*index)+5))-(1d/((8*index)+6)));
		}
	},
	
	NILAKANTHA(Long.MAX_VALUE) {
		@Override
		protected double valueAt(long index) {
			if(index == 0) {
				return 3d;
			} else if (index % 2 == 0) {
				return -4d / ((2 * index) * (2 * index + 1) * (2 * index + 2));
			}
			else {
				return 4d / ((2 * index) * (2 * index + 1) * (2 * index + 2));
			}
		}
	};
	
	public final long maxIndex;
	
	Formula(long maxIndex) {
		this.maxIndex = maxIndex;
	}
	
	public double at(long index) {
		if(index > maxIndex) {
			throw new IllegalArgumentException("The index can not be greater than " + maxIndex + " for " + this.name() + " formual");
		}
		
		return valueAt(index);
	}
	
	protected abstract double valueAt(long index);
	
	
}
