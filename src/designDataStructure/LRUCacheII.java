package designDataStructure;

/**
 * 146. LRU 缓存[medium]
 * https://leetcode.cn/problems/lru-cache/
 */


import java.util.HashMap;

/**
 * 自定义数据结构
 */
public class LRUCacheII {
    // 哈希表
    // key -> Node(key,val)
    private HashMap<Integer,Node> map;
    // 双向链表
    private DoubleList cache;
    // 容量
    private int cap;

    public LRUCacheII(int capacity) {
        this.cap=capacity;
        map=new HashMap<>();
        cache=new DoubleList();
    }

    public int get(int key) {
        // 若缓存中不存在
        if (!map.containsKey(key)){
            return -1;
        }
        // 存在，将该key变为最近使用
        makeRecently(key);
        // 返回对应value
        return map.get(key).val;
    }

    public void put(int key, int value) {
        // 若当前缓存中已存在该key
        if (map.containsKey(key)){
           // 删除旧数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key,value);
            return;
        }

        // 若缓存中不存在该key
        if (cache.size()>=this.cap){ // 缓存满了
            // 删除最久未使用的元素
            removeLeastRecently();
        }
        // 添加为最近使用的元素
        addRecently(key, value);
    }

    // 删除最久未使用的元素
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        Node removeFirst = cache.removeFirst();
        // 从map中删除它的映射
        int key = removeFirst.key;
        map.remove(key);
    }

    // 添加最近使用的元素
    private void addRecently(int key, int value) {
        Node x = new Node(key, value);
        // 链表尾部就是最近使用的元素
        cache.addLast(x);
        // 在map中增加key的映射
        map.put(key,x);
    }

    private void deleteKey(int key){
        Node x = map.get(key);
        // 从链表中删除
        cache.remove(x);
        // 从map中删除
        map.remove(key);
    }




    // 将该key变为最近使用
    private void makeRecently(int key) {
        Node x = map.get(key);
        // 删除key，重新插入到队尾
        cache.remove(x);
        cache.addLast(x);
    }

    /**
     * 双向链表
     */
    class DoubleList {
        // 头尾虚节点
        private Node head, tail;
        // 链表元素数
        private int size;

        public DoubleList(){
            // 初始化双向链表的数据
            head=new Node(0,0);
            tail=new Node(0,0);
            head.next=tail;
            tail.prev=head;
            size=0;
        }

        // 在链表尾部添加节点x
        public void addLast(Node x){
            x.prev=tail.prev;
            x.next=tail;
            tail.prev.next=x;
            tail.prev=x;
            size++;
        }

        // 删除链表中的x节点（x一定存在）
        public void remove(Node x){
            x.prev.next=x.next;
            x.next.prev=x.prev;
            size--;
        }

        // 删除链表中的第一个节点，并返回该节点
        public Node removeFirst(){
            if (head.next==null){
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        // 返回链表长度
        public int size(){
            return size;
        }

    }

    /**
     * 节点类
     */
    class Node {
        public int key, val;
        public Node next, prev;
        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }
}
