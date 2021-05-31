package arraylist;

import java.util.*;

/**
 * 126.     Hard    单词接龙 II
 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像
 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：

 每对相邻的单词之间仅有单个字母不同。
 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 sk == endWord
 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，
 如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 */
public class WordLadderII126 {

    /**
     * 方法一/二
     * 慢
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        // 递归
        findMinLadders(beginWord, endWord, wordList, temp, res);
        return res;
    }

    /**
     * 方法三
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadder3(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (! wordList.contains(endWord)) {
            return res;
        }
        // BFS 得到所有的邻居节点，路径已经在map中
        Map<String, List<String>> map = bfs(beginWord, endWord, wordList);
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        // 递归
        findMinLadders3(beginWord, endWord, map, temp, res);
        return res;
    }

    /**
     * 方法四
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadder4(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (! wordList.contains(endWord)) {
            return res;
        }

        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        bfs2(beginWord, endWord, map, distance, wordList);
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        findMinLadders4(beginWord, endWord, map, distance, temp, res);
        return res;
    }

    /**
     * 方法五  剪枝
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadder5(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (! wordList.contains(endWord)) {
            return res;
        }

        Map<String, List<String>> map = new HashMap<>();
        bfs3(beginWord, endWord, map, wordList);
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        findMinLadders5(beginWord, endWord, map, temp, res);
        return res;
    }

    /**
     * 方法六  只用bfs
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders6(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        // 如果不含有结束单词，直接结束，不然后边会造成死循环
        if (!wordList.contains(endWord)) {
            return ans;
        }

        bfs4(beginWord, endWord, wordList, ans);
        return ans;
    }

    /**
     * 方法七  DFS+BFS 双向搜索
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders7(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (! wordList.contains(endWord)) {
            return res;
        }

        Map<String, List<String>> map = new HashMap<>();
        bfs5(beginWord, endWord, wordList, map);
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        findMinLadders7(beginWord, endWord, map, temp, res);
        return res;
    }


    int min3 = 0;
    /**
     * 广度优先 找出了所有的
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    private Map<String, List<String>> bfs(String beginWord, String endWord, List<String> wordList) {
        // 队列 先进先出
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Map<String, List<String>> map = new HashMap<>();
        boolean isFound = false;

        while (! queue.isEmpty()) {
            // 路径长度加1
            min3++;
            // 这一步很重要，记录当前队列的长度，如果直接用queue.size(),for循环中queue的长度会增加，则不会跳出每次的循环,
            // 会影响isFound的判断，影响路径的长度
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String temp = queue.poll();
                // 一次性得到该节点所有的下一个节点
                List<String> list = getNextWord(beginWord, new HashSet<>(wordList));
                // 记录当前节点，和对应的所有下一个节点
                map.put(temp, list);
                for (String word : list) {
                    if (word.equals(endWord)) {
                        isFound = true;
                    }
                    queue.offer(word);
                }
            }
            if (isFound) {
                break;
            }
        }
        return map;
    }

    /** 方法四 记录层数*/
    private void bfs2(String beginWord, String endWord, Map<String, List<String>> map, Map<String, Integer> distance, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        int depth = 0;
        boolean isFound = false;

        while (! queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                String temp = queue.poll();
                List<String> list = getNextWord(beginWord, new HashSet<>(wordList));
                map.put(temp, list);
                for (String word : list) {
                    // 该单词还没有在所有路径中出现过
                    if (! distance.containsKey(word)) {
                        distance.put(word, depth);
                        if (word.equals(endWord)) {
                            isFound = true;
                        }
                        queue.offer(word);
                    }
                }
            }
            if (isFound) {
                break;
            }
        }
    }

    /** 方法五
     * 在 BFS 中，如果发现有邻接节点在之前已经出现过了，我们直接把这个邻接节点删除不去。
     * 这样的话，在 DFS 中就不用再判断了，直接取邻居节点就可以了。
     判断之前是否已经处理过，可以用一个 HashSet 来把之前的节点存起来进行判断。**/
    private void bfs3(String beginWord, String endWord, Map<String, List<String>> map,List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        boolean isFound = false;
        // 记录已经访问过的节点   用于剪枝
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (! queue.isEmpty()) {
            int size = queue.size();
            // 记录当前层所有的节点
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                String temp = queue.poll();
                List<String> list = getNextWord(temp, new HashSet<>(wordList));
                // 元素导入迭代器
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    String word = it.next();
                    // 没有访问过
                    if (! visited.contains(word)) {
                        if (word.equals(endWord)) {
                            isFound = true;
                        }
                        queue.offer(word);
                        subVisited.add(word);
                    } else {
                        it.remove();
                    }
                }
                // 剪枝完成
                map.put(temp, list);
            }
            visited.addAll(subVisited);
            if (isFound) {
                break;
            }
        }
    }

    /** 方法六 只用bfs*/
    private void bfs4(String beginWord, String endWord, List<String> wordList, List<List<String>> res) {
        // 保存当前已经访问过的路径
        Queue<List<String>> queue = new LinkedList<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        queue.offer(path);
        boolean isFound = false;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (! queue.isEmpty()) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> lastPath = queue.poll();
                // 当前路径的末尾单词
                String temp = lastPath.get(lastPath.size() - 1);
                List<String> list = getNextWord(temp, new HashSet<>(wordList));
                for (String word : list) {
                    if (! visited.contains(word)) {
                        // 到达末尾单词结束
                        if (word.equals(endWord)) {
                            isFound = true;
                            lastPath.add(word);
                            res.add(new ArrayList<>(lastPath));
                            // 很重要 不能直接continue 因为需要更新queue
                            lastPath.remove(lastPath.size() - 1);
                        }
                        // 没结束加入当前单词    即使结束了也要更新queue
                        lastPath.add(word);
                        queue.offer(new ArrayList<>(lastPath));
                        // 更新完了 去掉末尾单词，留出位置 不影响下一个word 因为lastPath是全局变量
                        lastPath.remove(lastPath.size() - 1);
                        subVisited.add(word);
                    }
                }
                visited.addAll(subVisited);
                if(isFound) {
                    break;
                }
            }
        }

    }

    private void bfs5(String beginWord, String endWord, List<String> wordList, Map<String, List<String>> map) {
         Set<String> set1 = new HashSet<>();
         set1.add(beginWord);
         Set<String> set2 = new HashSet<>();
         set2.add(endWord);
         Set<String> set = new HashSet<>(wordList);
         bfsBothWay(set1, set2, set, true, map);
    }

    /** direction 为 true 代表向下扩展，false 代表向上扩展*/
    private boolean bfsBothWay(Set<String> set1, Set<String> set2, Set<String> wordSet, boolean direction, Map<String, List<String>> map) {
        if (set1.isEmpty()) {
            return false;
        }

        if (set1.size() > set2.size()) {
            // set1的数量多，换个方向
            return bfsBothWay(set2, set1, wordSet, !direction, map);
        }

        // 删除已经访问过的节点
        wordSet.removeAll(set1);
        wordSet.removeAll(set2);
        // 保存新扩展得到的节点
        Set<String> set = new HashSet<>();
        // 路径完成标识
        boolean done = false;

        for (String str : set1) {
            for (int i = 0; i < str.length(); i++) {
                char [] chs = str.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (chs[i] == ch) {
                        continue;
                    }

                    chs[i] = ch;
                    String word = new String(chs);

                    // 判断方向 找到key word
                    String key = direction ? str : word;
                    String val = direction ? word : str;

                    List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<>();

                    // 相遇
                    if (set2.contains(word)) {
                        done = true;
                        list.add(val);
                        map.put(key, list);
                    }

                    // 没相遇 且新单词在wordSet中
                    if (!done && wordSet.contains(word)) {
                        set.add(word);
                        list.add(val);
                        map.put(key, list);
                    }
                }
            }
        }

        // 下次反方向扩展
        return done || bfsBothWay(set2, set, wordSet, !direction, map);
    }

    private int min = Integer.MAX_VALUE;
    /**
     * 方法一
     * 遍历wordList 判断每个单词和当前单词是否只有一个字母不同
     * @param beginWord
     * @param endWord
     * @param wordList
     * @param temp
     * @param res
     */
    private void findMinLadders(String beginWord, String endWord, List<String> wordList, List<String> temp, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            // 发现更小路径
            if (min > temp.size()) {
                min = temp.size();
                res.clear();
                res.add(new ArrayList<>(temp));
            } else if (min == temp.size()) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        // 当前长度已经达到了min,但是还没有找到就提前结束
        if (temp.size() >= min) {
            return;
        }
        // 遍历当前列表所有单词
        for (int i = 0; i < wordList.size(); i++) {
            String curWord = wordList.get(i);
            // 如果路径里已经出现则跳过
            if (temp.contains(curWord)) {
                continue;
            }
            // 只有一个单词不同，则递归
            if (oneChanged(beginWord, curWord)) {
                temp.add(curWord);
                findMinLadders(curWord, endWord, wordList, temp, res);
                // 回退，查找是否还有更短路径
                temp.remove(temp.size() - 1);
            }
        }
    }

    int min2 = Integer.MAX_VALUE;
    /**
     * 方法二
     * 将要找的单词每个位置换一个字符，然后看更改的单词在不在wordList中
     * @param beginWord
     * @param endWord
     * @param wordList
     * @param temp
     * @param res
     */
    private void findMinLadders2(String beginWord, String endWord, List<String> wordList, List<String> temp, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            if (min2 > temp.size()) {
                res.clear();
                min2 = temp.size();
                res.add(new ArrayList<>(temp));
            } else if (min2 == temp.size()) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        if (temp.size() >= min2) {
            return;
        }

        Set<String> sets = new HashSet<>(wordList);
        // 一次性到达所有的下一个节点
        List<String> list = getNextWord(beginWord, sets);
        for (String word : list) {
            // 路径中已有
            if (temp.contains(word)) {
                continue;
            }

            temp.add(word);
            findMinLadders2(word, endWord, wordList, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 方法三  递归
     * @param beginWord
     * @param endWord
     * @param map
     * @param temp
     * @param res
     */
    private void findMinLadders3(String beginWord, String endWord, Map<String, List<String>> map, List<String> temp, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            // 最小路径长度已经确定，不用再判断
            res.add(new ArrayList<>(temp));
            return;
        }
        // bfs中循环次数比路径长度小1 所有min3也要减1
        if (temp.size() - 1 == min3) {
            return;
        }
        // 得到所有下一个的节点
        List<String> list = map.getOrDefault(beginWord, new ArrayList<>());
        for (String word : list) {
            if (temp.contains(word)) {
                continue;
            }

            temp.add(word);
            findMinLadders3(word, endWord, map, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    private void findMinLadders4(String beginWord, String endWord, Map<String, List<String>> map, Map<String, Integer> distance, List<String> temp, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(temp));
            return;
        }

        List<String> list = map.getOrDefault(beginWord, new ArrayList<>());
        for (String word : list) {
            // 判断层数是否符合
            if (distance.get(beginWord) + 1 == distance.get(word)) {
                temp.add(word);
                findMinLadders4(word, endWord, map, distance, temp, res);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private void findMinLadders5(String beginWord, String endWord, Map<String, List<String>> map, List<String> temp, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(temp));
            return;
        }

        List<String> list = map.getOrDefault(beginWord, new ArrayList<>());
        for (String word : list) {
            temp.add(word);
            findMinLadders5(word, endWord, map, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    private void findMinLadders7(String beginWord, String endWord, Map<String, List<String>> map, List<String> temp, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(temp));
            return;
        }

        List<String> list = map.getOrDefault(beginWord, new ArrayList<>());
        for (String word : list) {
            temp.add(word);
            findMinLadders7(word, endWord, map, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    // 方法一  判断是否只有一个单词不同
    private boolean oneChanged(String beginWord, String curWord) {
        int count = 0;
        for (int i = 0; i < beginWord.length(); i++) {
            if (beginWord.charAt(i) != curWord.charAt(i)) {
                count++;
            }

            if (count == 2) {
                return false;
            }
        }
        return count == 1;
    }

    // 方法二  将要查找的单词每个位置换一个字母，看更改后的单词是否在list中
    private List<String> getNextWord(String beginWord, Set<String> wordList) {
        List<String> result = new ArrayList<>();
        char [] chars = beginWord.toCharArray();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chars.length; i++) {
                // 该位置的字母一样，比较下一个
                if (chars[i] == ch) {
                    continue;
                }
                char old = chars[i];
                chars[i] = ch;
                if (wordList.contains(String.valueOf(chars))) {
                    result.add(String.valueOf(chars));
                }
                // 恢复原来的字母
                chars[i] = old;
            }
        }
        return result;
    }
}
