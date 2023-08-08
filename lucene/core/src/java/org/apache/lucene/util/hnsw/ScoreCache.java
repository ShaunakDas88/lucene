package org.apache.lucene.util.hnsw;

import java.util.HashMap;
import java.util.Map;


/**
 * Stupidly simple cache for keeping scores between a newly-adding node and existing node
 */
public class ScoreCache {
    private int hits;
    private int total;
    private final Map<Integer, Float> scoreMap;

    public ScoreCache() {
        this.hits = 0;
        this.total = 0;
        this.scoreMap = new HashMap<>();
    }

    public Float get(int ordinal) {
        Float val = scoreMap.get(ordinal);
        total++;
        if (val != null) {
            hits++;
        }
        return val;
    }

    public void put(int ordinal, float score) {
        scoreMap.put(ordinal, score);
    }

    public void clear() {
        hits = 0;
        total = 0;
        scoreMap.clear();
    }

    public float hitRate() {
        return ((float) hits / total) * 100;
    }
}