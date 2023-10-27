package com.mer.util;

import java.util.Optional;

public interface DataLoader<T> {
    Optional<T> load();
}
