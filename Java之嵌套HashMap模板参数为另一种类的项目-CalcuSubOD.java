package CalcuSubODByday;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import CalculateNumOfAfc.BusLinenum;
import CalculateNumOfAfc.PrintDataFormat;

/**
 * 统计每一天的每个地铁站18个小时分别对应的O，D，O-D,D-O
 * 输入：标准afc文件
 * 输出：这一天所有的站点对应的18个小时的O，O-D,D-O
 * @author cyf
 *
 */
public class CalcuSubOD {
   
	
	public static void solve(String rfile,String wfile)//一次处理一天的数据
	{   
		//建立地铁站点的HashMap
		HashMap<String,Subitem>hsO=new HashMap<String,Subitem>();
		HashMap<String,Subitem>hsD=new HashMap<String,Subitem>();
		String filePath=rfile;//存放标准的afc格式文件
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(new File(
					filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line="";
		int tmp=0;
		try {//4:上车站点D；9:下车站点O
			while((line = br.readLine()) != null){
				
				String[] item = line.split(",");
				String D=item[4];
				String O=item[9];
				if(hsD.containsKey(D))
				{   
					//++操作
					Subitem tmpsb=new Subitem();
					tmpsb=hsD.get(D);
					tmp=tmpsb.getD();
					tmp++;
					tmpsb.setD(tmp);
					hsD.put(D, tmpsb);
				}
				else
				{   
					Subitem newsb=new Subitem();
					newsb.setD(1);
					hsD.put(D, newsb);
					
				}
				if(hsO.containsKey(O))
				{  
					//++操作
					Subitem tmpsb=new Subitem();
					tmpsb=hsO.get(O);
					tmp=tmpsb.getO();
					tmp++;
					tmpsb.setO(tmp);
					hsO.put(O, tmpsb);
				}
				else
				{
					Subitem newsb=new Subitem();
					newsb.setO(1);
					hsO.put(O, newsb);
				}
				
			   }
			
			//写入数据,计算O-D,D-O
			    ArrayList<String>namelist=new ArrayList<String>();
			    Map<String,Subitem> map=hsO;
			    Iterator<?> iter=map.entrySet().iterator();
			    while(iter.hasNext())
			    {
				Map.Entry<String, Subitem> entry=(Map.Entry)iter.next();
				String name=entry.getKey();
				namelist.add(name);
			    }
			   String writefile=wfile;
			   for(int i=0;i<namelist.size();i++)
			   {
				   String tmpname=namelist.get(i);
		       try {
					BufferedWriter bw;
					br.close();
				    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writefile+tmpname+".csv"), "UTF-8"));
					bw.write((char)(65279) + "Time,O,D,O-D,D-O");
					bw.newLine();
					int Ddata=0;
					int Odata=0;
					int OD=0;
					int DO=0;
					if(hsD.containsKey(tmpname)){
						Ddata=hsD.get(tmpname).getD();
					}
					if(hsO.containsKey(tmpname)){
						Odata=hsO.get(tmpname).getO();
					}
					OD=Odata-Ddata;
					DO=Ddata-Odata;
					bw.write("day"+","+Odata+","+Ddata+","+OD+","+DO+"\r\n");
					
					
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		       
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
	}
	public static void solve2(String rfile,String wfile)//一次处理一天，间隔为1个小时
	{          
		String wystart01="0500";
		String wyend01="0600";
		String wystart02="0600";
		String wyend02="0700";
		String wystart03="0700";
		String wyend03="0800";
		String wystart04="0800";
		String wyend04="0900";
		String wystart05="0900";
		String wyend05="1000";
		String wystart06="1000";
		String wyend06="1100";
		String wystart07="1100";
		String wyend07="1200";
		String wystart08="1200";
		String wyend08="1300";
		String wystart09="1300";
		String wyend09="1400";
		String wystart10="1400";
		String wyend10="1500";
		String wystart11="1500";
		String wyend11="1600";
		String wystart12="1600";
		String wyend12="1700";
		String wystart13="1700";
		String wyend13="1800";
		String wystart14="1800";
		String wyend14="1900";
		String wystart15="1900";
		String wyend15="2000";
		String wystart16="2000";
		String wyend16="2100";
		String wystart17="2100";
		String wyend17="2200";
		String wystart18="2200";
		String wyend18="2300";
		//建立地铁站点的HashMap
				HashMap<String,Subitem>hsO=new HashMap<String,Subitem>();
				HashMap<String,Subitem>hsD=new HashMap<String,Subitem>();
				String filePath=rfile;//存放标准的afc格式文件
				BufferedReader br = null;
				try {
					 br = new BufferedReader(new FileReader(new File(
							filePath)));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				SimpleDateFormat format=null;
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String line="";
				int tmp=0;
				try {//4:上车站点D；9:下车站点O；3:D时间；8:O时间
					while((line = br.readLine()) != null){
						
						String[] item = line.split(",");
						String D=item[4];
						String O=item[9];
						String Dtime=item[3];
						String Otime=item[8];
						Dtime=Dtime.substring(8, 13);
						Otime=Otime.substring(8, 13);
						
						if(Dtime.compareTo(wystart01)>=0 && Dtime.compareTo(wyend01)<0)
					    {   
							int i=1;
							String ti="05:00:00 06:00:00";
					      
							if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();
								s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{       //建立新的映射
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart02)>=0 && Dtime.compareTo(wyend02)<0)
					    {
					    	int i=2;
					    	String ti="06:00:00 07:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart03)>=0 && Dtime.compareTo(wyend03)<0)
					    {
					    	int i=3;
					    	String ti="07:00:00 08:00:00";
						  
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem ss=new Ssubitem();ss=m.get("22:00:00 23:00:00");
								tmp=ss.getD();
								
								
								
							}
					    }
					    if(Dtime.compareTo(wystart04)>=0 && Dtime.compareTo(wyend04)<0)
					    {
					    	int i=4;
					    	String ti="08:00:00 09:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    //
					    if(Dtime.compareTo(wystart05)>=0 && Dtime.compareTo(wyend05)<0)
					    {
					    	int i=5;
					    	String ti="09:00:00 10:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart06)>=0 && Dtime.compareTo(wyend06)<0)
					    {
					    	int i=6;
					    	String ti="10:00:00 11:00:00";
					    	if(hsD.containsKey(D))
							{  
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								
								System.out.println(tmp);
								tmp++;
								s.setD(tmp);
								
								m.put(ti, s);
								tmpsb.putHashMap(m);
								
								//tmpsb.setHash(i, s);
								hsD.put(D, tmpsb);
								
							
							
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
								
							}
					    }
					    if(Dtime.compareTo(wystart07)>=0 && Dtime.compareTo(wyend07)<0)
					    {
					    	int i=7;
					    	String ti="11:00:00 12:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart08)>=0 && Dtime.compareTo(wyend08)<0)
					    {
					    	int i=8;
					    	String ti="12:00:00 13:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart09)>=0 && Dtime.compareTo(wyend09)<0)
					    {
					    	int i=9;
					    	String ti="13:00:00 14:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart10)>=0 && Dtime.compareTo(wyend10)<0)
					    {
					    	int i=10;
					    	String ti="14:00:00 15:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart11)>=0 && Dtime.compareTo(wyend11)<0)
					    {
					    	int i=11;
					    	String ti="15:00:00 16:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart12)>=0 && Dtime.compareTo(wyend12)<0)
					    {
					    	int i=12;
					    	String ti="16:00:00 17:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart13)>=0 && Dtime.compareTo(wyend13)<0)
					    {
					    	int i=13;
					    	String ti="17:00:00 18:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart14)>=0 && Dtime.compareTo(wyend14)<0)
					    {
					    	int i=14;
					    	String ti="18:00:00 19:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart15)>=0 && Dtime.compareTo(wyend15)<0)
					    {
					    	int i=15;
					    	String ti="19:00:00 20:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart16)>=0 && Dtime.compareTo(wyend16)<0)
					    {
					    	int i=16;
					    	String ti="20:00:00 21:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart17)>=0 && Dtime.compareTo(wyend17)<0)
					    {
					    	int i=17;
					    	String ti="21:00:00 22:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
					    if(Dtime.compareTo(wystart18)>=0 && Dtime.compareTo(wyend18)<0)
					    {
					    	int i=18;
					    	String ti="22:00:00 23:00:00";
					    	if(hsD.containsKey(D))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsD.get(D);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getD();
								tmp++;
								s.setD(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsD.put(D, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setD(1);
								newsb.setHash(i, s);
								hsD.put(D, newsb);
								
							}
					    }
						
					    //O操作
					    if(Otime.compareTo(wystart01)>=0 && Otime.compareTo(wyend01)<0)
					    {   
							int i=1;
							String ti="05:00:00 06:00:00";
							if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart02)>=0 && Otime.compareTo(wyend02)<0)
					    {
					    	int i=2;
					    	String ti="06:00:00 07:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart03)>=0 && Otime.compareTo(wyend03)<0)
					    {
					    	int i=3;
					    	
					    	String ti="07:00:00 08:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart04)>=0 && Otime.compareTo(wyend04)<0)
					    {
					    	int i=4;
					    	String ti="08:00:00 09:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    //
					    if(Otime.compareTo(wystart05)>=0 && Otime.compareTo(wyend05)<0)
					    {
					    	int i=5;
					    	String ti="09:00:00 10:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart06)>=0 && Otime.compareTo(wyend06)<0)
					    {
					    	int i=6;
					    	String ti="10:00:00 11:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart07)>=0 && Otime.compareTo(wyend07)<0)
					    {
					    	int i=7;
					    	String ti="11:00:00 12:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart08)>=0 && Otime.compareTo(wyend08)<0)
					    {
					    	int i=8;
					    	String ti="12:00:00 13:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart09)>=0 && Otime.compareTo(wyend09)<0)
					    {
					    	int i=9;
					    	String ti="13:00:00 14:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart10)>=0 && Otime.compareTo(wyend10)<0)
					    {
					    	int i=10;
					    	String ti="14:00:00 15:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart11)>=0 && Otime.compareTo(wyend11)<0)
					    {
					    	int i=11;
					    	String ti="15:00:00 16:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart12)>=0 && Otime.compareTo(wyend12)<0)
					    {
					    	int i=12;
					    	String ti="16:00:00 17:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart13)>=0 && Otime.compareTo(wyend13)<0)
					    {
					    	int i=13;
					    	String ti="17:00:00 18:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart14)>=0 && Otime.compareTo(wyend14)<0)
					    {
					    	int i=14;
					    	String ti="18:00:00 19:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart15)>=0 && Otime.compareTo(wyend15)<0)
					    {
					    	int i=15;
					    	String ti="19:00:00 20:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart16)>=0 && Otime.compareTo(wyend16)<0)
					    {
					    	int i=16;
					    	String ti="20:00:00 21:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart17)>=0 && Otime.compareTo(wyend17)<0)
					    {
					    	int i=17;
					    	String ti="21:00:00 22:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
					    if(Otime.compareTo(wystart18)>=0 && Otime.compareTo(wyend18)<0)
					    {
					    	int i=18;
					    	String ti="22:00:00 23:00:00";
					    	if(hsO.containsKey(O))
							{   
								//++操作
								Subitem tmpsb=new Subitem();
								tmpsb=hsO.get(O);
								HashMap<String,Ssubitem> m=new HashMap<String,Ssubitem>();
							    m=tmpsb.getHashMap();
								Ssubitem s=new Ssubitem();s=m.get(ti);
								tmp=s.getO();
								tmp++;
								s.setO(tmp);
								m.put(ti, s);
								tmpsb.putHashMap(m);
								hsO.put(O, tmpsb);
							}
							else
							{   
								Subitem newsb=new Subitem();
								Ssubitem s=new Ssubitem();
								s.setO(1);
								newsb.setHash(i, s);
								hsO.put(O, newsb);
								
							}
					    }
						
					   }
					   String[]T={"05:00:00 06:00:00","06:00:00 07:00:00","07:00:00 08:00:00","08:00:00 09:00:00","09:00:00 10:00:00","10:00:00 11:00:00","11:00:00 12:00:00","12:00:00 13:00:00","13:00:00 14:00:00","14:00:00 15:00:00","15:00:00 16:00:00","16:00:00 17:00:00","17:00:00 18:00:00","18:00:00 19:00:00","19:00:00 20:00:00","20:00:00 21:00:00","21:00:00 22:00:00","22:00:00 23:00:00"};
					   //写入数据,计算O-D,D-O
					    ArrayList<String>namelist=new ArrayList<String>();
					    Map<String,Subitem> map=hsO;
					    Iterator<?> iter=map.entrySet().iterator();
					    while(iter.hasNext())
					    {
						Map.Entry<String, Subitem> entry=(Map.Entry)iter.next();
						String name=entry.getKey();
						namelist.add(name);
					    }
					    
					    Map<String,Subitem> map2=hsD;
					    Iterator<?> iter2=map2.entrySet().iterator();
					    while(iter2.hasNext())
					    {
						Map.Entry<String, Subitem> entry=(Map.Entry)iter2.next();
						String name=entry.getKey();
						if(namelist.contains(name))
						   continue;
						else
						   namelist.add(name);
					    }
					    
					   String writefile=wfile;
					   for(int i=0;i<namelist.size();i++)
					   {
						   String tmpname=namelist.get(i);
				       try {
							BufferedWriter bw;
							br.close();
						        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writefile+tmpname+".csv"), "UTF-8"));
							bw.write((char)(65279) + "Time,O,D,O-D,D-O");
							bw.newLine();
							Subitem tmpsbO=new Subitem();
							Subitem tmpsbD=new Subitem();
							if(hsO.containsKey(tmpname))
							  tmpsbO=hsO.get(tmpname);//获取key对应的Subitem
							if(hsD.containsKey(tmpname))
							  tmpsbD=hsD.get(tmpname);
							HashMap<String,Ssubitem>Ol=new HashMap<String,Ssubitem>();
							HashMap<String,Ssubitem>Dl=new HashMap<String,Ssubitem>();
							Ol=tmpsbO.getHashMap();
							Dl=tmpsbD.getHashMap();
							Ssubitem sitem=new  Ssubitem();
							int Ddata=0;
							int Odata=0;
							int OD=0;
							int DO=0;
							for(int j=0;j<18;j++)
							{
								sitem=Ol.get(T[j]);
								Odata=sitem.getO();
								sitem=Dl.get(T[j]);
								Ddata=sitem.getD();
								OD=Odata-Ddata;
								DO=Ddata-Odata;
								bw.write(T[j]+","+Odata+","+Ddata+","+OD+","+DO+"\r\n");
							
							}
							bw.flush();
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				    }
				       
				    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				    }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*solve-按天
		int i,j,k;
		String filer="";
		String filew="";
		int []month=new int[2];
		month[0]=8;
		month[1]=9;
		int []day=new int[2];
		day[0]=31;
		day[1]=30;
		for(i=0;i<2;i++)
		{  
			for(j=1;j<=day[i];j++)
			  {
			    filer="T:\\2016xxxx\\20160"+month[i]+(j>9?j:"0"+j)+"_AfcStandarddata.csv";
			    filew="T:\\zznSubwayDayxxx\\"+month[i]+"."+j+"\\20160"+month[i]+(j>9?j:"0"+j)+"_";
			    solve(filer,filew);
			  }
		}
		*/
		//按小时
		int i,j,k;
		String filer="";
		String filew="";
		int []month=new int[2];
		month[0]=8;
		month[1]=9;
		int []day=new int[2];
		day[0]=31;
		day[1]=30;
		for(i=0;i<2;i++)
		{  
			for(j=1;j<=day[i];j++)
			  {
				filer="T:\\2016xxxxx\\20160"+month[i]+(j>9?j:"0"+j)+"_AfcStandarddata.csv";
			    filew="T:\\zznSubwayHourxxxx\\"+month[i]+"."+j+"\\20160"+month[i]+(j>9?j:"0"+j)+"_";
			    solve2(filer,filew);
			  }
		}
		
		
		
	}

}
