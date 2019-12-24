package com.yh.csx.bsf.elasticsearch.impl;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElasticSearchProvider implements AutoCloseable {
	
	private static final int MAX = 10000;
    private TransportClient transportClient;
	
	public ElasticSearchProvider(TransportClient transportClient) {
	    this.transportClient = transportClient;
	}
	
	/**
     * 功能描述：新建索引
     *
     * @param indexName 索引名
     */
    public void createIndex(String indexName) {
        ElasticSearchMonitor.hook().run("createIndex", () -> {
            getClient().admin().indices().create(new CreateIndexRequest(indexName)).actionGet();
        });
    }

    /**
     * 功能描述：新建索引
     *
     * @param index 索引名
     * @param type  类型
     */
    public void createIndex(String index, String type) {
        ElasticSearchMonitor.hook().run("createIndex", () -> {
            getClient().prepareIndex(index, type).setSource().get();
        });
    }

    /**
     * 功能描述：删除索引
     *
     * @param index 索引名
     */
    public void deleteIndex(String index) {
        ElasticSearchMonitor.hook().run("deleteIndex", () -> {
            if (indexExist(index)) {
               AcknowledgedResponse response = getClient().admin().indices().prepareDelete(index) .execute().actionGet();
                if (!response.isAcknowledged()) {
                    throw new ElasticsearchException("failed to delete index.");
                }
            } else {
                throw new ElasticsearchException("index name not exists");
            }
        });
    }

    /**
     * 功能描述：验证索引是否存在
     *
     * @param index 索引名
     */
    public boolean indexExist(String index) {
        return ElasticSearchMonitor.hook().run("indexExist", () -> {
            IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(index);
            IndicesExistsResponse inExistsResponse = getClient().admin().indices().exists(inExistsRequest).actionGet();
            return inExistsResponse.isExists();
        });
    }

    /**
     * 功能描述：插入数据
     *
     * @param index 索引名
     * @param type  类型
     * @param json  数据
     */
    public void insertData(String index, String type, String json) {
        ElasticSearchMonitor.hook().run("insertData", () -> {
            getClient().prepareIndex(index, type).setSource(json).get();
        });
    }

    /**
     * 功能描述：插入数据
     *
     * @param index 索引名
     * @param type  类型
     * @param _id   数据id
     * @param json  数据
     */
    public void insertData(String index, String type, String _id, String json) {
        ElasticSearchMonitor.hook().run("insertData", () -> {
            getClient().prepareIndex(index, type).setId(_id).setSource(json).get();
        });
    }
    /**
     * 	 功能描述：插入数据
     *
     * @param index 索引名
     * @param type  类型
     * @param _id   数据id
     * @param json  数据
     */
    public void insertData(String index, String type, String _id, Map<String,Object> json) {
        ElasticSearchMonitor.hook().run("insertData", () -> {
            getClient().prepareIndex(index, type).setId(_id).setSource(json).get();
        });
    }
    /**
     * 功能描述：更新数据
     *
     * @param index 索引名
     * @param type  类型
     * @param _id   数据id
     * @param json  数据
     */
    public void updateData(String index, String type, String _id, String json) throws Exception {
        ElasticSearchMonitor.hook().run("updateData", () -> {
            try {
                UpdateRequest updateRequest = new UpdateRequest(index, type, _id).doc(json);
                getClient().update(updateRequest).get();
            } catch (Exception e) {
                throw new ElasticsearchException("update data failed.", e);
            }
        });
    }
    /**
     * 功能描述：更新数据
     *
     * @param index 索引名
     * @param type  类型
     * @param _id   数据id
     * @param json  数据
     */
    public void updateData(String index, String type, String _id, Map<String,Object> map) throws Exception {
        ElasticSearchMonitor.hook().run("updateData", () -> {
            try {
                UpdateRequest updateRequest = new UpdateRequest(index, type, _id).doc(map);
                getClient().update(updateRequest).get();
            } catch (Exception e) {
                throw new ElasticsearchException("update data failed.", e);
            }
        });
    }

    /**
     * 功能描述：删除数据
     *
     * @param index 索引名
     * @param type  类型
     * @param _id   数据id
     */
    public void deleteData(String index, String type, String _id) {
        ElasticSearchMonitor.hook().run("deleteData", () -> {
            getClient().prepareDelete(index, type, _id).get();
        });
    }

    /**
     * 功能描述：批量插入数据
     *
     * @param index 索引名
     * @param type  类型
     * @param data  (_id 主键, json 数据)
     */
    public void bulkInsertData(String index, String type, Map<String, String> data) {
        ElasticSearchMonitor.hook().run("bulkInsertData", () -> {
            BulkRequestBuilder bulkRequest = getClient().prepareBulk();
            data.forEach((param1, param2) -> {
                bulkRequest.add(getClient().prepareIndex(index, type, param1).setSource(param2)
                );
            });
            bulkRequest.get();
        });
    }

    /**
     * 功能描述：批量插入数据
     *
     * @param index    索引名
     * @param type     类型
     * @param jsonList 批量数据
     */
    public void bulkInsertData(String index, String type, List<String> jsonList) {
        ElasticSearchMonitor.hook().run("bulkInsertData", () -> {
            BulkRequestBuilder bulkRequest = getClient().prepareBulk();
            jsonList.forEach(item -> {
                bulkRequest.add(getClient().prepareIndex(index, type).setSource(item)
                );
            });
            bulkRequest.get();
        });
    }

    /**
     * 功能描述：查询
     *
     * @param index        索引名
     * @param type         类型
     * @param queryBuilder 查询构造
     */
    public List<Map<String, Object>> search(String index, String type, QueryBuilder queryBuilder, int size, int from) {
        return ElasticSearchMonitor.hook().run("search", () -> {
            List<Map<String, Object>> result = new ArrayList<>();
            SearchRequestBuilder searchRequestBuilder = getClient().prepareSearch(index).setTypes(type);
            searchRequestBuilder.setQuery(queryBuilder);
            //返回条目数
            searchRequestBuilder.setSize(size < 0 ? 0 : (size > MAX ? MAX : size));

            searchRequestBuilder.setFrom(from);

            SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit sh : searchHists) {
                result.add(sh.getSourceAsMap());
            }
            return result;
        });
    }

    /**
     * 功能描述：排序查询
     *
     * @param index        索引名
     * @param type         类型
     * @param queryBuilder 查询构造
     */
    public List<Map<String, Object>> search(String index, String type, QueryBuilder queryBuilder, int size, int from, String orderBy, String order) {
        return ElasticSearchMonitor.hook().run("search", () -> {
            List<Map<String, Object>> result = new ArrayList<>();
            SearchRequestBuilder searchRequestBuilder = getClient().prepareSearch(index).setTypes(type);
            searchRequestBuilder.setQuery(queryBuilder);
            if (!org.apache.commons.lang3.StringUtils.isAnyBlank(orderBy, order)) {
                if (StringUtils.equalsIgnoreCase(order, "asc")) {
                    searchRequestBuilder.addSort(SortBuilders.fieldSort(orderBy).order(SortOrder.ASC));
                } else {
                    searchRequestBuilder.addSort(SortBuilders.fieldSort(orderBy).order(SortOrder.DESC));
                }
            }
            //返回条目数
            searchRequestBuilder.setSize(size < 0 ? 0 : (size > MAX ? MAX : size));

            searchRequestBuilder.setFrom(from);

            SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

            SearchHits hits = searchResponse.getHits();
            long totalHits = hits.getTotalHits();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit sh : searchHists) {
                sh.getSourceAsMap().put("totalHits", totalHits);
                result.add(sh.getSourceAsMap());
            }
            return result;
        });
    }

    /**
     * 功能描述：查询
     *
     * @param index        索引名
     * @param type         类型
     * @param queryBuilder 查询构造
     */
    public List<Map<String, Object>> searchOfSort(String index, String type, QueryBuilder queryBuilder, List<SortBuilder<?>> sortBuilders, int size, int from) {
        return ElasticSearchMonitor.hook().run("searchOfSort", () -> {
            List<Map<String, Object>> result = new ArrayList<>();
            SearchRequestBuilder searchRequestBuilder = getClient().prepareSearch(index).setTypes(type);
            searchRequestBuilder.setQuery(queryBuilder);
            //默认排序
            sortBuilders.stream().forEach(sb -> searchRequestBuilder.addSort(sb));

            //返回条目数
            searchRequestBuilder.setSize(size < 0 ? 0 : (size > MAX ? MAX : size));

            searchRequestBuilder.setFrom(from);

            SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

            SearchHits hits = searchResponse.getHits();
            long totalHits = hits.getTotalHits();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit sh : searchHists) {
                sh.getSourceAsMap().put("totalHits", totalHits);
                result.add(sh.getSourceAsMap());
            }
            return result;
        });
    }
    
    /***
    *
    * 分组查询
    */
   public List<Map<String, Object>> searchDataGroupBy(String index, String type, QueryBuilder queryBuilder, int size, int innerSize, String group) {
       return ElasticSearchMonitor.hook().run("searchDataGroupBy", () -> {
           List<Map<String, Object>> result = new ArrayList<>();
           AggregationBuilder aggBuilder = AggregationBuilders.terms("agg")
                   .field(group)
                   .subAggregation(
                           AggregationBuilders.topHits("top").size(innerSize)
                   ).size(size);

           SearchRequestBuilder searchRequestBuilder = getClient().prepareSearch(index).setTypes(type);
           searchRequestBuilder.setQuery(queryBuilder);
           SearchResponse sr = searchRequestBuilder.addAggregation(aggBuilder).get();
           Terms agg = sr.getAggregations().get("agg");
           for (Terms.Bucket entry : agg.getBuckets()) {
               TopHits topHits = entry.getAggregations().get("top");
               SearchHit[] searchHists = topHits.getHits().getHits();
               for (SearchHit sh : searchHists) {
                   result.add(sh.getSourceAsMap());
               }
           }
           return result;
       });
   }



    /**
     * 功能描述：关闭链接
     */
    @Override
    public void close() {
        if(transportClient!=null){transportClient.close();}
    }

    public TransportClient getClient() {
		return transportClient;
	}
}
