package com.mer;

@FunctionalInterface
public interface GroupCriterion<T> {
    Object getKey(T element);
}
