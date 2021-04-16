//package com.example.provider.config;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.ActionListener;
//import org.elasticsearch.action.bulk.BackoffPolicy;
//import org.elasticsearch.action.bulk.BulkProcessor;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.unit.ByteSizeUnit;
//import org.elasticsearch.common.unit.ByteSizeValue;
//import org.elasticsearch.common.unit.TimeValue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.function.BiConsumer;
//
///**
// * es配置
// *
// * @author wakening
// */
//@Getter
//@Setter
//@Configuration
//public class ElasticsearchConfig {
//
//    @Value("${template.elasticsearch.bulk.bulkActions:100}")
//    private int bulkActions;
//
//    @Value("${template.elasticsearch.bulk.bulkSizeMb:2}")
//    private long bulkSizeMb;
//
//    @Value("${template.elasticsearch.bulk.concurrentRequests:1}")
//    private int concurrentRequests;
//
//    @Value("${template.elasticsearch.bulk.flushIntervalSeconds:5}")
//    private long flushIntervalSeconds;
//
//    @Value("${template.elasticsearch.bulk.backoffDelaySeconds:1}")
//    private long backoffDelaySeconds;
//
//    @Value("${template.elasticsearch.bulk.backoffMaxNumberOfRetries:3}")
//    private int backoffMaxNumberOfRetries;
//
//    /**
//     * es高阶客户端，默认配置，配置调优参考@see
//     *
//     * @see org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientProperties
//     * @see org.elasticsearch.client.RestClientBuilder
//     */
//    @Autowired
//    private RestHighLevelClient highLevelClient;
//
//    /**
//     * es定时批量客户端
//     *
//     * @return BulkProcessor
//     */
//    @Bean(destroyMethod = "close")
//    public BulkProcessor bulkProcessor() {
//        BulkProcessor.Listener listener = new BulkProcessorListener();
//        BiConsumer<BulkRequest, ActionListener<BulkResponse>> bulkConsumer =
//                (request, bulkListener) -> highLevelClient.bulkAsync(request, RequestOptions.DEFAULT, bulkListener);
//        return BulkProcessor.builder(bulkConsumer, listener)
//                // 多少条数据请求执行一次bulk
//                .setBulkActions(this.bulkActions)
//                // 多少兆的数据刷新一次bulk
//                .setBulkSize(new ByteSizeValue(this.bulkSizeMb, ByteSizeUnit.MB))
//                // 并发发送bulk的数量，0表示同步执行每个批，大于1表示异步执行
//                .setConcurrentRequests(this.concurrentRequests)
//                // 固定5s必须刷新一次
//                .setFlushInterval(TimeValue.timeValueSeconds(this.flushIntervalSeconds))
//                // 重试3次，间隔1s
//                .setBackoffPolicy(BackoffPolicy.constantBackoff(
//                        TimeValue.timeValueSeconds(this.backoffDelaySeconds), this.backoffMaxNumberOfRetries))
//                .build();
//    }
//
//    @Slf4j
//    static class BulkProcessorListener implements BulkProcessor.Listener {
//        @Override
//        public void beforeBulk(long executionId, BulkRequest request) {
//            int numberOfActions = request.numberOfActions();
//            log.info("Executing bulk [{}] with {} requests", executionId, numberOfActions);
//        }
//
//        @Override
//        public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
//            if (response.hasFailures()) {
//                log.error("Bulk [{}] executed with failures, response = {}", executionId, response.buildFailureMessage());
//            } else {
//                log.info("Bulk [{}] completed in {} milliseconds", executionId, response.getTook().getMillis());
//            }
//        }
//
//        @Override
//        public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
//            log.error("Failed to execute bulk", failure);
//        }
//    }
//
//}
