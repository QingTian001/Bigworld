package gs.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by zyao on 2020/7/24 15:30
 * 敏感词检查, 服务器启动时加载
 */
public final class NameChecker {
    private static NameChecker instance = new NameChecker();


    private TriTree triTree = new TriTree();
    public static NameChecker getInstance() {
        return instance;
    }

    public static class Node {
        private HashMap<Character, Node> nodes = new HashMap<>();
        private boolean isEnd = false;

        void addString(String str) {
            char c = str.charAt(0);
            if (!nodes.containsKey(c)) {
                nodes.put(c, new Node());
            }
            if (str.length() > 1) {
                nodes.get(c).addString(str.substring(1));
            }
            else {
                nodes.get(c).isEnd = true;
            }

        }

        boolean checkString(String str) {
            if (isEnd) {
                return false;
            }
            if (str.length() == 0) {
                return true;
            }
            char c = str.charAt(0);
            if (!nodes.containsKey(c)) {
                return true;
            }

            return nodes.get(c).checkString(str.substring(1));
        }
    }

    public static class TriTree {
        private Node root = new Node();
        void addString(String str) {
            root.addString(str);
        }

        boolean checkString(String checkStr) {
            return root.checkString(checkStr);
        }
    }

    public void loadSensitiveWords(String file) throws Exception {
        try (BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                triTree.addString(line);
            }
        }
    }

    /**
     * 检查是否通过敏感词检查
     * @param checkStr
     * @return true 表示通过敏感词检查 false表示没通过
     */
    public boolean checkString(String checkStr) {
        for (int i = 0; i < checkStr.length(); ++i) {
            if (!triTree.checkString(checkStr.substring(i))) {
                return false;
            }
        }
        return true; // 全部通过
    }
}
