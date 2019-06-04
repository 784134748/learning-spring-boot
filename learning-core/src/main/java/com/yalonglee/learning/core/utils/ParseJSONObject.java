package com.yalonglee.learning.core.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class ParseJSONObject {

//    public static void main(String[] args) {
//
//        String oldStr = "{\"pid\":1,\"title\":\"体型\"}";
//        String newStr = "{\"pid\":2,\"title\":\"体型选择\",\"description\":\"描述\"}";
//
//        JSONObject oldJsonObject = JSONObject.fromObject(oldStr);
//        JSONObject newJsonObject = JSONObject.fromObject(newStr);
//        JSONObject threeJsonObject = new JSONObject();
//        threeJsonObject.putAll(newJsonObject);
//        threeJsonObject.putAll(oldJsonObject);
//        System.out.println(threeJsonObject);
//
//
//        String jsonArrayStr = "[{\"title\": \"参考尺寸\", \"sizeList\": [{\"code\": \"code\", \"name\": \"尺寸\"}, {\"code\": \"23\", \"name\": \"阿飞\"}]}, {\"title\": \"量体方式\", \"cleanList\": [{\"code\": \"1\", \"name\": \"1\", \"isMust\": 1, \"maxSize\": \"1\", \"minSize\": \"1\"}], \"readyList\": [{\"code\": \"code\", \"name\": \"尺寸\", \"isMust\": 1, \"maxSize\": \"1\", \"minSIze\": \"\"}, {\"code\": \"23\", \"name\": \"阿飞\", \"isMust\": 1, \"maxSize\": \"\", \"minSize\": \"\"}], \"setNumber\": [{\"code\": \"code\", \"name\": \"尺寸\", \"isMust\": 1}, {\"code\": \"23\", \"name\": \"阿飞\", \"isMust\": 1}]}, {\"title\": \"体型选择\", \"shapeList\": [{\"code\": \"1\", \"list\": [{\"sonCode\": \"1\", \"sonPhoto\": \"category-image/94518_14_20190526.png\", \"sonValue\": \"1\"}], \"name\": \"1\", \"type\": 1, \"isMust\": 1}, {\"code\": \"2\", \"list\": [{\"sonCode\": \"1\", \"sonPhoto\": \"category-image/95924_14_20190527.png\", \"sonValue\": \"1\"}], \"name\": \"2\", \"type\": 1, \"isMust\": 1}]}, {\"title\": \"个性设计\", \"individualizationList\": [{\"code\": \"1\", \"list\": [{\"sonCode\": \"1\", \"sonName\": \"1\", \"sonPhoto\": \"category-image/21049_14_20190526.png\", \"isAcquiesce\": 1, \"sonAddition\": \"1\"}, {\"sonCode\": \"3\", \"sonName\": \"2\", \"sonPhoto\": \"category-image/84636_14_20190529.png\", \"isAcquiesce\": 0, \"sonAddition\": \"4\"}], \"name\": \"1\", \"isMust\": 1}]}, {\"title\": \"深度设计\", \"depthList\": [{\"code\": \"1\", \"list\": [{\"sonCode\": \"1\", \"sonName\": \"1\", \"sonPhoto\": \"category-image/36243_14_20190526.png\", \"isAcquiesce\": 1, \"sonAddition\": \"1\"}], \"name\": \"1\", \"isMust\": 1, \"acquiesce\": {\"sonCode\": \"1\", \"sonName\": \"1\", \"sonPhoto\": \"category-image/36243_14_20190526.png\", \"isAcquiesce\": 1, \"sonAddition\": \"1\"}}]}, {\"title\": \"高级设计\", \"seniorList\": [{\"code\": \"1\", \"list\": [{\"sonCode\": \"1\", \"sonName\": \"1\", \"sonPhoto\": \"category-image/12350_14_20190526.png\", \"isAcquiesce\": 1, \"sonAddition\": \"1\"}], \"name\": \"1\", \"isMust\": 1, \"acquiesce\": {\"sonCode\": \"1\", \"sonName\": \"1\", \"sonPhoto\": \"category-image/12350_14_20190526.png\", \"isAcquiesce\": 1, \"sonAddition\": \"1\"}}]}, {\"title\": \"刺绣设计\", \"textList\": [{\"code\": \"1\", \"list\": [{\"sonCode\": \"1\", \"sonName\": \"1\", \"sonPhoto\": \"category-image/45165_14_20190526.png\", \"isAcquiesce\": 1, \"sonAddition\": \"1\"}], \"name\": \"1\", \"isMust\": 1, \"acquiesce\": {\"sonCode\": \"1\", \"sonName\": \"1\", \"sonPhoto\": \"category-image/45165_14_20190526.png\", \"isAcquiesce\": 1, \"sonAddition\": \"1\"}}], \"depotList\": [{\"code\": \"1\", \"list\": [{\"sonCode\": \"1\", \"sonName\": \"1\", \"sonPhoto\": \"category-image/58767_14_20190526.png\", \"isAcquiesce\": 1, \"sonAddition\": \"1\"}], \"name\": \"1\", \"isMust\": 1, \"acquiesce\": {\"sonCode\": \"1\", \"sonName\": \"1\", \"sonPhoto\": \"category-image/58767_14_20190526.png\", \"isAcquiesce\": 1, \"sonAddition\": \"1\"}}]}]";
//        JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr);
//
//        List<JSONObject> addJsonObjectList = Lists.newArrayList();
//        List<JSONObject> updateJsonObjectList = Lists.newArrayList();
//        Map<Integer, String> titles = Maps.newHashMap();
//        Map<Integer, String> keys = Maps.newHashMap();
//        parseArray(titles, keys, 0, addJsonObjectList, updateJsonObjectList, jsonArray);
//        /**
//         * 新增属性
//         */
//        addJsonObjectList.forEach(addJsonObject -> System.out.println(addJsonObject));
//        /**
//         * 更新属性
//         */
//        updateJsonObjectList.forEach(updateJsonObject -> System.out.println(updateJsonObject));
//        /**
//         * 插入id后的jsonArray
//         */
//        System.out.println(jsonArray);
//        //将所有属性置为已删除状态，更新属性后重新生效，新增属性有效
//
//    }
//
//    private static void parseArray(Map<Integer, String> titles, Map<Integer, String> keys, Integer level, List<JSONObject> addJsonObjectList, List<JSONObject> updateJsonObjectList, JSONArray jsonArray) {
//        if (titles.get(level) != null) {
//            System.out.println(titles.get(level));
//            System.out.println(keys.get(level));
//            System.out.println(jsonArray);
//        }
//
//        if (jsonArray != null && !jsonArray.isEmpty()) {
//            jsonArray.forEach(jsonObject -> {
//                if (jsonObject instanceof JsonArray) {
//                    parseArray(titles, keys, level + 1, addJsonObjectList, updateJsonObjectList, (JSONArray) jsonObject);
//                }
//                if (jsonObject instanceof JSONObject) {
//                    parseObject(titles, keys, level + 1, addJsonObjectList, updateJsonObjectList, (JSONObject) jsonObject);
//                }
//            });
//        }
//    }
//
//    private static void parseObject(Map<Integer, String> titles, Map<Integer, String> keys, Integer level, List<JSONObject> addJsonObjectList, List<JSONObject> updateJsonObjectList, JSONObject jsonObject) {
//        String title = (String) jsonObject.get("title");
//        titles.put(level, title);
//        Map<String, Object> properties = Maps.newHashMap();
//        properties.put("level", level);
//        Iterator iterator = jsonObject.keySet().iterator();
//        while (iterator.hasNext()) {
//            String key = (String) iterator.next();
//            if (jsonObject.get(key) instanceof JSONArray) {
//                keys.put(level, key);
//                parseArray(titles, keys, level, addJsonObjectList, updateJsonObjectList, (JSONArray) jsonObject.get(key));
//                continue;
//            }
//            if (jsonObject.get(key) instanceof JSONObject) {
//                parseObject(titles, keys, level, addJsonObjectList, updateJsonObjectList, (JSONObject) jsonObject.get(key));
//                continue;
//            }
//            properties.put(key, jsonObject.get(key));
//        }
//        if (properties.containsKey("id")) {
//            updateJsonObjectList.add(JSONObject.fromObject(properties));
//        } else {
//            String uuid = UUID.randomUUID().toString().replace("-", "");
//            jsonObject.put("id", uuid);
//            jsonObject.put("value", "");
//            jsonObject.put("select", false);
//            addJsonObjectList.add(JSONObject.fromObject(properties));
//        }
//    }

}
