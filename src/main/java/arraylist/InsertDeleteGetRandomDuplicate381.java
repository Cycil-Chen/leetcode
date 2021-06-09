package arraylist;

import java.util.*;

/**
 * 381.     Hard    O(1) 时间插入、删除和获取随机元素 - 允许重复
 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 注意: 允许出现重复元素。
 insert(val)：向集合中插入元素 val。
 remove(val)：当 val 存在时，从集合中移除一个 val。
 getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 */
public class InsertDeleteGetRandomDuplicate381 {
    private Map<Integer, Set<Integer>> map;
    private List<Integer> list;

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomDuplicate381() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        Set<Integer> set = map.getOrDefault(val, new HashSet<>());
        set.add(list.size() - 1);
        map.put(val, set);
        return set.size() == 1;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (! map.containsKey(val)) {
            return false;
        }

        Iterator<Integer> iterator = map.get(val).iterator();
        int index = iterator.next();
        list.set(index, list.get(list.size() - 1));
        map.get(val).remove(index);
        map.get(list.get(list.size() - 1)).remove(list.size() - 1);
        if (index < list.size() - 1) {
            map.get(list.get(list.size() - 1)).add(index);
        }
        if (map.get(val).size() == 0) {
            map.remove(val);
        }
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get((int) (Math.random() * list.size()));
    }
}
