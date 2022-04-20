package com.wp.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

/**
 * @author: wp
 * @Title: ExcelUtil
 * @Description:
 * @date 2022/4/20 14:01
 */
public class ExcelUtil {
    public static void main( String[] args ) {
        extractJcfw();
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

    public static void extractJcfw(){
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\检测服务与检测机构.xlsx" );
        List<Map<String, Object>> maps = excelReader.readAll();
        for (Map<String, Object> map : maps) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                if (key.equals( "jg" )) {
                    Object val = map.get( key );
                    if (val instanceof String) {
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
            }
            map.put( "id",map.get( "fwxq" ).toString().trim().split( " " )[0] );
        }
        File file = FileUtil.newFile( "C:\\Users\\wp\\Desktop\\质量检测场景数据分享测试-2022.04.20\\jcfw.json" );
        FileUtil.appendString( JSONArray.toJSONString( maps ), file, "utf-8" );
    }
}
