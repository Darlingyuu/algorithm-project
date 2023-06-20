package tree;

/**
 * 341. 扁平化嵌套列表迭代器[medium]
 * https://leetcode.cn/problems/flatten-nested-list-iterator/
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    private LinkedList<NestedInteger> list;

    public NestedIterator(List<NestedInteger> nestedList) {
        // 不直接用 nestedList 的引用，是因为不能确定它的底层实现
        // 必须保证是 LinkedList，否则下面的 addFirst 会很低效
        list = new LinkedList<>(nestedList);
    }

    @Override
    public Integer next() {
        // hasNext 方法保证了第一个元素一定是整数类型
        return list.remove(0).getInteger();
    }

    @Override
    public boolean hasNext() {
        // 循环拆分列表元素，直到列表第一个元素是整数类型
        while (!list.isEmpty() && !list.get(0).isInteger()){
            // 当列表开头第一个元素是列表类型时，进入循环
            List<NestedInteger> first = this.list.remove(0).getList();
            // 将第一个列表打平并按顺序添加到开头
            for (int i = first.size()-1; i >=0; i--) {
                list.addFirst(first.get(i));
            }

            // 相当于 list{{a,b,c},...}  -->  list{a,b,c,...}
        }
        return !list.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

// 此处方便测试，自行实现NestedInteger  类比多叉树，值都存放在叶子节点
class NestedInteger {
    Integer val;
    List<NestedInteger> list;

    // 如果其中存的是一个整数，则返回 true，否则返回 false
    public boolean isInteger(){
        return val != null;
    }

    // 如果其中存的是一个整数，则返回这个整数，否则返回 null
    public Integer getInteger(){
        return this.val;
    }

    // 如果其中存的是一个列表，则返回这个列表，否则返回 null
    public List<NestedInteger> getList(){
        return this.list;
    }
}