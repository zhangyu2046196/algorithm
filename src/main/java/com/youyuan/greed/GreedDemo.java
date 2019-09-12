package com.youyuan.greed;

import java.util.*;

/**
 * @author zhangyu
 * @version 1.0
 * @description 贪心算法  解决集合覆盖问题
 * <p>
 * 要求：
 * 假设存在如下表的需要付费的广播台，以及广播台信号可以覆盖的地区。 如何选择最少的广播台，让所有的地区都可以接收到信号
 * <p>
 * 背景：
 * 广播台	覆盖地区
 * K1	   "北京", "上海", "天津"
 * K2	   "广州", "北京", "深圳"
 * K3	   "成都", "上海", "杭州"
 * K4	   "上海", "天津"
 * K5	   "杭州", "大连"
 * <p>
 * 思路：
 * <p>
 * 1.定义allareas集合存储所有地区名称 不能重复
 * 2.定义select集合用于存储选定的广播台
 * 3.把上述内容放入Map<String,HashSet<String>> 中
 * 4.遍历map
 * 5.每次遍历时找到覆盖地区与allareas交集最多的广播台，然后把广播台放入select集合,然后从allareas中
 * 删除交集的广播台
 * 6.继续遍历，直到allareas里面元素为空
 * 7.select里面的广播台就是最后求解
 * @date 2019/8/23 13:33
 */
public class GreedDemo {

    public static void main(String[] args) {

        //存放原始数据
        Map<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();

        //初始化广播台对应的广播地区
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //组装
        map.put("K1", hashSet1);
        map.put("K2", hashSet2);
        map.put("K3", hashSet3);
        map.put("K4", hashSet4);
        map.put("K5", hashSet5);

        //定义allAreas来存储所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        for (String key : map.keySet()) {
            allAreas.addAll(map.get(key));
        }

        //定义temp临时存放覆盖地区
        HashSet<String> temp = new HashSet<String>();
        //存储选择出的电台
        List<String> selectList = new ArrayList<String>();
        //定义存储交集最大的key
        String maxKey = null;
        //存储数量最大交集的数量
        int count = 0;
        //待比较的集合中有数据代表没有匹配完
        while (allAreas.size() > 0) {
            maxKey = null;
            count = 0;
            //循环遍历map比较交集最大的k
            for (String key : map.keySet()) {
                temp.clear();
                temp.addAll(map.get(key));
                //计算交集 把交集结果在赋值给temp
                temp.retainAll(allAreas);
                if (maxKey == null || count < temp.size()) {
                    count = temp.size();
                    maxKey = key;
                }
            }
            //删除allAreas集合中的交集元素
            if (maxKey != null) {
                //把选出来的电台加到集合中
                selectList.add(maxKey);
                //从所有地区列表删除匹配过的电台覆盖的地区
                allAreas.removeAll(map.get(maxKey));
            }
        }

        System.out.println("覆盖所有地区的电台集合是:" + selectList);

    }

}
