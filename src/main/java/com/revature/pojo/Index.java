package com.revature.pojo;

import java.util.Arrays;

public class Index {
	private int[] index;
	
	public Index(){
		
	}

	public int[] getIndex() {
		return index;
	}

	public void setIndex(int[] index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Index [index=" + Arrays.toString(index) + "]";
	}
	
}
