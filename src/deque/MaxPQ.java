package deque;

// 二叉堆详解实现优先级队列
public class MaxPQ {
    // 存储元素的数组
    private Integer[] pq;
    // 当前 Priority Queue 中的元素个数
    private int size = 0;

    public MaxPQ(int cap) {
        // 索引 0 不用，所以多分配一个空间
        pq = new Integer[cap + 1];
    }

    private int getLeftIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private int getRightIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private int getParentIndex(int childIndex) {
        return (int) Math.floor((childIndex - 1) / 2);
    }

    private boolean hasLeft(int parentIndex) {
        return this.getLeftIndex(parentIndex) < this.pq.length;
    }

    private boolean hasRight(int parentIndex) {
        return this.getRightIndex(parentIndex) < this.pq.length;
    }

    private boolean hasParent(int childIndex) {
        return this.getParentIndex(childIndex) >= 0;
    }

    private int getLeft(int parentIndex) {
        return this.pq[parentIndex * 2 + 1];
    }

    private int getRight(int parentIndex) {
        return this.pq[parentIndex * 2 + 2];
    }

    private int getParent(int childIndex) {
        return this.pq[(int) Math.floor((childIndex - 1) / 2)];
    }


    /* 返回当前队列中最大元素 */
    public int max() {
        return pq[1];
    }

    /* 插入元素 e */
    public void insert(int e) {
        size++;
        // 先把新元素加到最后
        pq[size] = e;
        // 然后让它上浮到正确的位置
        swim(size);
    }

    /* 删除并返回当前队列中最大元素 */
    public int delMax() {
        // 最大堆的堆顶就是最大元素
        int max = pq[1];
        // 把这个最大元素换到最后，删除之
        swap(1, size);
        pq[size] = null;
        size--;
        // 让 pq[1] 下沉到正确位置
        sink(1);
        return max;
    }

    /* 上浮第 x 个元素，以维护最大堆性质 */
    private void swim(int x) {
        // 如果浮到堆顶，就不能再上浮了
        while (x > 1 && less(getParentIndex(x), x)) {
            // 如果第 x 个元素比上层大
            // 将 x 换上去
            swap(getParentIndex(x), x);
            x = getParentIndex(x);
        }
    }

    /* 下沉第 x 个元素，以维护最大堆性质 */
    private void sink(int x) {
        // 如果沉到堆底，就沉不下去了
        while (getLeft(x) <= size) {
            // 先假设左边节点较大
            int max = getLeft(x);
            // 如果右边节点存在，比一下大小
            if (getRight(x) <= size && less(max, getRight(x)))
                max = getRight(x);
            // 结点 x 比俩孩子都大，就不必下沉了
            if (less(max, x)) break;
            // 否则，不符合最大堆的结构，下沉 x 结点
            swap(x, max);
            x = max;
        }
    }

    /* 交换数组的两个元素 */
    private void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /* pq[i] 是否比 pq[j] 小？ */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

}

