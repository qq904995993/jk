//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs == null) return "";
        String res = strs[0];
        for (String str : strs) {
            //表示必须从0开始包含
            while (str.indexOf(res) != 0) {
                //开始滑动
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }

    //mine
//    public String longestCommonPrefix(String[] strs) {
//        if(strs == null || strs.length < 1 || strs[0].length() == 0) {
//            return "";
//        }
//        if(strs.length == 1) {
//            return strs[0];
//        }
//
//        Set<String> set = new TreeSet<>(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() > o2.length() ? -1 : o1.length() > o2.length() ? 0 : 1;
//            }
//        });
//
//        int length = 1;
//        String s;
//        while(strs[0].length() >= length && strs[1].startsWith(s = strs[0].substring(0, length))) {
//            set.add(s);
//            length ++;
//        }
//
//        if(set.size() == 0) {
//            return "";
//        }
//
//        for(int i = 2; i < strs.length; i ++) {
//            if(strs[i] == null || "".equals(strs[i])) {
//                set = new HashSet<>();
//                continue;
//            }
//
//            Iterator<String> iterator = set.iterator();
//            while(iterator.hasNext()) {
//                if(!strs[i].startsWith(iterator.next())) {
//                    iterator.remove();
//                }
//            }
//        }
//
//        if(set.size() == 0) {
//            return "";
//        }
//
//        return ((TreeSet<String>) set).first();
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
