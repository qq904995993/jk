import java.util.Arrays;

/**
 *  冒泡排序
 *
 *      重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
 *      走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 *      这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 *
 *          步骤1: 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 *          步骤2: 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 *          步骤3: 针对所有的元素重复以上的步骤，除了最后一个；
 *          步骤4: 重复步骤1~3，直到排序完成。
 *
 *     最佳情况：T(n) = O(n)
 *     最差情况：T(n) = O(n2)
 *     平均情况：T(n) = O(n2)
 *     稳定
 */
public class Bubble {

     private static int[] bubbleSort(int[] arr) {
        if(arr != null) {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        arr[j] = arr[j] + arr[j + 1];
                        arr[j + 1] = arr[j] - arr[j + 1];
                        arr[j] = arr[j] - arr[j + 1];
                    }
                }
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] i = {9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(bubbleSort(i)));
    }

}
