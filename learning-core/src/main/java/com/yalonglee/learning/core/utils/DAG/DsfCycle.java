package com.yalonglee.learning.core.utils.DAG;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 具体思想是：
 * 1、记录node节点数和对应的线。
 * 2、将其转化为矩阵。
 * 3、通过dsf算法查找闭环。
 *
 * @author yalonglee
 */
public class DsfCycle {

    /**
     * 限制node最大数
     */
    private static int MAX_NODE_COUNT = 100;

    /**
     * node集合
     */
    private static List<String> nodes = Lists.newArrayList();

    /**
     * 有向图的邻接矩阵
     */
    private static int[][] adjacencyMatrix = new int[MAX_NODE_COUNT][MAX_NODE_COUNT];

    /**
     * link集合
     */
    private static Map<String, String> links = Maps.newHashMap();

    /**
     * @param nodeName
     * @return
     * @Title addNode
     * @Description 添加节点
     * @date 2018年5月17日
     */
    private static int addNode(String nodeName) {
        if (!nodes.contains(nodeName)) {
            if (nodes.size() >= MAX_NODE_COUNT) {
                System.out.println("nodes length over maxlength:" + nodeName);
                return -1;
            }
            nodes.add(nodeName);
            return nodes.size() - 1;
        }
        return nodes.indexOf(nodeName);
    }

    /**
     * @param startNodeName
     * @param endNodeName
     * @return
     * @Title addLink
     * @Description 添加节点
     * @date 2018年5月17日
     */
    private static void addLink(String startNodeName, String endNodeName) {
        final String sepa = ";";
        if (endNodeName.contains(sepa)) {
            System.out.println("nodeName not allow contain separation character [;]");
        }
        if (links.containsKey(startNodeName)) {
            links.put(startNodeName, links.get(startNodeName) + sepa + endNodeName);
            return;
        }
        links.put(startNodeName, endNodeName);
    }

    /**
     * @param startNode
     * @param endNode
     * @Title addLine
     * @Description 添加线，初始化邻接矩阵
     * @date 2018年5月17日
     */
    public static void addLine(String startNode, String endNode) {
        int startIndex = addNode(startNode);
        int endIndex = addNode(endNode);
        if (startIndex >= 0 && endIndex >= 0) {
            adjacencyMatrix[startIndex][endIndex] = 1;
            addLink(startNode, endNode);
        }
    }

    /**
     * @return
     * @Title findCycle
     * @Description 寻找闭环
     * @date 2018年5月17日
     */
    public static List<String> findCycle() {
        // 从出发节点到当前节点的轨迹
        List<Integer> trace = Lists.newArrayList();
        //返回值
        List<String> result = Lists.newArrayList();
        if (adjacencyMatrix.length > 0) {
            findCycle(0, trace, result);
        }
        if (result.size() == 0) {
            result.add("no cycle!");
        }
        return result;
    }

    /**
     * @param v
     * @param trace
     * @param result
     * @Title findCycle
     * @Description dfs
     * @date 2018年5月17日
     */
    private static void findCycle(int v, List<Integer> trace, List<String> result) {
        int j;
        //添加闭环信息
        if ((j = trace.indexOf(v)) != -1) {
            StringBuffer sb = new StringBuffer();
            String startNode = nodes.get(trace.get(j));
            while (j < trace.size()) {
                sb.append(nodes.get(trace.get(j)) + "-");
                j++;
            }
            result.add("cycle:" + sb.toString() + startNode);
            return;
        }
        trace.add(v);
        for (int i = 0; i < nodes.size(); i++) {
            if (adjacencyMatrix[v][i] == 1) {
                findCycle(i, trace, result);
            }
        }
        trace.remove(trace.size() - 1);
    }

    public static void main(String[] args) {
        DsfCycle.addLine("A", "B");
        DsfCycle.addLine("A", "C");
        DsfCycle.addLine("B", "D");
        DsfCycle.addLine("D", "A");
        DsfCycle.addLine("D", "C");
        DsfCycle.addLine("D", "B");
        List<String> result = DsfCycle.findCycle();
        for (String string : result) {
            System.out.println(string);
        }
        Iterator iterator = links.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

