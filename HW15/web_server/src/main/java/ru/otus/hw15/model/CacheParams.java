package ru.otus.hw15.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CacheParams {
    private int hint;
    private int miss;
    private int size;

    public CacheParams(int hint, int miss, int size) {
        this.hint = hint;
        this.miss = miss;
        this.size = size;
    }
}
