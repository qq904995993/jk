//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //mine
    public static boolean isValid(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }

        LinkedList list = new LinkedList();
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '[') {
                list.add(c);
            } else if(c == ')' || c == '}' || c == ']') {
                if(list.size() == 0) {
                    return false;
                }
                if(c == ')' && (char) list.getLast() == '(') {
                    list.removeLast();
                } else if(c == '}' && (char) list.getLast() == '{') {
                    list.removeLast();
                } else if(c == ']' && (char) list.getLast() == '[') {
                    list.removeLast();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return list.size() == 0;
    }

    // theirs : stack is not recommend
//    public boolean isValid(String s) {
//        Stack<Character> stack = new Stack<>();
//        char[] chars = s.toCharArray();
//        for (char aChar : chars) {
//            if (stack.size() == 0) {
//                stack.push(aChar);
//            } else if (isSym(stack.peek(), aChar)) {
//                stack.pop();
//            } else {
//                stack.push(aChar);
//            }
//        }
//        return stack.size() == 0;
//    }
//
//    private boolean isSym(char c1, char c2) {
//        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
//    }

}
//leetcode submit region end(Prohibit modification and deletion)
