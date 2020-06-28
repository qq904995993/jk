import java.util.Arrays;

/**
 * 插入排序
 *
 *      构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 *      插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 *
 *          步骤1: 从第一个元素开始，该元素可以认为已经被排序；
 *          步骤2: 取出下一个元素，在已经排序的元素序列中从后向前扫描；
 *          步骤3: 如果该元素（已排序）大于新元素，将该元素移到下一位置；
 *          步骤4: 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 *          步骤5: 将新元素插入到该位置后；
 *          步骤6: 重复步骤2~5。
 *
 *      最佳情况：T(n) = O(n)
 *      最坏情况：T(n) = O(n2)
 *      平均情况：T(n) = O(n2)
 *      稳定
 */
public class Insertion {

    private static int[] insertionSort(int[] arr) {
        if(arr != null) {
            for (int i = 1; i < arr.length; i++) {
                int preIndex = i - 1;
                int current = arr[i];
                while (preIndex >= 0 && current < arr[preIndex]) {
                    arr[preIndex + 1] = arr[preIndex];
                    preIndex--;
                }
                arr[preIndex + 1] = current;
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] i = {9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(insertionSort(i)));

    }

}
