package com.mer;

import com.mer.model.Student;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataGroup<T> {
    private final GroupCriterion<T> groupCriterion;
    private final Map<Object, List<T>> dataGroup = new HashMap<>();

    public DataGroup(GroupCriterion<T> groupCriterion) {
        this.groupCriterion = groupCriterion;
    }

    public void add(T element) {
        Object key = groupCriterion.getKey(element);
        if (!dataGroup.containsKey(key)) {
            dataGroup.put(key, new LinkedList<>());
        }
        dataGroup.get(key).add(element);
    }

    public List<T> getList(Object key) {
        return dataGroup.get(key);
    }
}
