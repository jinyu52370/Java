package com.jinyu.tree;

import com.jinyu.entity.TreeNode;

import java.io.*;
import java.util.*;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/17 21:18
 */
public class HuffmanTree extends BinaryTree implements Serializable{
    //region private
    private byte[] bytes;
    private void codeMap(HashMap<Byte, String> huffmanCodeMap, TreeNode node, String code, StringBuilder builder){
        StringBuilder builder1 = new StringBuilder(builder);
        //拼接赫夫曼编码
        builder1.append(code);

        if (node != null){
            if (node.objects == null){
                codeMap(huffmanCodeMap, node.left, "0", builder1);
                codeMap(huffmanCodeMap, node.right, "1", builder1);
            } else {
                //<letter, code>
                huffmanCodeMap.put((Byte) node.objects[0], String.valueOf(builder1));
            }
        }
    }
    //endregion

    //region constructor
    public HuffmanTree(String str){
        char[] chars = str.toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }
        createBytes(bytes);
    }

    public HuffmanTree(){
    }
    //endregion

    /**
     * 统计字符串中字符出现的次数
     */
    public static Map<Byte, Integer> countStr(byte[] bytesStr) {
        HashMap<Byte, Integer> map = new HashMap<>(bytesStr.length);
        for (byte b : bytesStr) {
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        return map;
    }

    /**
     *  根据字节数组创建赫夫曼树
     */
    public void createBytes(byte[] bytes){
        if (bytes == null || bytes.length == 0){
            System.out.println("字符串为空");
            return;
        }
        this.bytes = bytes;
        List<TreeNode> treeNodes = new LinkedList<>();
        //通过统计每个字符出现次数的countStrMap来给treeNodes中添加节点(节点id为权重，即出现次数，object[0]为字符)
        Map<Byte, Integer> countStrMap = HuffmanTree.countStr(bytes);
        for (byte key : countStrMap.keySet()) {
            treeNodes.add(new TreeNode(countStrMap.get(key), key));
        }
        //todo 暂不支持只有一种字符
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
    public HashMap<Byte, String> codeMap() {
        if (root == null){
            System.out.println("赫夫曼树为空");
            return null;
        }
        HashMap<Byte, String> huffmanCodeMap = new HashMap<>();
        codeMap(huffmanCodeMap, root, "", new StringBuilder());
        return huffmanCodeMap;
    }

    /**
     * 通过codeMap赫夫曼编码表将str的每个字符转换为二进制码并拼接为StringBuilder
     */
    public StringBuilder huffmanCodes(){
        //获得赫夫曼编码表
        HashMap<Byte, String> huffmanCodeMap = codeMap();
        //创建拼接所有编码的StringBuilder
        StringBuilder huffmanCodes = new StringBuilder();
        //拼接编码
        for (byte b : bytes) {
            huffmanCodes.append(huffmanCodeMap.get(b));
        }
        return huffmanCodes;
    }

    /**
     * 将赫夫曼编码压缩为byte数组
     * @return 处理后的赫夫曼编码
     */
    public byte[] huffmanBytes() {
        StringBuilder huffmanCodes = huffmanCodes();
        if (huffmanCodes.length() < 3){
            throw new RuntimeException("赫夫曼树的root只有左右两个节点，编码为01，暂无法压缩");
        }
        //通过所有字符编码allCodes确定bytes的长度
//        if (huffmanCodes.length() % 8 == 0){
//            bytesLength = huffmanCodes.length() / 8;
//        } else {
//            bytesLength = huffmanCodes.length() / 8 + 1;
//        }
        byte[] bytes = new byte[(huffmanCodes.length() + 7) / 8];
        //将拼接后的编码每8位转换为byte并加入byte数组
        for (int i = 0, j = 0; i < huffmanCodes.length(); i += 8, j++) {
            String oneByteStr;
            if (i + 8 > huffmanCodes.length()){
                oneByteStr = huffmanCodes.substring(i);
            } else {
                oneByteStr = huffmanCodes.substring(i, i + 8);
            }
            //todo 若编码为01，则0会丢弃
            bytes[j] = (byte) Integer.parseInt(oneByteStr, 2);
        }
        return bytes;
    }

    /**
     * 解码
     */
    public byte[] decode(Map<Byte, String> codeMap, byte[] huffmanBytes) {
        //从文件中读取压缩后的赫夫曼编码，存入huffmanCodes
        StringBuilder huffmanCodes = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            //将element转换为无符号String
            String valueStr = Integer.toBinaryString(huffmanBytes[i] & 0x000000FF);
            //读取到最后一个byte时，元素不补0
            if (i == huffmanBytes.length - 1) {
                huffmanCodes.append(valueStr);
                break;
            }
            //给value补0，使其填满一字节
            huffmanCodes.append(String.format("%08d", Integer.valueOf(valueStr)));
        }
        //获取反转的赫夫曼编码表
        //<code, letter>
        HashMap<String, Byte> reverseCodeMap = new HashMap<>(codeMap.size());
        for (Map.Entry<Byte, String> entry : codeMap.entrySet()) {
            reverseCodeMap.put(entry.getValue(), entry.getKey());
        }
        //根据reverseCodeMap和huffmanCodes得到原始字节数组
        List<Byte> originalBytesList = new ArrayList<>(huffmanCodes.length());
        for (int start = 0, end = 1; end <= huffmanCodes.length(); end++) {
            String oneCode = huffmanCodes.substring(start, end);
            for (String key : reverseCodeMap.keySet()) {
                if (key.equals(oneCode)) {
                    start = end;
                    originalBytesList.add(reverseCodeMap.get(key));
                    break;
                }
            }
        }
        byte[] originalBytes = new byte[originalBytesList.size()];
        for (int i = 0; i < originalBytesList.size(); i++) {
            originalBytes[i] = originalBytesList.get(i);
        }
        return originalBytes;
    }

    /**
     * 将压缩后的赫夫曼编码保存为文件
     */
    public void write(String aimPath, String fileName){
        try {
            FileOutputStream fos1 = new FileOutputStream(new File(aimPath, fileName + ".txt"));
            FileOutputStream fos2 = new FileOutputStream(new File(aimPath, fileName + ".class"));
            ObjectOutputStream oos = new ObjectOutputStream(fos2);

            fos1.write(huffmanBytes());
            oos.writeObject(codeMap());

            oos.close();
            fos2.close();
            fos1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件中的被压缩的赫夫曼编码转换为字符串
     */
    public String read(String aimPath, String fileName) {
        try {
            File file = new File(aimPath, fileName + ".txt");
            FileInputStream fis1 = new FileInputStream(file);
            FileInputStream fis2 = new FileInputStream(new File(aimPath, fileName + ".class"));
            ObjectInputStream ois = new ObjectInputStream(fis2);

            //获取赫夫曼压缩后的数组
            byte[] huffmanBytes = new byte[(int) file.length()];
            int index = 0;
            byte len;
            while ((len = (byte) fis1.read()) != -1){
                huffmanBytes[index] = len;
                index++;
            }
            //读取赫夫曼编码表
            Map<Byte,String> codeMap = (Map<Byte, String>) ois.readObject();
            byte[] originalBytes = decode(codeMap, huffmanBytes);

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < originalBytes.length; i++) {
                result.append((char)originalBytes[i]);
            }

            ois.close();
            fis2.close();
            fis1.close();
            return String.valueOf(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void compressFile(String srcPath, String aimPath) {
        try {
            //从srcPath读取文件
            FileInputStream fis = new FileInputStream(srcPath);
            byte[] originalBytes = new byte[fis.available()];
            byte len;
            int index = 0;
            while ((len = (byte) fis.read(originalBytes)) != -1){
                originalBytes[index] = len;
                index++;
            }
            //
            FileOutputStream fos = new FileOutputStream(aimPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            createBytes(originalBytes);
            byte[] huffmanBytes = huffmanBytes();
            HashMap<Byte, String> codeMap = codeMap();

            oos.writeObject(huffmanBytes);
            oos.writeObject(codeMap);

            oos.close();
            fos.close();
            fis.close();

            System.out.println("压缩成功");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void unCompressFile(String srcPath, String aimPath) {
        try {
            FileInputStream fis = new FileInputStream(srcPath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> codeMap = (Map<Byte, String>) ois.readObject();

            byte[] originalBytes = decode(codeMap, huffmanBytes);

            FileOutputStream fos = new FileOutputStream(aimPath);
            fos.write(originalBytes);

            fos.close();
            ois.close();
            fis.close();

            System.out.println("解压成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
