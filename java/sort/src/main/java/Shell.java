/**
 * 希尔排序
 *      把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；
 *      随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 *
 *          步骤1：将数据长度除以2作为增量分组；
 *          步骤2：对每个分组进行插入排序；
 *          步骤3：重复增量除以2作为分组，分组就行插入排序，知道增量为1结束。
 *
 *      最佳情况：T(n) = O(nlog2 n)
 *      最坏情况：T(n) = O(nlog2 n)
 *      平均情况：T(n) =O(nlog2n)
 *      不稳定
 */
public class Shell {

    private int[] shellSort(int[] arr) {
        if(arr == null) {
            return arr;
        }

        int gap = arr.length;
        while(gap != 1) {
            gap /= 2;
            for(int i = gap; i < arr.length; i ++) {
                int preIndex = i - 1;
                int current = arr[i];
                while(preIndex >= 0 && current < arr[preIndex]) {
                    arr[preIndex + 1] = arr[preIndex];
                    preIndex --;
                }
                arr[preIndex + 1] = current;
            }
        }

        return arr;
    }

}
