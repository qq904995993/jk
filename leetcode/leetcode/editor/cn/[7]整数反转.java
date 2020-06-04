//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 
//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        long l = 0;
        while (x != 0) {
            l = l * 10 + x % 10;
            x /= 10;
        }
        return (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) ? 0 : (int) l;
    }

    //mine
//    public int reverse(int x) {
//        if(x  == 0) {
//            return 0;
//        }
//
//        StringBuilder sb;
//        if(x < 0) {
//            String s = String.valueOf(x);
//            sb = new StringBuilder().append(s.substring(1, s.length()));
//        } else {
//            sb = new StringBuilder().append(x);
//        }
//        sb.reverse();
//        long l = Long.parseLong(sb.toString());
//        l = x < 0 ? -l : l;
//
//        if(l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
//            return 0;
//        }
//
//        return (int) l;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
