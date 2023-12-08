package edu.handong.csee.java.examples.thread.lab;

import java.util.ArrayList;

public class SumMultipleThreads {

	public static void main(String[] args) {

		long to = 10000000;
		ArrayList<SumRunner> sumRunners = new ArrayList<SumRunner>();
		ArrayList<Thread> threadsForSubSum = new ArrayList<Thread>();

		/* Let a thread compute a sub-sum.
		 * sumRunners.add(new SumMultipleThreads(1,1000000));
		 * sumRunners.add(new SumMultipleThreads(1000001,2000000));
		 * sumRunners.add(new SumMultipleThreads(2000001,3000000));
		 * sumRunners.add(new SumMultipleThreads(3000001,4000000));
		 * sumRunners.add(new SumMultipleThreads(4000001,5000000));
		 * sumRunners.add(new SumMultipleThreads(5000001,6000000));
		 * sumRunners.add(new SumMultipleThreads(6000001,7000000));
		 * sumRunners.add(new SumMultipleThreads(7000001,8000000));
		 * sumRunners.add(new SumMultipleThreads(8000001,9000000));
		 * sumRunners.add(new SumMultipleThreads(9000001,10000000));*/
		for(long i=0; i<to/1000000; i++) {
			SumRunner currentRunner = new SumRunner((i*1000000)+1, (i+1)*1000000);
			sumRunners.add(currentRunner);
			
			Thread thread = new Thread(currentRunner);
			thread.start();
			threadsForSubSum.add(thread);
			System.out.println("Thread-" + i + " started!");
		}
		
		for(Thread thread : threadsForSubSum) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		long grandTotal = 0;
		for(SumRunner runner:sumRunners) {
			grandTotal += runner.totalSum;
		}

		System.out.println("Grand Total = " + grandTotal);
	}

}
