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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: wp
 * @Title: ExcelUtil
 * @Description:
 * @date 2022/4/20 14:01
 */
public class ExcelUtil {
    public static void main( String[] args ) throws IOException, InterruptedException {
        /*ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\plantdata-nb-0001\\Desktop\\银行poc\\码表\\新建文件夹\\plbs_code_library.xlsx" );
        List<Map<String, Object>> maps = excelReader.readAll();
        maps.remove( 0 );
        JSONObject data = new JSONObject();
        for (Map<String, Object> map : maps) {
            Object value = map.get( "描述信息" );
            String field = map.get( "字段名" ).toString().toLowerCase( Locale.ROOT );
            data.put( field, Objects.isNull( value )?field:value.toString().contains( "(" )?value.toString().split( "\\(" )[0]:value.toString() );
        }
        System.out.println( data.toJSONString() );*/
        String str = "{\"GB 4706.45-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH56ANXtfAA0t7QpTtd4044.pdf\",\"GB 4706.32-2012\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIROAB7nGAQ1lBu9nRvE505.pdf\",\"GB 25957-2010\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5uAWvlfAATCUtm6ksA191.pdf\",\"GB 13837-2012\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIKCAJ78cACp3EFbODc0475.pdf\",\"GB 4943.1-2011\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIQ-Ad3E_ALWx07pwjRw386.pdf\",\"GB 4706.59-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5-AbE6-AA4-dBtOTZM897.pdf\",\"GB 7000.213-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH6qAYuF5AAe_RsS_olE868.pdf\",\"GB 12021.6-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6CAfSbEAAxZvIMkB2s147.pdf\",\"GB 4706.28-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5OAJUOJAAiDrFPg0hY439.pdf\",\"GB 4706.76-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5uADQazAAsc0zOKd_o848.pdf\",\"GB 4706.23-2007\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH6uAcfxSABl657iJEAQ156.pdf\",\"GB 4706.15-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH6uAB2kCAA-MmAuaLBU865.pdf\",\"GB 12021.2-2015\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIQ6AUawJAKRLopuh1Do924.pdf\",\"GB 4706.22-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIL6AYz_MACBZQR09hPM480.pdf\",\"GB 4706.30-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwILeAdvFyAB3USGdAQUc290.pdf\",\"GB 4706.29-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIOSAKvc_AK6yPV_sQiE376.pdf\",\"GB 4706.9-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6iAT8ItABH9u8Lv0_s510.pdf\",\"GB 4706.14-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6aAAwaxABENrK3pNog304.pdf\",\"GB 4706.37-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwINuAOXbsAGByeNjz5qU161.pdf\",\"GB 4706.53-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH6uAVkEeABh3jXpaMd4898.pdf\",\"GB 24850-2013\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5uAKYCrAAgZXVR9T2I212.PDF\",\"GB 4706.66-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwINiATW2yAFRXUW4al1o150.pdf\",\"GB 19510.1-2009\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIMqAUSVxACNJt8m4p84664.pdf\",\"GB 7000.212-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5aANNFJAAZY9hn8cJk834.pdf\",\"GB 17625.2-2007\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6mAYCpsABDdmDtYG8o992.pdf\",\"GB 21519-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIJyABPm2ABGleXCN9Ss219.pdf\",\"GB 21456-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH52Ac66yAAKlvLKRaIo009.pdf\",\"GB 4343.1-2009\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIK2AZSTQAFoq7gpqeKg990.pdf\",\"GB 4706.55-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIMmAcf5_ACccVeOhSQo552.pdf\",\"GB 7000.201-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5-ALfhMAAeJeAPNHyQ021.pdf\",\"GB 4706.52-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6iABjdlABEuvnlbK7A290.pdf\",\"GB 19510.4-2009\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6OAcMqVAA_kX32wWzs139.pdf\",\"GB 7000.1-2015\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIRGAQ_MlAN-cv4mQE3Y125.pdf\",\"GB 19510.10-2009\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH66ATmC8AAq0zQ1m36k234.pdf\",\"GB 4706.12-2006\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwILyAMnWIAC2oJylLuow970.pdf\",\"GB 21456-2014\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5CAHczMAAKGtfsTUeI944.pdf\",\"GB 19510.13-2007\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH52AWAgnAAe7gjHeDl8506.pdf\",\"GB 7000.204-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6iARG6lAAiMGrq2Vn4064.pdf\",\"GBT 14472-1998\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6GAWTIDABE2naCq-TI931.pdf\",\"GB 17988-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwILmAGiOqACGQuoOoEz0090.pdf\",\"GB 4706.18-2014\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwINeAcV7JAFadk5phnI4177.pdf\",\"GB 12021.9-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5iAcyfpAAnmQpf28Ew649.pdf\",\"GB 16915.1-2014\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIN2ASpiWAGnHzkwFumE060.pdf\",\"GB 4706.48-2009\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6WANfimAA-C2mQjJnE968.pdf\",\"GB 8898-2011\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIOKAIntjAHDAU2-I8sU552.PDF\",\"GB 19212.18-2006\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH56Aemh7AAXKYk7WmJs251.pdf\",\"GB 4706.1-2005\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIKuAQ9sfAELdqr4VLsk672.pdf\",\"GB 17625.1-2012\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwINaAGVZTADiO-8v-3Y8145.pdf\",\"GB 4706.20-2004\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH52AH8udAAcUuDS9Gik213.pdf\",\"GB 4706.73-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6mAaYsMAA7Vj9qkSsg534.pdf\",\"GB 7000.4-2007\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5uAMabeAAWQ4OGswJk716.pdf\",\"GBT 19484.1-2013\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIMKATza7AB71kxdan8c799.pdf\",\"GB 4706.2-2007\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH6yAWALgAAsVyc3PAO4042.pdf\",\"GB 4706.33-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwINiAI06hAHmIN-pUIww485.pdf\",\"GB 19510.9-2009\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH56AP1q5AApGCSKUv4k537.pdf\",\"GB 12021.3-2010\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH5iAF261AAIWyehqnx4672.pdf\",\"GB 4706.26-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH6yAGQQiABqP6si2kqc795.pdf\",\"GB 4706.25-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIMyAbRDoACq4TJBYRoI715.pdf\",\"GB 19510.14-2009\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6OAefPUABPDyrPF2hs575.pdf\",\"GB 4706.13-2014\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIMqASmkBADzqsSDxlvE686.pdf\",\"GB 21520-2015\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6GAQ3F0AAvy_B1mmgQ031.pdf\",\"GB 30978-2014\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH56AK-0JAAuMwLcjD6M088.pdf\",\"GB 7000.202-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6mAZ3i4AAlYKnQcaig427.pdf\",\"GB 4706.11-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH7KAA6FUAAvfddOG02s090.pdf\",\"GB 4706.24-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIMmACL1rAC6IXPKwXJ8071.pdf\",\"GB 4706.27-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH56AFhGSAApd-5MbGiY016.pdf\",\"GB 4706.10-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIK6AdvvwAFT4ql12_A0065.pdf\",\"GB 19212.1-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIN-AJGx2AKSPs5SUqSA666.pdf\",\"GB 4706.21-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6SAeuxBAA4vYuFwoh8722.pdf\",\"GB 4706.13-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwILqAU-DXACj22A5SX_0531.pdf\",\"GB4706.132008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIMSAdk3EACjt9v-T1WU486.pdf\",\"GB 4706.36-2014\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6OAYt-MABO5_bAScbc001.pdf\",\"GB 4706.49-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH56AElogAAsXZxO9gxE331.pdf\",\"GB 4706.17-2010\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwINaAc0n5ADeF0XXGO98471.pdf\",\"GB 4706.7-2014\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIL6AJ0pyAB9MLDymPb0314.pdf\",\"GB 16915.2-2012\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIMSAX6AeACKTSnOM8Tk200.pdf\",\"GB 9254-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIM6AFT4hAEXUyvigYcE063.pdf\",\"GB 19212.7-2012\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIMeAA-leACBLkzpwAC0922.pdf\",\"GB 7000.211-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH7GAalUqAAXrCyKxtAg327.pdf\",\"GB 4343.2-2009\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwH62AfC80ACCuhJImJQE031.pdf\",\"GB 4706.19-2008\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/01/rKjQjGKwH6SANX3CAA21oz87kZk635.pdf\",\"GB 12476.1-2013\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwIKuATcRfAD1RpFZbZNw365.pdf\",\"GB 17743-2007\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwINuAZ_oDADq37OD0mgM921.pdf\",\"GB 4706.1-1998\":\"http://product410-d5yjs.kg.plantdata.cn/group1/M00/00/02/rKjQjGKwINaACTCuAEFTmFz685s473.pdf\"}";
        JSONObject urls = JSONObject.parseObject( str );
        String json = "{\"query\":{\"term\":{\"bzdh.keyword\":\"${bzdh}\"}},\"script\":{\"source\":\"ctx._source.bzurl='${bzurl}'\"}}";
        for (String key : urls.keySet()) {
            HttpRequest post = HttpUtil.createPost( "http://192.169.4.200:33213/index1650508600772/_update_by_query" );
            String tmp = json.replace( "${bzdh}", key ).replace( "${bzurl}", urls.getString( key ) );
            post.body( tmp );
            post.contentType( "application/json" );
            System.out.println( post.execute().body() );
        }
    }
    public static void extractJcbz(){
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\plantdata-nb-0001\\Desktop\\产品质量检测全部数据（2022.06.15）\\检测标准信息.xlsx" );
        List<Map<String, Object>> maps = excelReader.readAll();

        File file = FileUtil.newFile( "C:\\Users\\plantdata-nb-0001\\Desktop\\产品质量检测全部数据（2022.06.15）\\jcjg.json" );
        FileUtil.appendString( JSONArray.toJSONString( maps ), file, "utf-8" );
    }
    public static void extractJcjg(){
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\plantdata-nb-0001\\Desktop\\产品质量检测全部数据（2022.06.15）\\检测服务+检测机构.xlsx" );
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
            String jgdz = (String) map.get( "jgdz" );
            if (StringUtils.hasText( jgdz )) {
                try {
                    JSONObject geo = getGeo(jgdz);
                    map.put( "jgjwd",geo.getDoubleValue( "lat" )+","+geo.getDoubleValue( "lng" ) );
                } catch (Exception e) {
                }
            }
            map.put( "jgjj",map.get( "机构简介" ) );
            map.remove( "机构简介" );
            map.put( "jgmc",map.get( "机构名称" ) );
            map.remove( "机构名称" );
            map.put( "jglxfs",map.get( "机构联系方式" ) );
            map.remove( "机构联系方式" );
            map.put( "jcjg_ext",map.get( "jgmc" )+" "+map.get( "jgdz" )+" "+map.get( "jgjj" )  );
        }
        File file = FileUtil.newFile( "C:\\Users\\plantdata-nb-0001\\Desktop\\产品质量检测全部数据（2022.06.15）\\jcjg.json" );
        FileUtil.appendString( JSONArray.toJSONString( maps ), file, "utf-8" );
    }

    public static void extractYqsb(){
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\plantdata-nb-0001\\Desktop\\产品质量检测全部数据（2022.06.15）\\仪器设备.xlsx" );
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
                map.put( "cjjwd",geo.getDoubleValue( "lat" )+","+geo.getDoubleValue( "lng" ) );
            } catch (Exception e) {
            }

            map.put( "sbje", i );
            map.put( "id", map.get( "sbmc" ).toString().concat( "(" ).concat( map.get( "xhgg" ).toString() ).concat( ")" ));
            map.put( "yqsb_ext",map.get( "id" )+" "+map.get( "sccj" )+" "+map.get( "cjdz" ) );
            Object str = map.get( "sblx" );
            if (Objects.nonNull( str )) {
                String sblx = str.toString().trim();
                if (sblx.equals( "电子测量仪器" )) {
                    map.put( "sbtp",new String[]{"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphotocdn.sohu.com%2F20160311%2Fmp62962222_1457659592990_4.jpeg&refer=http%3A%2F%2Fphotocdn.sohu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654945170&t=ed490bc6d6bc3652293474272d3b8180"} );
                } else if (sblx.equals( "分析仪器" )) {
                    map.put( "sbtp",new String[]{"https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/8326cffc1e178a8213b30754fd03738da877e8b3.jpg"} );
                }else if (sblx.equals( "工艺实验设备" )) {
                    map.put( "sbtp",new String[]{"https://img0.baidu.com/it/u=3458006271,1077554184&fm=253&fmt=auto&app=138&f=JPEG?w=667&h=500"} );
                }else if (sblx.equals( "计量仪器" )) {
                    map.put( "sbtp",new String[]{"https://img2.baidu.com/it/u=1693993871,3434035234&fm=253&fmt=auto&app=138&f=JPEG?w=558&h=337"} );
                }else if (sblx.equals( "其他仪器" )) {
                    map.put( "sbtp",new String[]{"https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00267-501.jpg"} );
                }else if (sblx.equals( "特种检测仪器" )) {
                    map.put( "sbtp",new String[]{"https://img1.baidu.com/it/u=35998043,2138424718&fm=253&fmt=auto&app=138&f=JPEG?w=505&h=500"} );
                }else if (sblx.equals( "物理性能测试仪器" )) {
                    map.put( "sbtp",new String[]{"https://img1.baidu.com/it/u=4204568660,1293261328&fm=253&fmt=auto&app=138&f=JPEG?w=667&h=500"} );
                }
            }
        }
        File file = FileUtil.newFile( "C:\\Users\\plantdata-nb-0001\\Desktop\\产品质量检测全部数据（2022.06.15）\\yqsb.json" );
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
        System.out.println( body.toJSONString() );
        throw new Exception("获取经纬度失败");
    }

    public static void extractJcfw(){
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader( "C:\\Users\\plantdata-nb-0001\\Desktop\\产品质量检测全部数据（2022.06.15）\\检测服务+检测机构.xlsx" );
        List<Map<String, Object>> maps = excelReader.readAll();
        for (Map<String, Object> map : maps) {
            Set<String> keySet = map.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
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
            }
            String jgdz = (String) map.get( "jgdz" );
            if (StringUtils.hasText( jgdz )) {
                try {
                    JSONObject geo = getGeo(jgdz);
                    map.put( "jgjwd",geo.getDoubleValue( "lat" )+","+geo.getDoubleValue( "lng" ) );
                } catch (Exception e) {
                }
            }
            map.put( "id", map.get( "cp" )+" "+map.get( "jcxm" )   );
            map.put( "jcfw_ext",map.get( "id" )+" "+map.get( "jgm" )+" "+map.get( "jgdz" ) );
            Object str = map.get( "hy" );
            if (Objects.nonNull( str )) {
                String hy = str.toString().trim();
                if (hy.equals( "计算机" )) {
                    map.put( "fwtp",new String[]{"https://img0.baidu.com/it/u=1200005702,54780051&fm=253&fmt=auto&app=138&f=JPEG?w=678&h=500"} );
                } else if (hy.equals( "家电" )) {
                    map.put( "fwtp",new String[]{"https://img0.baidu.com/it/u=1339301717,2465904571&fm=253&fmt=auto&app=120&f=JPEG?w=750&h=500"} );
                }else if (hy.equals( "汽车" )) {
                    map.put( "fwtp",new String[]{"https://img0.baidu.com/it/u=1247285783,3786043839&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500"} );
                }else if (hy.equals( "通信设备" )) {
                    map.put( "fwtp",new String[]{"https://img0.baidu.com/it/u=3420248053,2384874113&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=325"} );
                }else if (hy.equals( "消费电子" )) {
                    map.put( "fwtp",new String[]{"https://img0.baidu.com/it/u=2130984148,1804678107&fm=253&fmt=auto?w=750&h=500"} );
                }else if (hy.equals( "元器件" )) {
                    map.put( "fwtp",new String[]{"https://img0.baidu.com/it/u=2023301705,2623210334&fm=253&fmt=auto&app=138&f=JPEG?w=344&h=214"} );
                }
            }
        }
        File file = FileUtil.newFile( "C:\\Users\\plantdata-nb-0001\\Desktop\\产品质量检测全部数据（2022.06.15）\\jcfw.json" );
        FileUtil.appendString( JSONArray.toJSONString( maps ), file, "utf-8" );
    }
}
