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

    //region constructor
    public HuffmanTree(String str){
        createByStr(str);
    }

    public HuffmanTree(){
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
        if (str == null || "".equals(str)){
            System.out.println("字符串为空");
            return;
        }
        this.str = str;
        List<TreeNode> treeNodes = new LinkedList<>();
        //通过统计每个字符出现次数的countStrMap来给treeNodes中添加节点(节点id为权重，即出现次数，object[0]为字符)
        Map<Character, Integer> countStrMap = HuffmanTree.countStr(str);
        for (char key : countStrMap.keySet()) {
            treeNodes.add(new TreeNode(countStrMap.get(key), String.valueOf(key)));
        }
        //todo
        //暂不支持只有一种字符
        if (treeNodes.size() == 1) {
            throw new RuntimeException("赫夫曼树只有一个节点，暂不支持编码");
        }
        create(treeNodes);
    }

    /**
     * 创建赫夫曼树
     */
    public void create(List<TreeNode> treeNodes) {
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

            if (list.size() == 0) {
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
     * 通过codeMap赫夫曼编码表将str的每个字符转换为二进制码并拼接为StringBuilder
     */
    public StringBuilder allCodes(){
        //获得赫夫曼编码表
        HashMap<String, String> huffmanCodeMap = codeMap();
        //创建拼接所有编码的StringBuilder
        StringBuilder allCodes = new StringBuilder();
        //拼接编码
        for (char letter : str.toCharArray()) {
            allCodes.append(huffmanCodeMap.get(String.valueOf(letter)));
        }
        return allCodes;
    }

    /**
     * 将赫夫曼编码表压缩为byte数组
     * @return 处理后的赫夫曼编码
     */
    public byte[] compress() {
        StringBuilder allCodes = allCodes();
        if (allCodes.length() < 3){
            throw new RuntimeException("赫夫曼树的root只有左右两个节点，编码为01，暂无法压缩");
        }
        //通过所有字符编码allCodes确定bytes的长度
//        if (allCodes.length() % 8 == 0){
//            bytesLength = allCodes.length() / 8;
//        } else {
//            bytesLength = allCodes.length() / 8 + 1;
//        }
        byte[] bytes = new byte[(allCodes.length() + 7) / 8];
        //将拼接后的编码每8位转换为byte并加入byte数组
        for (int i = 0, j = 0; i < allCodes.length(); i += 8, j++) {
            String oneByteStr;
            if (i + 8 > allCodes.length()){
                oneByteStr = allCodes.substring(i);
            } else {
                oneByteStr = allCodes.substring(i, i + 8);
            }
            //todo
            bytes[j] = (byte) Integer.parseInt(oneByteStr, 2);
        }
        return bytes;
    }

    /**
     * 将压缩后的赫夫曼编码保存为文件
     */
    public void write(String path, String fileName){
        try {
            FileOutputStream fos1 = new FileOutputStream(new File(path, fileName + ".txt"));
            FileOutputStream fos2 = new FileOutputStream(new File(path, fileName + ".properties"));

            Properties prop = new Properties();
            prop.putAll(codeMap());

            fos1.write(compress());
            prop.store(fos2, "Huffman Code Map");

            fos2.close();
            fos1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件中的被压缩的赫夫曼编码转换为字符串
     */
    public static String read(String path, String fileName) {
        try {
            File file = new File(path, fileName + ".txt");
            FileInputStream fis1 = new FileInputStream(file);
            FileInputStream fis2 = new FileInputStream(new File(path, fileName + ".properties"));
            //计数器：统计读取文件的第几个字节
            int count = 0;
            //从文件中读取压缩后的赫夫曼编码，存入allCodes
            StringBuilder allCodes = new StringBuilder();
            byte valueByte;
            while ((valueByte = (byte) fis1.read()) != -1) {
                count++;
                //将element转换为无符号String
                String valueStr = Integer.toBinaryString(valueByte & 0x000000FF);
                //读取到最后一个byte时，元素不补0
                if (count == file.length()) {
                    allCodes.append(valueStr);
                    break;
                }
                //给value补0，使其填满一字节
                allCodes.append(String.format("%08d", Integer.valueOf(valueStr)));
            }
            //读取赫夫曼编码表
            Properties codeMapProp = new Properties();
            codeMapProp.load(fis2);
            //获取反转的赫夫曼编码表
            HashMap<String, String> reverseCodeMap = new HashMap<>(codeMapProp.size());
            for (Map.Entry<Object, Object> entry : codeMapProp.entrySet()){
                reverseCodeMap.put((String) entry.getValue(), (String) entry.getKey());
            }
            //根据反转的赫夫曼编码表和读取文件得到的二进制码allCodes获得str
            StringBuilder result = new StringBuilder();
            for (int start = 0, end = 1; end <= allCodes.length(); end++){
                String oneCode = allCodes.substring(start, end);
                for (String key : reverseCodeMap.keySet()) {
                    if (key.equals(oneCode)){
                        start = end;
                        result.append(reverseCodeMap.get(key));
                        break;
                    }
                }
            }
            fis2.close();
            fis1.close();
            return String.valueOf(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
