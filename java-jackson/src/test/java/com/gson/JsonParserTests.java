package com.gson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class JsonParserTests {

    /**
     * case1.json中的json字符串内容如下
     * {
     *   "Auctions": [
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/bao/uploaded/i3/774105607/O1CN011rI3C3hE2bTBqEy_!!774105607.jpg",
     *       "SortExprValues": "3.13627815246582;229",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_10126503"
     *     },
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/bao/uploaded/i1/2872817779/O1CN0127KpT2YMnHu6zYV_!!2872817779.jpg",
     *       "SortExprValues": "3.04063725471497;198",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_11509556"
     *     },
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/bao/uploaded/i1/702142113/O1CN01Qo2TBY1RTnP2KVubv_!!702142113.jpg",
     *       "SortExprValues": "2.83944272994995;243",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_14295864"
     *     },
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/bao/uploaded/i2/2081297388/O1CN01o7eMBF24RkdeCWXaZ_!!2081297388.jpg",
     *       "SortExprValues": "2.75051164627075;226",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_13552438"
     *     },
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/imgextra/i1/2217548222/TB2j.W5eACWBuNjy0FaXXXUlXXa_!!2217548222.jpg",
     *       "SortExprValues": "2.72173500061035;245",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_13005422"
     *     },
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/imgextra/i1/424633193/TB2g7DfpKOSBuNjy0FdXXbDnVXa_!!424633193.jpg",
     *       "SortExprValues": "2.67716360092163;243",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_8599760"
     *     },
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/imgextra/i3/1695837829/O1CN0127hjGqxfLf0RODf_!!1695837829.jpg",
     *       "SortExprValues": "2.64812707901001;206",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_11509566"
     *     },
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/bao/uploaded/i1/1653242980/O1CN01TKtkkW1XssmSzPGVt_!!1653242980.jpg",
     *       "SortExprValues": "2.64754438400269;255",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_13552437"
     *     },
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/imgextra/i4/2828605004/O1CN01bPUOAF1mpsQKmGK2o_!!0-item_pic.jpg",
     *       "SortExprValues": "2.59448003768921;261",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_13618490"
     *     },
     *     {
     *       "CategoryId": 0,
     *       "PicName": "//img.alicdn.com/bao/uploaded/i4/2816099891/O1CN01UQw8km2Mw7z2dksAx_!!2816099891.jpg",
     *       "SortExprValues": "2.59078764915466;231",
     *       "CustomContent": "city:gz",
     *       "StrAttr": "gz",
     *       "ProductId": "0_12838142"
     *     }
     *   ],
     *   "Head": {
     *     "DocsReturn": 10,
     *     "DocsFound": 3774,
     *     "SearchTime": 458
     *   },
     *   "PicInfo": {
     *     "Region": "219,486,143,505",
     *     "CategoryId": 0,
     *     "AllCategories": [
     *       {
     *         "Name": "Tops",
     *         "Id": 0
     *       },
     *       {
     *         "Name": "Dress",
     *         "Id": 1
     *       },
     *       {
     *         "Name": "Bottoms",
     *         "Id": 2
     *       },
     *       {
     *         "Name": "Bag",
     *         "Id": 3
     *       },
     *       {
     *         "Name": "Shoes",
     *         "Id": 4
     *       },
     *       {
     *         "Name": "Accessories",
     *         "Id": 5
     *       },
     *       {
     *         "Name": "Snack",
     *         "Id": 6
     *       },
     *       {
     *         "Name": "Makeup",
     *         "Id": 7
     *       },
     *       {
     *         "Name": "Bottle",
     *         "Id": 8
     *       },
     *       {
     *         "Name": "Furniture",
     *         "Id": 9
     *       },
     *       {
     *         "Name": "Toy",
     *         "Id": 20
     *       },
     *       {
     *         "Name": "Underwear",
     *         "Id": 21
     *       },
     *       {
     *         "Name": "Digital device",
     *         "Id": 22
     *       },
     *       {
     *         "Name": "Other",
     *         "Id": 88888888
     *       }
     *     ]
     *   },
     *   "RequestId": "DB5C8AB5-9B32-48A0-A57E-6BE42C9D6011",
     *   "Msg": "success",
     *   "Success": true,
     *   "Code": 0
     * }
     * @throws IOException
     */
    @Test
    public void testReadFile() throws IOException {
        // 获取资源方式1
        URL resource = this.getClass().getResource("/case1.json");

        String json = IOUtils.toString(resource, "utf-8");
        System.out.println(json);

        ObjectMapper om = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        // 第三方的接口响应结果，我们经常只想要其中的一部分数据，那么
        // 定义响应model时，做了如下配置后，只需要包含想要的属性即可，
        // 其他结果在序列化化时会忽略掉
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 值为null的字段不参与序列化
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);


        /**jackson 没有jsonObject对象，一般就是把json字符串转成业务对象**/
        SearchResp searchResp = om.readValue(json, SearchResp.class);
        System.out.println(searchResp);


    }


}

/**
 * JsonIgnoreProperties注解，可以忽略掉json字符串中业务不需要的json键
 * 但是如果配置了 objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
 * 这个注解也可以忽略掉
 */
//@JsonIgnoreProperties({"Head","PicInfo","RequestId","Msg","Success","Code"})
class SearchResp {

    /**
     * JsonProperty注解在业务上的java属性上，值为与之匹配的json字符串中的json键
     **/
    @JsonProperty(value = "Auctions")
    List<Auction> actions;


    @Override
    public String toString() {
        return "SearchResp{" +
                "actions=" + actions +
                '}';
    }
}

//@JsonIgnoreProperties({"CustomContent","StrAttr"})
class Auction {
    @JsonProperty(value = "CategoryId")
    int categoryId;
    @JsonProperty(value = "PicName")
    String picName;

    @JsonProperty(value = "SortExprValues")
    String sortExprValues;

    @JsonProperty(value = "ProductId")
    String productId;

    @Override
    public String toString() {
        return "Auction{" +
                "categoryId=" + categoryId +
                ", picName='" + picName + '\'' +
                ", sortExprValues='" + sortExprValues + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
