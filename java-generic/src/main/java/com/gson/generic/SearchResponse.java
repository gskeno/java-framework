package com.gson.generic;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * 将json字符串转化为泛型对象
 * opensearch返回示例
 * {"status":"OK","request_id":"157736198319535762540394","result":{"searchtime":0.697602,"total":4,"num":4,"viewtotal":4,"compute_cost":[{"index_name":"op_user_entry_record","value":0.359}],"items":[{"global_id":"4eleme1121","membership_id":"11","merchant_id":"199","seller_id":"1999","seller_site":"taobao","site":"eleme","user_id":"4","entry_time":"21","exit_time":"0","index_name":"op_user_entry_record"},{"global_id":"1eleme719","membership_id":"7","merchant_id":"177","seller_id":"1777","seller_site":"taobao","site":"eleme","user_id":"1","entry_time":"19","exit_time":"0","index_name":"op_user_entry_record"},{"global_id":"1taobao717","membership_id":"7","merchant_id":"177","seller_id":"1777","seller_site":"taobao","site":"taobao","user_id":"1","entry_time":"17","exit_time":"0","index_name":"op_user_entry_record"},{"global_id":"2taobao917","membership_id":"9","merchant_id":"199","seller_id":"1999","seller_site":"taobao","site":"taobao","user_id":"2","entry_time":"17","exit_time":"0","index_name":"op_user_entry_record"}],"facet":[]},"errors":[],"tracer":"","ops_request_misc":"%7B%22request%5Fid%22%3A%22157736198319535762540394%22%2C%22scm%22%3A%221.100123400..%22%7D"}
 * @date 2019/12/26
 */
public class SearchResponse<T> {

    enum Fields{
        time;
    }
    private String status;
    private String request_id;
    private Result<T> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Result<T> getResult() {
        return result;
    }

    public void setResult(Result<T> result) {
        this.result = result;
    }

    public boolean ok(){
        return "OK".equals(status);
    }
    @Override
    public String toString() {
        return "SearchResponse{" +
                "status='" + status + '\'' +
                ", request_id='" + request_id + '\'' +
                ", result=" + result +
                '}';
    }

    public static final class Result<T>{
        private int total;
        private List<T> items;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<T> getItems() {
            return items;
        }

        public void setItems(List<T> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "total=" + total +
                    ", items=" + items +
                    '}';
        }
    }

    public static final class Entry{
        private String exit_time;
        private String entry_time;
        private String site;

        public String getExit_time() {
            return exit_time;
        }

        public void setExit_time(String exit_time) {
            this.exit_time = exit_time;
        }

        public String getEntry_time() {
            return entry_time;
        }

        public void setEntry_time(String entry_time) {
            this.entry_time = entry_time;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "exit_time='" + exit_time + '\'' +
                    ", entry_time='" + entry_time + '\'' +
                    ", site='" + site + '\'' +
                    '}';
        }
    }

    public  static <T> SearchResponse<T> execute(String s) {
        SearchResponse<T> mapSearchResponse = JSONObject.parseObject(s, new TypeReference<SearchResponse<T>>() {

        });

        return mapSearchResponse;
    }
    public static void main(String[] args) {
        String s = "{\"status\":\"OK\",\"request_id\":\"157736198319535762540394\",\"result\":{\"searchtime\":0.697602,\"total\":4,\"num\":4,\"viewtotal\":4,\"compute_cost\":[{\"index_name\":\"op_user_entry_record\",\"value\":0.359}],\"items\":[{\"global_id\":\"4eleme1121\",\"membership_id\":\"11\",\"merchant_id\":\"199\",\"seller_id\":\"1999\",\"seller_site\":\"taobao\",\"site\":\"eleme\",\"user_id\":\"4\",\"entry_time\":\"21\",\"exit_time\":\"0\",\"index_name\":\"op_user_entry_record\"},{\"global_id\":\"1eleme719\",\"membership_id\":\"7\",\"merchant_id\":\"177\",\"seller_id\":\"1777\",\"seller_site\":\"taobao\",\"site\":\"eleme\",\"user_id\":\"1\",\"entry_time\":\"19\",\"exit_time\":\"0\",\"index_name\":\"op_user_entry_record\"},{\"global_id\":\"1taobao717\",\"membership_id\":\"7\",\"merchant_id\":\"177\",\"seller_id\":\"1777\",\"seller_site\":\"taobao\",\"site\":\"taobao\",\"user_id\":\"1\",\"entry_time\":\"17\",\"exit_time\":\"0\",\"index_name\":\"op_user_entry_record\"},{\"global_id\":\"2taobao917\",\"membership_id\":\"9\",\"merchant_id\":\"199\",\"seller_id\":\"1999\",\"seller_site\":\"taobao\",\"site\":\"taobao\",\"user_id\":\"2\",\"entry_time\":\"17\",\"exit_time\":\"0\",\"index_name\":\"op_user_entry_record\"}],\"facet\":[]},\"errors\":[],\"tracer\":\"\",\"ops_request_misc\":\"%7B%22request%5Fid%22%3A%22157736198319535762540394%22%2C%22scm%22%3A%221.100123400..%22%7D\"}\n";
        SearchResponse<Entry> execute = execute(s);
        System.out.println(execute);
    }
}
