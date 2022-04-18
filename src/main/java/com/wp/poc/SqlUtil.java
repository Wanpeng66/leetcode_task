package com.wp.poc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

/**
 * @author: wp
 * @Title: SqlUtil
 * @Description:
 * @date 2022/4/14 13:17
 */
public class SqlUtil {
    public static int pageSize = 200;
    public static int maxTry = 100;
    public static long sleepTime = 100;
    public static String urlPrefix = "http://192.168.4.180:9047";
    public static RestTemplate restTemplate = new RestTemplate();

    public static JSONObject executeSql(String username, String password, String sql, String[] context) throws Exception {
        //获取token
        String token = getToken( username, password );
        //执行sql
        String jobId = doSql(token, sql, context);
        //等待job执行完毕
        int status = isDone( jobId, token );
        JSONObject res = new JSONObject();
        if (status==-1) {
            res.put( "status", "sql执行被取消" );
        } else if (status==-2) {
            res.put( "status","sql执行失败" );
        }else if(status==1){
            JSONObject data = new JSONObject();
            data.put( "data",new JSONArray() );
            //获取sql执行结果
            getData(jobId, token, data, 0);
            res.put( "status","sql执行成功" );
            res.put( "data",data );
            res.put( "total",data.getJSONArray( "data" ).size() );
        }
        return  res;
    }

    private static void getData( String jobId, String token, JSONObject data, int offset ) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.APPLICATION_JSON;
        headers.setContentType(type);
        headers.add( "Authorization","_daas".concat( token ) );
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange( urlPrefix + ("/api/v3/job/{id}/results?offset={offset}&limit="+pageSize).replace( "{id}", jobId ).replace( "{offset}", offset+"" ), HttpMethod.GET, httpEntity, JSONObject.class );
        if (null!=responseEntity.getBody()) {
            JSONObject body = responseEntity.getBody();
            JSONArray rows = body.getJSONArray( "rows" );
            data.getJSONArray( "data" ).addAll( rows );
            if (rows.size()<pageSize) {
                data.put( "schema", body.getJSONArray( "schema" ) );
                return ;
            }
            getData( jobId, token, data, offset+=pageSize );
        }
    }

    private static int isDone( String jobId, String token ) throws Exception {
        int status = 0;
        int tryNum = 0;
        while (tryNum<=maxTry) {
            status = getStatus( jobId, token );
            if (status!=0) {
                break;
            }
            tryNum++;
            Thread.sleep( sleepTime );
        }
        if (status == 0) {
            throw new Exception("获取job状态失败");
        }
        return  status;
    }

    private static  int  getStatus( String jobId, String token ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add( "Authorization","_daas".concat( token ) );
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange( urlPrefix + "/apiv2/job/{id}/details".replace( "{id}", jobId ), HttpMethod.GET, httpEntity, JSONObject.class );
        if (null!=responseEntity.getBody()) {
            String state = responseEntity.getBody().getString( "state" );
            if (StringUtils.hasText( state ) ) {
                if (state.equalsIgnoreCase( "RUNNING" )) {
                    return 0;
                } else if (state.equalsIgnoreCase( "COMPLETED" )) {
                    return 1;
                }else if(state.equalsIgnoreCase( "CANCELED" )){
                    return -1;
                } else if (state.equalsIgnoreCase( "FAILED" )) {
                    return -2;
                }
            }
        }
        return 0;
    }

    private static String doSql( String token, String sql, String[] context ) throws Exception {
        try{
            JSONObject param = new JSONObject();
            param.put( "sql", sql );
            param.put( "context",context );
            HttpHeaders headers = new HttpHeaders();
            headers.add( "authorization","_daas".concat( token ) );
            MediaType type = MediaType.APPLICATION_JSON;
            headers.setContentType(type);
            HttpEntity<String> httpEntity = new HttpEntity<>(param.toJSONString(), headers);
            ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity( urlPrefix + "/api/v3/sql", httpEntity, JSONObject.class );
            if (null!=responseEntity.getBody()) {
                String id = responseEntity.getBody().getString( "id" );
                if (StringUtils.hasText( id )) {
                    return id;
                }
            }
            throw new Exception("获取jobId失败");
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("获取jobId失败");
        }
    }

    private static String getToken( String username, String password ) throws Exception {
        try{
            JSONObject param = new JSONObject();
            param.put( "userName", username );
            param.put( "password", Base64.getEncoder().encodeToString( password.getBytes() ) );
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.APPLICATION_JSON;
            headers.setContentType(type);
            HttpEntity<String> httpEntity = new HttpEntity<>(param.toJSONString(), headers);
            ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity( urlPrefix + "/apiv2/login", httpEntity, JSONObject.class );
            if (null != responseEntity.getBody()) {
                JSONObject body = responseEntity.getBody();
                String token = body.getString( "token" );
                if (StringUtils.hasText( token )) {
                    return token;
                }
            }
            throw new Exception("token获取失败");
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("token获取失败");
        }
    }

    public static void main( String[] args ) throws Exception {
        String sql = "select * from channel_dim limit 200";
        String username = "spd";
        String password = "spd@123456";
        JSONObject data = executeSql( username, password, sql, new String[]{"poc"} );
        System.out.println( data.toJSONString() );
    }
}
