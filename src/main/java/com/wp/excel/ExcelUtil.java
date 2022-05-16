package com.wp.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelReader;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: wp
 * @Title: ExcelUtil
 * @Description:
 * @date 2022/4/20 14:01
 */
public class ExcelUtil {
    public static void main( String[] args ) throws IOException {
        extractYqsb();
    }
    public static void extractJcbz(){
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\检测标准信息.xlsx" );
        List<Map<String, Object>> maps = excelReader.readAll();
        Set<String> set = new HashSet<>();
        Iterator<Map<String, Object>> iterator = maps.iterator();
        big:while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            String id = "";
            Set<String> keySet = map.keySet();
            Iterator<String> keyIter = keySet.iterator();
            while (keyIter.hasNext()) {
                String key = keyIter.next();
                if (key.equals( "机构名称" )) {
                    String name = (String) map.get( key );
                    id = Base64.getEncoder().encodeToString( name.getBytes() );
                    if (set.contains( name )) {
                        iterator.remove();
                        continue big;
                    }

                    set.add( name );
                }
                if (!(key.equals( "机构地址" ) || key.equals( "机构简介" ) || key.equals( "机构名称" ) || key.equals( "机构联系方式" ))) {
                    keyIter.remove();
                }
            }
            map.put( "id",id );
        }
        iterator = maps.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            map.put( "jgdz",map.get( "机构地址" ) );
            map.remove( "机构地址" );
            map.put( "jgjj",map.get( "机构简介" ) );
            map.remove( "机构简介" );
            map.put( "jgmc",map.get( "机构名称" ) );
            map.remove( "机构名称" );
            map.put( "jglxfs",map.get( "机构联系方式" ) );
            map.remove( "机构联系方式" );
        }
        File file = FileUtil.newFile( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\jcjg.json" );
        FileUtil.appendString( JSONArray.toJSONString( maps ), file, "utf-8" );
    }
    public static void extractJcjg(){
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\检测服务与检测机构.xlsx" );
        List<Map<String, Object>> maps = excelReader.readAll();
        Set<String> set = new HashSet<>();
        Iterator<Map<String, Object>> iterator = maps.iterator();
        big:while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            String id = "";
            Set<String> keySet = map.keySet();
            Iterator<String> keyIter = keySet.iterator();
            while (keyIter.hasNext()) {
                String key = keyIter.next();
                if (key.equals( "机构名称" )) {
                    String name = (String) map.get( key );
                    id = Base64.getEncoder().encodeToString( name.getBytes() );
                    if (set.contains( name )) {
                        iterator.remove();
                        continue big;
                    }

                    set.add( name );
                }
                if (!(key.equals( "机构地址" ) || key.equals( "机构简介" ) || key.equals( "机构名称" ) || key.equals( "机构联系方式" ))) {
                    keyIter.remove();
                }
            }
            map.put( "id",id );
        }
        iterator = maps.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            map.put( "jgdz",map.get( "机构地址" ) );
            map.remove( "机构地址" );
            map.put( "jgjj",map.get( "机构简介" ) );
            map.remove( "机构简介" );
            map.put( "jgmc",map.get( "机构名称" ) );
            map.remove( "机构名称" );
            map.put( "jglxfs",map.get( "机构联系方式" ) );
            map.remove( "机构联系方式" );
        }
        File file = FileUtil.newFile( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\jcjg.json" );
        FileUtil.appendString( JSONArray.toJSONString( maps ), file, "utf-8" );
    }

    public static void extractYqsb(){
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\仪器设备.xlsx" );
        List<Map<String, Object>> maps = excelReader.readAll();
        for (int i = 0; i < maps.size(); i++) {
            Map<String, Object> map = maps.get( i );
            for (String key : map.keySet()) {
                Object val = map.get( key );
                if (val instanceof String) {
                    val = ( (String) val ).trim();
                }
                map.put( key, val );
            }
            String cjdz = (String) map.get( "cjdz" );
            JSONObject geo = null;
            try {
                geo = getGeo( cjdz );
            } catch (Exception e) {
                continue;
            }
            map.put( "sbje", i );
            map.put( "cjjwd",geo.getDoubleValue( "lat" )+","+geo.getDoubleValue( "lng" ) );
            map.put( "id", map.get( "sbmc" ).toString().concat( "(" ).concat( map.get( "xhgg" ).toString() ).concat( ")" ));
        }
        File file = FileUtil.newFile( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\yqsb.json" );
        FileUtil.appendString( JSONArray.toJSONString( maps ), file, "utf-8" );
    }

    public static void geoForJcjg() throws IOException {
        String path = "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\jcjg.json";
        String str = FileUtil.readString( path, "utf-8" );
        JSONArray datas = JSONArray.parseArray( str );
        for (int i = 0; i < datas.size(); i++) {
            JSONObject data = datas.getJSONObject( i );
            String jgdz = data.getString( "jgdz" );
            JSONObject geo = null;
            try {
                geo = getGeo(jgdz);
            } catch (Exception e) {
                continue;
            }
            data.put( "jgjwd",geo.getDoubleValue( "lat" )+","+geo.getDoubleValue( "lng" ) );
        }
        BufferedWriter writer = FileUtil.getWriter( path, "utf-8", false );
        writer.write( datas.toJSONString() );
        writer.close();
    }

    private static JSONObject getGeo( String dz ) throws Exception {
        HttpRequest request = HttpUtil.createGet( "http://api.map.baidu.com/geocoder?address=" + dz + "&output=json" );
        HttpResponse response = request.execute();
        JSONObject body = JSONObject.parseObject( response.body() );
        if (body.getString( "status" ).equalsIgnoreCase( "ok" )) {
            return body.getJSONObject( "result" ).getJSONObject( "location" );
        }
        throw new Exception("获取经纬度失败");
    }

    public static void extractJcfw(){
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\检测服务与检测机构.xlsx" );
        List<Map<String, Object>> maps = excelReader.readAll();
        for (Map<String, Object> map : maps) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                if (key.equals( "jg" )) {
                    Object val = map.get( key );
                    if (val instanceof String && ( (String) val ).startsWith( "￥" )) {
                        String[] split = ( (String) val ).split( "-" );
                        double[] array = new double[split.length];
                        for (int i = 0; i < split.length; i++) {
                            split[i] = split[i].replaceAll( "￥","" ).trim();
                            array[i] = Double.parseDouble( split[i] );
                        }
                        if (array.length==1) {
                            val = array[0];
                        }else{
                            val = array;
                        }
                    }else if(val instanceof String && !( (String) val ).startsWith( "￥" )){
                        val = null;
                    }
                    map.put( key, val );
                }
                if (key.equals( "jcbz" )) {
                    String val = (String) map.get( key );
                    String[] split = val.split( "," );
                    for (int i = 0; i < split.length; i++) {
                        split[i] = split[i].trim();
                    }
                    map.put( key, split );
                }
                if (key.equals( "jgdz" )) {
                    String jgdz = (String) map.get( key );
                    if (!StringUtils.hasText( jgdz )) {
                        continue;
                    }
                    JSONObject geo = null;
                    try {
                        geo = getGeo(jgdz);
                    } catch (Exception e) {
                        continue;
                    }
                    map.put( "jgjwd",geo.getDoubleValue( "lat" )+","+geo.getDoubleValue( "lng" ) );
                }
            }
            String fwxq = map.get( "fwxq" ).toString();
            map.put( "id", fwxq.trim().substring( 0, fwxq.indexOf( "检测" )+2 ) );
        }
        File file = FileUtil.newFile( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\jcfw.json" );
        FileUtil.appendString( JSONArray.toJSONString( maps ), file, "utf-8" );
    }
}
