package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONObject;
import utils.GlobalParams;
import utils.HttpUtil;
import utils.MD5Util;
import utils.QmJsonUtils;

public class HttpRequest {

	public static void main(String[] args) {
//		findError();
//		findDriverAround();
//		calculateCarDistance("116.481028","39.989643","114.465302","40.004717");
		calculateCarDistance("114.244991","22.714724","114.244915","22.715006");
	}
	
	/**
	 * 原本代码备份
	 */
	public static void mainTestBackUp() {
		//参数拼接
////	String jsonParams = "{"++"}";
//	Map<String, Object> map = new HashMap<String, Object>();
//	map.put("_name", "13450507958");
//	map.put("_location", "114.245055,22.71469");
//	map.put("_address", "广东省深圳市龙岗区德政路靠近陈翡记");
//	JSONObject jsonObject = JSONObject.fromObject(map);
////	System.out.println(jsonObject);
//	
//	String paraForSign = "data="+jsonObject.toString()+"&key="+GlobalParams.AUTONAVI_WEB_KEY+"&tableid="+GlobalParams.AUTONAVI_HTTP_TABLE_ID
//    		+GlobalParams.AUTONAVI_WEB_PRIVATE_KEY;
//	String sig = MD5Util.MD5Encode(paraForSign, "utf-8");
//	String sr = "1111";
//	//发送 POST 请求
//    sr = HttpRequest.sendPost(GlobalParams.AUTONAVI_HTTP_DATA_CREATE_URL, 
//    		"key="+GlobalParams.AUTONAVI_WEB_KEY+"&tableid="+GlobalParams.AUTONAVI_HTTP_TABLE_ID
//    		+"&data="+jsonObject.toString()+"&sig="+sig);
//    System.out.println(sr);
//    Map<String, Object> resultMap = QmJsonUtils.parseJSON2Map(sr);
//    int status = Integer.valueOf(String.valueOf(resultMap.get("status"))).intValue();
//    System.out.println("解析高德返回状态码-----"+status);
//    System.out.println("在后面打出来的就说明网络请求是同步的");
	}
	
	/**
	 * 确认了是网络求情框架在java后台项目中使用有问题
	 */
	public static void findError() {
		//待插入表数据参数拼接
  		Map<String, Object> map = new HashMap<String, Object>();
  		map.put("_name", "13450507958");
  		map.put("_location", "114.245055,22.71469");
  		map.put("_address", "广东省深圳市龙岗区德政路靠近陈翡记");
  		JSONObject jsonObject = JSONObject.fromObject(map);
  		
  		String paraForSign = "data="+jsonObject.toString()+"&key="+GlobalParams.AUTONAVI_WEB_KEY+"&tableid="+GlobalParams.AUTONAVI_HTTP_TABLE_ID
          		+GlobalParams.AUTONAVI_WEB_PRIVATE_KEY;
  		String sig = MD5Util.MD5Encode(paraForSign, "utf-8");
  		
  		//发送 POST 请求
  		String sr = HttpRequest.sendPost(GlobalParams.AUTONAVI_HTTP_DATA_CREATE_URL, 
          		"key="+GlobalParams.AUTONAVI_WEB_KEY+"&tableid="+GlobalParams.AUTONAVI_HTTP_TABLE_ID
          		+"&data="+jsonObject.toString()+"&sig="+sig);
  		System.out.println(sr);
  		Map<String, Object> resultMap = QmJsonUtils.parseJSON2Map(sr);
  		int status = Integer.valueOf(String.valueOf(resultMap.get("status"))).intValue();
  		System.out.println("解析高德返回状态码-----"+status);
  		
	}

	/**
	 * 测试高德云周边检索
	 */
	public static void findDriverAround () {
		String centerLocation = "114.245839,22.717345";
  		String paraForSign = "center="+centerLocation+"&key="+GlobalParams.AUTONAVI_WEB_KEY+"&limit=1"
  				+"&radius=5000"+"&tableid="+GlobalParams.AUTONAVI_HTTP_TABLE_ID;
  		String privateKey = GlobalParams.AUTONAVI_WEB_PRIVATE_KEY;
  		String sig = MD5Util.MD5Encode(paraForSign+privateKey, "utf-8");
  		
  		Map<String, String> paraMap = new HashMap<String, String>();
  		paraMap.put("center",centerLocation);
  		paraMap.put("key", GlobalParams.AUTONAVI_WEB_KEY);
  		paraMap.put("limit", "1");
  		paraMap.put("radius", "5000");
  		paraMap.put("sig", sig);
  		paraMap.put("tableid", GlobalParams.AUTONAVI_HTTP_TABLE_ID);
  		
  		String sr = HttpUtil.sendPost(GlobalParams.AUTONAVI_HTTP_SEARCH_AROUND_URL,paraMap);
  		Map<String, Object> resultMap = QmJsonUtils.parseJSON2Map(sr);
//  		int status = Integer.valueOf(String.valueOf(resultMap.get("status"))).intValue();
//		System.out.println(resultMap);
		@SuppressWarnings("unchecked")
		ArrayList<Map<String, Object>> listDatas = (ArrayList<Map<String, Object>>) resultMap.get("datas");
		Map<String, Object> datas = listDatas.get(0);
//		System.out.println(datas);
		String driverUserName = String.valueOf(datas.get("_name"));
		
		
		
	}
	
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //看了高的API，多加的一行，设置Content-Type
            conn.setRequestProperty("Content-Type",
    				"application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    

    /**
     * @param startLng
     * @param startLat
     * @param endLng
     * @param endLat
     * @return
     */
    public static void calculateCarDistance(String startLng,String startLat,String endLng,String endLat) {
    	String startLoc = startLng+","+startLat;
    	String endLoc = endLng+","+endLat;
    	String paraForSign = "destination="+endLoc+"&key="+GlobalParams.AUTONAVI_WEB_KEY
    			+"&origins="+startLoc;
  		String privateKey = GlobalParams.AUTONAVI_WEB_PRIVATE_KEY;
  		String sig = MD5Util.MD5Encode(paraForSign+privateKey, "utf-8");
		
  		Map<String, String> paraMap = new HashMap<String, String>();
  		paraMap.put("destination",endLoc);
  		paraMap.put("key", GlobalParams.AUTONAVI_WEB_KEY);
  		paraMap.put("origins", startLoc);
  		paraMap.put("sig", sig);
  		
  		String sr = HttpUtil.sendPost(GlobalParams.AUTONAVI_HTTP_CAR_DISTANCE_URL,paraMap);
  		Map<String, Object> resultMap = QmJsonUtils.parseJSON2Map(sr);
  		System.out.println(resultMap);
  		@SuppressWarnings("unchecked")
		ArrayList<Map<String, Object>> results = (ArrayList<Map<String, Object>>) resultMap.get("results");
		Map<String, Object> result = results.get(0);
		System.out.println(result.get("distance"));
		
	}
    
}
