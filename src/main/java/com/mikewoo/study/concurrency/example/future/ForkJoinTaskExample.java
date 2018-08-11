package com.mikewoo.study.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Eric Gui
 * @date 2018/8/11
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {

    public static final int THREAD_HOLD = 2;

    private int start;

    private int end;

    public ForkJoinTaskExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canComute = (end - start) <= THREAD_HOLD;
        if (canComute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start, middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle + 1, end);

            leftTask.fork();
            rightTask.fork();

            Integer leftResult = leftTask.join();
            Integer reghtResult = rightTask.join();
            sum = leftResult + reghtResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTaskExample forkJoinTaskExample = new ForkJoinTaskExample(1, 100);
        ForkJoinTask<Integer> result = forkJoinPool.submit(forkJoinTaskExample);

        Integer sum = null;
        try {
            sum = result.get();
        } catch (Exception e) {
            log.warn("exception happened", e);
        }
        Optional.ofNullable(sum).ifPresent(System.out::println);
    }
}
