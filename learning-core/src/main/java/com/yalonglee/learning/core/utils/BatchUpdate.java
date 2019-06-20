package com.yalonglee.learning.core.utils;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class BatchUpdate {

    public static EnumMap<BatchUpdateOperation, Object> batchUpdate(List<BaseId> submitList, List<Fruit> queryList) {

        List<BaseId> submit = Lists.newArrayList();
        List<Fruit> query = Lists.newArrayList();

        ArrayList<BaseId> batchUpdate = Lists.newArrayList();
        ArrayList<BaseId> batchAdd = Lists.newArrayList();

        ArrayList<Long> submitIds = Lists.newArrayList();
        ArrayList<Long> queryIds = Lists.newArrayList();
        ArrayList<Long> batchRemoveIds = Lists.newArrayList();


        submit.stream().forEach(id -> {
            if (id.getId() == null) {
                batchAdd.add(id);
            }
            if (id.getId() != null && id.getId() > 0L) {
                submitIds.add(id.getId());
                batchUpdate.add(id);
            }
        });
        query.stream().forEach(id -> queryIds.add(id.getId()));
        queryIds.removeAll(submitIds);
        batchRemoveIds = queryIds;

        EnumMap<BatchUpdateOperation, Object> enumMap = new EnumMap<>(BatchUpdateOperation.class);
        enumMap.put(BatchUpdateOperation.ADD, batchAdd);
        enumMap.put(BatchUpdateOperation.REMOVE, batchRemoveIds);
        enumMap.put(BatchUpdateOperation.UPDATE, batchUpdate);

        return enumMap;
    }

    public static void main(String[] args) {
        ArrayList<BaseId> submit = Lists.newArrayList();
        submit.add(new Fruit(2L, "苹果"));
        submit.add(new Fruit(3L, "香蕉"));
        submit.add(new Fruit(4L, "橘子"));
        submit.add(new Fruit(null, "橙子"));

        ArrayList<Fruit> query = Lists.newArrayList();
        query.add(new Fruit(2L, "苹果"));
        query.add(new Fruit(3L, "香蕉"));
        query.add(new Fruit(4L, "橘子"));
        query.add(new Fruit(5L, "哈密瓜"));

        EnumMap<BatchUpdateOperation, Object> enumMap = BatchUpdate.batchUpdate(submit, query);
        List<Fruit> updateFruitList = (List<Fruit>) enumMap.get(BatchUpdateOperation.UPDATE);
        List<Long> removeFruitList = (List<Long>) enumMap.get(BatchUpdateOperation.REMOVE);
        List<Fruit> addFruitList = (List<Fruit>) enumMap.get(BatchUpdateOperation.ADD);

        System.out.println("批量新增:");
        addFruitList.forEach(a -> System.out.print(a.toString()));
        System.out.println();
        System.out.println("批量删除:");
        removeFruitList.forEach(a -> System.out.print(a));
        System.out.println();
        System.out.println("批量更新:");
        updateFruitList.forEach(a -> System.out.print(a.toString()));


    }

}

abstract class BaseId {
    private Long id;

    public BaseId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

class Fruit extends BaseId {
    private String name;

    public Fruit(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                '}';
    }
}

enum BatchUpdateOperation {
    REMOVE,
    ADD,
    UPDATE
}



