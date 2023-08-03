package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 722. 删除注释[medium]
 * https://leetcode.cn/problems/remove-comments/
 */
public class RemoveComments {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] source = {"a/*comment", "line", "more_comment*/b"};
        System.out.println(solution.removeComments(source).toString());

    }

    static class Solution {
        public List<String> removeComments(String[] source) {
            ArrayList<String> res = new ArrayList<>();
            // 用来拼接遍历过程中的字符串
            StringBuilder newLine = new StringBuilder();
            // 标记当前字符是否在块注释内部
            boolean inBlock = false;
            for (String line : source) {
                // 每行
                for (int i = 0; i < line.length(); i++) {
                    if (inBlock) {
                        // 在块注释内部
                        // 遇到 */
                        if (i < line.length() - 1 && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                            // 块注释结束
                            inBlock = false;
                            // 跳过'/'继续遍历
                            i++;
                        }
                    } else {
                        // 不在块注释内部
                        // 遇到 /*
                        if (i < line.length() - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                            // 块注释开始
                            inBlock = true;
                            // 跳过'*'继续遍历
                            i++;
                        } else if (i < line.length() - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                            // 遇到 // ，直接忽略该行后面的部分
                            break;
                        } else {
                            // 其他合法字符
                            newLine.append(line.charAt(i));
                        }
                    }
                }
                // 每行结束
                if (!inBlock && newLine.length() > 0) {
                    res.add(newLine.toString());
                    newLine.setLength(0);
                }
            }
            return res;
        }
    }
}
