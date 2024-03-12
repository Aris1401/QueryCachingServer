package com.sqlcaching.server.dto;

import lombok.Data;

@Data
public class CacheObjectRequest {
    String query;
    String data;
    double lifetimeDuration;
}
