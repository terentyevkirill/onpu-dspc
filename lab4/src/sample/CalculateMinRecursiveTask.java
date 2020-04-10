package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CalculateMinRecursiveTask extends RecursiveTask<Integer> {
    private int[] array;
    private int threadsNum;
    public static final int THRESHOLD = 5;

    public CalculateMinRecursiveTask(int[] array, int threadsNum) {
        this.array = array;
        this.threadsNum = threadsNum;
    }

    @Override
    protected Integer compute() {
        if (array.length > THRESHOLD && threadsNum != 1 && threadsNum < array.length) {
            return ForkJoinTask.invokeAll(createAsyncSubTasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .min()
                    .getAsInt();
        } else {
            return getMinSynchronously(array);
        }
    }

    private Collection<CalculateMinRecursiveTask> createAsyncSubTasks() {
        List<CalculateMinRecursiveTask> subTasks = new ArrayList<>();
        int divideRange = array.length / threadsNum;
        for (int i = 0; i < array.length; i = i + divideRange) {
            if (divideRange + i >= array.length) {
                subTasks.add(new CalculateMinRecursiveTask(Arrays.copyOfRange(array, i, array.length), threadsNum));
            } else {
                subTasks.add(new CalculateMinRecursiveTask(Arrays.copyOfRange(array, i, i + divideRange), threadsNum));
            }
        }

        return subTasks;
    }

    private Integer getMinSynchronously(int[] array) {
        return Arrays.stream(array).min().getAsInt();
    }
}
