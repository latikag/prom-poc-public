package com.example.timescaledb;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RunThreadToSaveSLOError {

	@Autowired
	private SloErrorRepository sloErrorRepository;

	@PostConstruct
	public void saveSLOErrorBudget() {

		System.out.println(sloErrorRepository.count());
		System.out.println("start time: " + OffsetDateTime.now());
		ExecutorService executor = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 100; i++) {
			Runnable worker = new WorkerThread(sloErrorRepository);
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {

		}
		System.out.println();
		System.out.println(OffsetDateTime.now());
	}

}

class WorkerThread implements Runnable {

	SloErrorRepository sloErrorRepository;

	Random r = new Random();

	public WorkerThread(SloErrorRepository sloErrorRepository) {
		this.sloErrorRepository = sloErrorRepository;
	}

	@Override
	public void run() {
		double randomValue = 90 + (10 * r.nextDouble());
		SloError sloError = new SloError();
		sloError.setThreshold(95);
		sloError.setSli_value(randomValue);
		sloError.setTime(OffsetDateTime.now());
		sloError.setSlo_id(r.nextInt());
		sloErrorRepository.save(sloError);
		System.out.print(sloError.getId());
	}

}
