package daily;

/**
 * 2446. 判断两个事件是否存在冲突[easy]
 * https://leetcode.cn/problems/determine-if-two-events-have-conflict/
 * 20230517
 */
public class HaveConflict {
    public static void main(String[] args) {
        String[] event1={"14:13","22:08"};
        String[] event2={"02:40","08:08"};
        Solution solution = new Solution();
        System.out.println(solution.haveConflict(event1, event2));
    }

    static class Solution {
        public boolean haveConflict(String[] event1, String[] event2) {
            //当两个事件不存在冲突的充要条件是一个事件的结束时间早于另一个事件的开始时间
            // 可以直接用字符串的比较判断两个事件是否存在冲突
            //event1[1].compareTo(event2[0])<0  不冲突
           return  !(event1[1].compareTo(event2[0])<0 || event2[1].compareTo(event1[0])<0);

        }
    }
}
