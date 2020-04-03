package com.aoher.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class Sorting {

    private static final String LOGGER_TIME_FORMATTER = "{} time: {} millis";

    private static final Logger log = LoggerFactory.getLogger(Sorting.class);

    public static void bubbleSort(int[] array) {
        LocalDateTime start = LocalDateTime.now();

        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        LocalDateTime end = LocalDateTime.now();
        log.info(LOGGER_TIME_FORMATTER, "bubbleSort", getTime(start, end));
    }

    public static void insertionSort(int[] array) {
        LocalDateTime start = LocalDateTime.now();

        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[i];
                j--;
            }
            array[j + 1] = current;
        }

        LocalDateTime end = LocalDateTime.now();
        log.info(LOGGER_TIME_FORMATTER, "insertionSort", getTime(start, end));
    }

    public static void mergeSort(int[] array) {
        LocalDateTime start = LocalDateTime.now();

        mergeSort(array, 0, array.length - 1);

        LocalDateTime end = LocalDateTime.now();
        log.info(LOGGER_TIME_FORMATTER, "mergeSort", getTime(start, end));
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        mergeSort(array, left, mid, right);
    }

    private static void mergeSort(int[] array, int left, int mid, int right) {
        // вычисляем длину
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // копируем отсортированные массивы во временные
        int[] leftArray = IntStream.range(0, lengthLeft).map(i -> array[left + i]).toArray();
        int[] rightArray = IntStream.range(0, lengthRight).map(i -> array[mid + i + 1]).toArray();

        // итераторы содержат текущий индекс временного подмассива
        int leftIndex = 0;
        int rightIndex = 0;

        // копируем из leftArray и rightArray обратно в массив
        for (int i = left; i < right + 1; i++) {
            // если остаются нескопированные элементы в R и L, копируем минимальный
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            // если все элементы были скопированы из rightArray, скопировать остальные из leftArray
            else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }
            // если все элементы были скопированы из leftArray, скопировать остальные из rightArray
            else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

    public static void quickSort(int[] array) {
        LocalDateTime startT = LocalDateTime.now();

        quickSort(array, 0, array.length - 1);

        LocalDateTime endT = LocalDateTime.now();
        log.info(LOGGER_TIME_FORMATTER, "quickSort", getTime(startT, endT));
    }

    private static void quickSort(int[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    private static int partition(int[] array, int begin, int end) {
        int counter = begin;

        for (int i = begin; i < end; i++) {
            if (array[i] < array[end]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }

        int temp = array[end];
        array[end] = array[counter];
        array[counter] = temp;

        return counter;
    }

    private static long getTime(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toMillis();
    }

    private Sorting() {
    }
}
