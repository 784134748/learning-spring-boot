package com.yalonglee.learning.core.utils;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class BatchUpdate {

    public static EnumMap<BatchUpdateOperation, Object> batchUpdate(ArrayList<BaseId> submit, ArrayList<BaseId> query) {

        ArrayList<BaseId> update = Lists.newArrayList();
        ArrayList<BaseId> add = Lists.newArrayList();

        ArrayList<Long> submitIds = Lists.newArrayList();
        ArrayList<Long> queryIds = Lists.newArrayList();
        ArrayList<Long> removeIds = Lists.newArrayList();


        submit.stream().forEach(id -> {
            if (id.getId() == null) {
                add.add(id);
            }
            if (id.getId() != null && id.getId() > 0L) {
                submitIds.add(id.getId());
                update.add(id);
            }
        });
        query.stream().forEach(id -> {
            queryIds.add(id.getId());
        });
        queryIds.removeAll(submitIds);
        removeIds = queryIds;

        EnumMap<BatchUpdateOperation, Object> enumMap = new EnumMap<>(BatchUpdateOperation.class);
        enumMap.put(BatchUpdateOperation.ADD, add);
        enumMap.put(BatchUpdateOperation.REMOVE, removeIds);
        enumMap.put(BatchUpdateOperation.UPDATE, update);

        return enumMap;
    }

    public static void main(String[] args) {
        ArrayList<BaseId> submit = Lists.newArrayList();
        submit.add(new Fruit(2L, "苹果"));
        submit.add(new Fruit(3L, "香蕉"));
        submit.add(new Fruit(4L, "橘子"));
        submit.add(new Fruit(null, "橙子"));

        ArrayList<BaseId> query = Lists.newArrayList();
        query.add(new Fruit(2L, "苹果"));
        query.add(new Fruit(3L, "香蕉"));
        query.add(new Fruit(4L, "橘子"));
        query.add(new Fruit(5L, "哈密瓜"));

        EnumMap<BatchUpdateOperation, Object> enumMap = BatchUpdate.batchUpdate(submit, query);
        List<Fruit> updateFruitList = (List<Fruit>) enumMap.get(BatchUpdateOperation.UPDATE);
        List<Long> removeFruitList = (List<Long>) enumMap.get(BatchUpdateOperation.REMOVE);
        List<Fruit> addFruitList = (List<Fruit>) enumMap.get(BatchUpdateOperation.ADD);

        System.out.println("新增:");
        addFruitList.forEach(a -> System.out.print(a.toString()));
        System.out.println();
        System.out.println("删除:");
        removeFruitList.forEach(a -> System.out.print(a));
        System.out.println();
        System.out.println("更新:");
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
    UPDATE;
}



