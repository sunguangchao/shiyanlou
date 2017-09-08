```javascript
{header: '有效标志', dataIndex:'FLAG', width:100, sortable:true, align: 'center', renderer:function(value){
	//将从数据库中读取的有效状态0,1转化为文字的形式。
    type_data;
    if("0" == value) {
    	return "无效";
    } else if("1" == value) {
    	return "有效";
    } 
}}
```
匹配HH:ss:mm时间样式的正则表达式

```javascript
var reg = /^(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/;
```

字符串转为时间进行比较

```javascript
var end_str = ETIME.replace(/-/g,"/");
var end_data = new Date(end_str);
var sta_str = STIME.replace(/-/g,"/");
var sta_data = new Date(sta_str);
if (sta_str > end_str){
	Ext.Msg.alert('信息提示',"服务开始时间不能晚于结束时间！");
	return;
}
```

参考：[js中如何将字符串转化为时间，并计算时间差](http://blog.csdn.net/zz87250976/article/details/42742093)



遍历输出HashMap的key和value

```java
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ecplise test");
		HashMap<String, String> hsMap = new HashMap<String, String>();
		hsMap.put("1", "a");
		hsMap.put("2", "b");
		hsMap.put("3", "c");
		Iterator<Entry<String, String>> iter = hsMap.entrySet().iterator();
		while (iter.hasNext()) {
			 Map.Entry<String, String> entry =iter.next();
			 String key = entry.getKey();
			 String value = entry.getValue();
			 System.out.print("key: "+ key + " and ");
			 System.out.println("value: "+value);
			
		}
	}
}
```

