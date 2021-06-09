package arraylist;

import java.util.*;

/**
 * 380.     Medium      O(1) 时间插入、删除和获取随机元素
 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 insert(val)：当元素 val 不存在时，向集合中插入该项。
 remove(val)：元素 val 存在时，从集合中移除该项。
 getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 */
public class InsertDeleteGetRandom380 {
    private Map<Integer, Integer> map;
    private List<Integer> list;

    /** Initialize your data structure here. */
    public InsertDeleteGetRandom380() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        map.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (! map.containsKey(val)) {
            return false;
        }

        int index = map.get(val);
        map.put(list.get(list.size() - 1), index);
        list.set(index, list.get(list.size() - 1));
        list.remove(list.size() -1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(new Random().nextInt(list.size()));
    }
}
