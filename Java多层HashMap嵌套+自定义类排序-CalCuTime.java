package CalcuTimeMax;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/***
 * 计算268个地铁站点每个站点通过的最大的OD时间t，输出：地铁站名 O名 D名 时间t 
 * @author Cyf
 *
 */
public class CalCuTime {
	
	/**
	 * 获得终点和起点的时间差，单位分钟
	 * @param start
	 * @param end
	 * @return
	 */
    public static int getMinute(String start,String end)
    {   
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	long diff=0;
    	double minutes=0.0; 
		try
		{
		    Date d1 = df.parse(start);
		    Date d2 = df.parse(end);
		    diff = d2.getTime() - d1.getTime();//毫秒
		    minutes = diff / (1000.0 *60.0);
		    
		}
		catch (Exception e)
		{
		}
    	return (int)minutes;
    }
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath="T:\\xxx\\8月\\tripChain_20160801.csv";//8月1日出行链读入的文件路径
		String WfilePath="T:\\xxx_R_ODTimeMean_2.csv";//8月1日出行链处理完写入的文件路径
		String line=null;
		BufferedReader br=null;
		try {
	    	  FileInputStream fis = new FileInputStream(filePath);   
	    	  InputStreamReader isr=null;
			try {
				isr = new InputStreamReader(fis, "UTF-8");
		        } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }   
	    	  br = new BufferedReader(isr);   
				
		    }catch (FileNotFoundException e) {
			e.printStackTrace();
	    }
		//时间记录
		String start=null;
		String end=null;
		int Time;
		String R=null;//换乘站记录
		String O=null;
		String D=null;
		String ODKey=null;
		//每个站点R对应一个HashMap，这个HashMap里面分别对应不同的ODKey的ODList集合,每一条内容都是O,D,t
		HashMap<String,HashMap<String,ArrayList<ODList>>>RList=new HashMap<String,HashMap<String,ArrayList<ODList>>>();
        try {		
			while ((line = br.readLine()) != null) {	
				String[] item = line.split(",");
			    String type=item[1];//换乘类型
			    if(type.equals("B-R"))
			    {
			    	start=item[4];
			    	end=item[20];
			    	Time=getMinute(start,end);
			    	R=item[16];
			    	O=item[5];
			    	D=item[21];
			    	ODKey=O+"-"+D;
			    	if(RList.containsKey(R))//含有站点R
			    	{
			    		//取出keyR对应的值
			    		HashMap<String,ArrayList<ODList>> newl=new HashMap<String,ArrayList<ODList>>();
			    		newl=RList.get(R);
			    		if(newl.containsKey(ODKey))//判断键R对应的值里面是否有ODKey
			    		{
			    			ArrayList<ODList> tmp=new ArrayList<ODList>();
			    			tmp=newl.get(ODKey);//取出对应List
			    			ODList it=new ODList(O,D,Time);
				    		tmp.add(it);//加入新的元素
				    		newl.put(ODKey, tmp);//放回
			    		}
			    		else
			    		{   
			    			ArrayList<ODList> tmp=new ArrayList<ODList>();
				    		ODList it=new ODList(O,D,Time);
				    		tmp.add(it);
			    			newl.put(ODKey, tmp);
			    		}
			    		RList.put(R, newl);//更新
			    	}
			    	else
			    	{
			    		HashMap<String,ArrayList<ODList>> newl=new HashMap<String,ArrayList<ODList>>();
			    		ArrayList<ODList> tmp=new ArrayList<ODList>();
			    		ODList it=new ODList(O,D,Time);
			    		tmp.add(it);
			    		newl.put(ODKey, tmp);
			    		RList.put(R, newl);
			    		
			    	}
			    	
			    }
			    if(type.equals("R-B"))
			    {
			    	start=item[4];
			    	end=item[20];
			    	Time=getMinute(start,end);
			    	R=item[10];
			    	O=item[5];
			    	D=item[21];
			    	ODKey=O+"-"+D;
			    	if(RList.containsKey(R))//含有站点R
			    	{
			    		//取出keyR对应的值
			    		HashMap<String,ArrayList<ODList>> newl=new HashMap<String,ArrayList<ODList>>();
			    		newl=RList.get(R);
			    		if(newl.containsKey(ODKey))//判断键R对应的值里面是否有ODKey
			    		{
			    			ArrayList<ODList> tmp=new ArrayList<ODList>();
			    			tmp=newl.get(ODKey);//取出对应List
			    			ODList it=new ODList(O,D,Time);
				    		tmp.add(it);//加入新的元素
				    		newl.put(ODKey, tmp);//放回
			    		}
			    		else
			    		{   
			    			ArrayList<ODList> tmp=new ArrayList<ODList>();
				    		ODList it=new ODList(O,D,Time);
				    		tmp.add(it);
			    			newl.put(ODKey, tmp);
			    		}
			    		RList.put(R, newl);//更新
			    	}
			    	else
			    	{
			    		HashMap<String,ArrayList<ODList>> newl=new HashMap<String,ArrayList<ODList>>();
			    		ArrayList<ODList> tmp=new ArrayList<ODList>();
			    		ODList it=new ODList(O,D,Time);
			    		tmp.add(it);
			    		newl.put(ODKey, tmp);
			    		RList.put(R, newl);
			    		
			    	}
			    	
			    }
			    
			}//while结束
			//处理RList,要对里面每个站点的HashMap<String,ODlist>的每个OD-String对应的ODList的数量排序，选出最大的那个HashMap值，然后对该值的ODList元素再取中位数时间作为该R的O,D,T
			Map<String,HashMap<String,ArrayList<ODList>>> map=RList;
			Iterator<?> iter=map.entrySet().iterator();
			int max=-10000;//定义比较变量
			String key=null;//记录每个R最大的O-DKey
			String []keylist=new String[100000000];//记录下R对应的最大的O-Dkey的顺序
			int p=0;
			while(iter.hasNext())
			{   
				max=-10000;
				Map.Entry<String,HashMap<String,ArrayList<ODList>>> entry=(Map.Entry)iter.next();
				String r=entry.getKey();//取出站点R
				HashMap<String,ArrayList<ODList>> t=new HashMap<String,ArrayList<ODList>>();
               	t=entry.getValue();//取出R对应的 HashMap,找出它里面的最大ODList
               	//采用同样的方法对取得的值HashMap<String,ArrayList<ODList>进行遍历找出当前R对应的最大的O-D
               	Map<String,ArrayList<ODList>> mm=t;
               	Iterator<?> itr=mm.entrySet().iterator();
				while(itr.hasNext())
				{
					Map.Entry<String,ArrayList<ODList>> en=(Map.Entry)itr.next();
					String re=en.getKey();//O-D
					int num=en.getValue().size();//该O-D对应的ODList的元素的数量
					if(num>max)//找出当前R下对应的最大的O-D
					{
						max=num;
						key=re;
					}
					
				}
				keylist[p++]=key;
			}
			//计算RList中每个R的最大的那个O-DKey对应的List列表里面的时间的中位均值，先排序，然后计算从第前10%开始到第90%的数据的t的平均值,同时写入文件
			int q=0;//keylist顺序变量
			int sum=0;
			int w=0;
			try{
					BufferedWriter bw;
					bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(WfilePath), "UTF-8"));//用这种格式
					bw.write((char)(65279)+"地铁站名"+","+"上车点-下车点"+","+"时间t(min)"+"\r\n");
					Map<String,HashMap<String,ArrayList<ODList>>> Remap=RList;
					Iterator<?> Reiter=map.entrySet().iterator();
					while(Reiter.hasNext())//处理
					{  
						Map.Entry<String,HashMap<String,ArrayList<ODList>>> entry=(Map.Entry)Reiter.next();
						String r=entry.getKey();//取出站点R
						HashMap<String,ArrayList<ODList>> t=new HashMap<String,ArrayList<ODList>>();
		               	t=entry.getValue();//取出R对应的 HashMap
		               	ArrayList<ODList> ta=new ArrayList<ODList>();
		               	ta=t.get(keylist[q]);//取出对应的数量最大的ODList
		                Collections.sort(ta, new Comparator<Object>() { //排序
		                    @Override
		                    public int compare(Object o1, Object o2) {
		                    	ODList s1=(ODList)o1;
		                    	ODList s2=(ODList)o2;
		                    	 if (s1.getT() > s2.getT())
		                           return 1;
		                        return -1;
		                    }
		                  });
		                //遍历ta,解出t的均值
		                w=ta.size();
		                int left=(int)(w*0.25);
		                int right=(int)(w*0.75)+1;
		                for(int a=left; a<right; a++)
		                {
		                	sum+=ta.get(a).getT();
		                }
		                sum=sum/(right-left);
		                System.out.println((right-left)+","+w+","+left+","+right);
						bw.write(r+","+keylist[q]+","+sum+"\r\n");
						q++;
					}
					
					bw.flush();
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}   
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
