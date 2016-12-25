package demos;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;


public class 数据解析测试类 {

	public static void main(String[] args) {
//		构建map转json();
//		字符串转Float();
		float与int算除法();
	}
	
	public static void 构建map转json() {
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("statuCode", "1001");
		map1.put("msg", "返回快车订单号");
		
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("orderNum", "805856659855");
		
		map1.put("data", map2);
		JSONObject jsonObject = JSONObject.fromObject(map1);
		System.out.println(jsonObject);
		
	}
	
	public static void 字符串转Float(){
		String spaceString = null;
		float aa = Float.parseFloat(spaceString);
		System.out.println(aa);
	}
	
	public static void float与int算除法() {
		String distance = "10";
		
		
	}
	
	
}
