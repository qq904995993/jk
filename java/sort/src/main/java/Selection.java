import java.util.Arrays;

/**
 * 选择排序
 *
 *      首先在未排序序列中找到最小（大）元素，存放到排序序列的起始（末尾）位置。
 *      然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到未排序序列的其实（末尾）位置。
 *      以此类推，直到所有元素均排序完毕。
 *
 *          步骤1：初始状态：无序区为R[1…n]，有序区为空；
 *          步骤2：第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1…i-1]和R(i…n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1…i]和R[i+1…n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
 *          步骤3：n-1趟结束，数组有序化了。
 *
 *      最佳情况：T(n) = O(n2)
 *      最差情况：T(n) = O(n2)
 *      平均情况：T(n) = O(n2)
 *      不稳定
 */
public class Selection {

     private static int[] selectionSort(int[] arr) {
        if(arr != null) {
            for (int i = 0; i < arr.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[minIndex] > arr[j]) {
                        minIndex = j;
                    }
                }

                if (minIndex != i) {
                    arr[i] = arr[i] + arr[minIndex];
                    arr[minIndex] = arr[i] - arr[minIndex];
                    arr[i] = arr[i] - arr[minIndex];
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] i = {9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(selectionSort(i)));

    }

}
