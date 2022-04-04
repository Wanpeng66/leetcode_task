package com.wp.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author: wp
 * @Title: TestHighlight
 * @Description: TODO
 * @date 2022/3/29 14:40
 */
public class TestHighlight {

    private JSONArray dealHighlight( JSONArray result) {
        JSONArray array = new JSONArray();
        Set<String> visited = new HashSet<>();
        for (Object item : result) {
            JSONObject data = (JSONObject) item;
            Map<String, Object> flattenAsMap = JsonFlattener.flattenAsMap( data.toJSONString() );
            JSONObject highlight = data.getJSONObject("highlight");
            //如果高亮有结果
            if (highlight != null) {
                JSONObject jsonObject = data.getJSONObject("highlight");
                for (String k : jsonObject.keySet()) {
                    String kw = k;
                    Object v = jsonObject.get( k );
                    if (k.contains( ".keyword" )) {
                        k = k.split( "\\.keyword" )[0];
                    }
                    Set<String> keySet = flattenAsMap.keySet();
                    for (String key : keySet) {
                        boolean flag = false;
                        if (key.replaceAll( "\\[", "" ).replaceAll( "]","" ).replaceAll( "\\d+","" ).equals( k )) {
                            Object o = flattenAsMap.get( key );
                            if (Objects.nonNull( o )) {
                                List<String> hl =  (List<String>) v;
                                //先判斷原值是否是數組 依据：高亮片段包含全部内容
                                for (String s : hl) {
                                    if (s.replaceAll( "<em>","" ).replaceAll( "</em>","" ).trim().contains( o.toString().trim() )) {
                                        JSONObject wildcard = new JSONObject();
                                        if (wildcard.containsKey( kw )) {
                                            JSONObject wildcardJSONObject = wildcard.getJSONObject( kw );
                                            String termValue = wildcardJSONObject.getString( "term" );
                                            if (!( StringUtils.hasText( termValue ) && termValue.equalsIgnoreCase( s))) {
                                                String wildcardValue = wildcardJSONObject.getString( "wildcard" );
                                                if (StringUtils.hasText( wildcardValue )&&s.contains( wildcardValue )) {
                                                    if (!visited.contains( kw )) {

                                                    }
                                                    s = s.replaceAll( "<em>","" ).replaceAll( "</em>","" );
                                                    s = highlightString(s,wildcardValue);
                                                    visited.add( kw );
                                                }
                                            }
                                        }
                                        if (flattenAsMap.containsKey(key)) {
                                            flattenAsMap.put(key,s );
                                        }
                                        flag = true;

                                        break;
                                    }
                                }
                                if (flag) {
                                    continue;
                                }
                                //原值为单值
                                String value = StringUtils.collectionToDelimitedString( ( (List<String>) v ), "" ).trim();
                                JSONObject wildcard = new JSONObject();
                                if (wildcard.containsKey( kw )) {
                                    JSONObject wildcardJSONObject = wildcard.getJSONObject( kw );
                                    String termValue = wildcardJSONObject.getString( "term" );
                                    if (!(StringUtils.hasText( termValue ) && termValue.equalsIgnoreCase( value))) {
                                        String wildcardValue = wildcardJSONObject.getString( "wildcard" );
                                        if (StringUtils.hasText( wildcardValue )&&value.contains( wildcardValue )) {
                                            value = value.replaceAll( "<em>","" ).replaceAll( "</em>","" ).trim();
                                            value = highlightString(value,wildcardValue);
                                            visited.add( kw );
                                        }
                                    }
                                }
                                if (flattenAsMap.containsKey(key)) {
                                    if (value.replaceAll( "<em>","" ).replaceAll( "</em>","" ).trim().contains( flattenAsMap.get( key ).toString().trim() )) {
                                        flattenAsMap.put(key,value );
                                    }
                                }
                            }

                        }
                    }

                };
                data = JSON.parseObject( JsonUnflattener.unflatten(JSON.toJSONString( flattenAsMap )) );
                data.remove("highlight");
            }
            array.add( data );
        }
        return array;
    }

    private String highlightString( String s, String wildcardValue ) {
        return s.replaceAll( wildcardValue,"<em>"+wildcardValue+"</em>" );
    }
}
