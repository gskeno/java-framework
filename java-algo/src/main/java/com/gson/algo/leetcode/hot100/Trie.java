package com.gson.algo.leetcode.hot100;

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * <p>
 * void insert(String word) 向前缀树中插入字符串 word 。
 * <p>
 * boolean search(String word) 如果字符串 word 在前缀树中，
 * 返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * <p>
 * boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，
 * 返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 链接：https://leetcode.cn/problems/implement-trie-prefix-tree
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 */
public class Trie {
    static class Node {
        private char parent;

        /**
         * 是否是结尾词
         */
        private boolean end;
        private Node[] children = new Node[26];

        public Node(char c) {
            this.parent = c;
        }
    }

    private Node[] nodes;

    public Trie() {
        nodes = new Node[26];
    }

    public void insert(String word) {
        Node node = nodes[word.charAt(0) - 'a'];
        if (node == null) {
            nodes[word.charAt(0) - 'a'] = new Node(word.charAt(0));
            node = nodes[word.charAt(0) - 'a'];
        }
        if (word.length() == 1){
            node.end = true;
        }
        for (int i = 1; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (node.children[letter - 'a'] == null) {
                node.children[letter - 'a'] = new Node(letter);
            }
            node = node.children[letter - 'a'];
            if (i == word.length() -1){
                node.end = true;
            }
        }
    }

    public boolean search(String word) {

        Node node = nodes[word.charAt(0) - 'a'];
        if (node == null) {
            return false;
        }
        for (int i = 1; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (node.children[letter - 'a'] == null) {
                return false;
            }
            node = node.children[letter - 'a'];
        }

        return node.end;

    }

    public boolean startsWith(String prefix) {
        Node node = nodes[prefix.charAt(0) - 'a'];
        if (node == null) {
            return false;
        }
        for (int i = 1; i < prefix.length(); i++) {
            char letter = prefix.charAt(i);
            if (node.children[letter - 'a'] == null) {
                return false;
            }
            node = node.children[letter - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("ap"));

        System.out.println(trie.startsWith("applem"));
        System.out.println(trie.startsWith("applm"));
        System.out.println(trie.startsWith("d"));
    }
}
