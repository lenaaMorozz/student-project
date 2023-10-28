package com.mer.groups;

@FunctionalInterface
public interface GroupCriterion<T> {
    Object getKey(T element);
}
