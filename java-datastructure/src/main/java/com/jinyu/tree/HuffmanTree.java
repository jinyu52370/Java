package com.jinyu.tree;

import com.jinyu.entity.TreeNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/17 21:18
 */
public class HuffmanTree extends BinaryTree{
    //region private
    private String str;
    private void codeMap(HashMap<String, String> huffmanCodeMap, TreeNode node, String code, StringBuilder builder){
        StringBuilder builder1 = new StringBuilder(builder);
        //拼接赫夫曼编码
        builder1.append(code);

        if (node != null){
            if (node.objects == null){
                codeMap(huffmanCodeMap, node.left, "0", builder1);
                codeMap(huffmanCodeMap, node.right, "1", builder1);
            } else {
                //<letter, code>
                huffmanCodeMap.put((String) node.objects[0], String.valueOf(builder1));
            }
        }
    }
    //endregion
    /**
     * 统计字符串中字符出现的次数
     */
    public static Map<Character, Integer> countStr(String str) {
        HashMap<Character, Integer> map = new HashMap<>(str.length());
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    /**
     *  根据字符串创建赫夫曼树
     */
    public void createByStr(String str){
        this.str = str;
        List<TreeNode> treeNodes = new LinkedList<>();

        Map<Character, Integer> countStrMap = HuffmanTree.countStr(str);
        for (char key : countStrMap.keySet()) {
            treeNodes.add(new TreeNode(countStrMap.get(key), String.valueOf(key)));
        }
        create(treeNodes);
    }

    /**
     * 创建赫夫曼树
     */
    public void create(List<TreeNode> treeNodes){
        List<TreeNode> list = new LinkedList<>(treeNodes);
        list.sort(Comparator.comparingInt(TreeNode::getId));

        TreeNode leftNode, rightNode, parent;
        while (true) {
            leftNode = list.get(0);
            rightNode = list.get(1);

            parent = new TreeNode(leftNode.id + rightNode.id, null);
            parent.left = leftNode;
            parent.right = rightNode;

            list.remove(0);
            list.remove(0);

            if (list.size() == 0){
                break;
            }

            list.add(parent);

            list.sort(Comparator.comparingInt(TreeNode::getId));
        }
        root = parent;
    }

    /**
     * 赫夫曼编码
     * 默认：向左为0，向右为1
     * <letter, code>
     *
     * @return 赫夫曼编码表
     */
    public HashMap<String, String> codeMap() {
        if (root == null){
            System.out.println("赫夫曼树为空");
            return null;
        }
        HashMap<String, String> huffmanCodeMap = new HashMap<>();
        codeMap(huffmanCodeMap, root, "", new StringBuilder());
        return huffmanCodeMap;
    }

    /**
     * 将赫夫曼编码表压缩为byte数组
     * @return 处理后的赫夫曼编码
     */
    public byte[] compress() {
        //获得赫夫曼编码表
        HashMap<String, String> huffmanCodeMap = codeMap();
        //创建拼接所有编码的StringBuilder
        StringBuilder allCodes = new StringBuilder();
        //拼接编码
        for (char letter : str.toCharArray()) {
            allCodes.append(huffmanCodeMap.get(String.valueOf(letter)));
        }
        //通过所有字符编码allCodes确定bytes的长度
        int bytesLength;
        if (allCodes.length() % 8 == 0){
            bytesLength = allCodes.length() / 8;
        } else {
            bytesLength = allCodes.length() / 8 + 1;
        }
        //todo
        System.out.println(allCodes);
        //将拼接后的编码每8位转换为byte并加入byte数组
        byte[] bytes = new byte[bytesLength];
        for (int i = 0, j = 0; i < allCodes.length(); i += 8, j++) {
            String oneByteStr;
            if (i + 8 > allCodes.length()){
                oneByteStr = String.valueOf(allCodes).substring(i, allCodes.length());
            } else {
                oneByteStr = String.valueOf(allCodes).substring(i, i + 8);
            }
            bytes[j] = (byte) Integer.parseInt(oneByteStr, 2);
        }
        return bytes;
    }

    /**
     * 将压缩后的赫夫曼编码保存为文件
     */
    public void write(String path){
        try {
            FileOutputStream fos = new FileOutputStream(path);

            fos.write(compress());

            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件中的被压缩的哈弗曼编码
     */
    public String  read(String path){
        try {
            FileInputStream fis = new FileInputStream(path);

            byte[] bytes = new byte[(int) new File(path).length()];

            //从文件中读取压缩后的赫夫曼编码，存入bytes数组
            byte element;
            int index = 0;
            while ((element = (byte) fis.read()) != -1){
                bytes[index] = element;
                index++;
            }
            //根据赫夫曼编码表和bytes数组获取字母
            HashMap<String, String> codeMap = codeMap();
            //<letter, code>
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes) {
                //todo
                builder.append(Integer.toBinaryString(b & 0xFF));
                for (Map.Entry<String, String> entry : codeMap.entrySet()) {
//                    if (s.equals(entry.getValue())){
//                        System.out.println(entry.getKey());
//                    }
                }
            }
            System.out.println(builder);


            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
