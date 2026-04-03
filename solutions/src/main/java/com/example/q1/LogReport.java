package com.example.q1;

import java.util.Map;

record LogReport(Map<String, Long> counts, Map<String, String> latestMessages) {

}
