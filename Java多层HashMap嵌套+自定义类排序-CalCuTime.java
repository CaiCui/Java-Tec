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
 * ����268������վ��ÿ��վ��ͨ��������ODʱ��t�����������վ�� O�� D�� ʱ��t 
 * @author Cyf
 *
 */
public class CalCuTime {
	
	/**
	 * ����յ������ʱ����λ����
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
		    diff = d2.getTime() - d1.getTime();//����
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
		String filePath="T:\\xxx\\8��\\tripChain_20160801.csv";//8��1�ճ�����������ļ�·��
		String WfilePath="T:\\xxx_R_ODTimeMean_2.csv";//8��1�ճ�����������д����ļ�·��
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
		//ʱ���¼
		String start=null;
		String end=null;
		int Time;
		String R=null;//����վ��¼
		String O=null;
		String D=null;
		String ODKey=null;
		//ÿ��վ��R��Ӧһ��HashMap�����HashMap����ֱ��Ӧ��ͬ��ODKey��ODList����,ÿһ�����ݶ���O,D,t
		HashMap<String,HashMap<String,ArrayList<ODList>>>RList=new HashMap<String,HashMap<String,ArrayList<ODList>>>();
        try {		
			while ((line = br.readLine()) != null) {	
				String[] item = line.split(",");
			    String type=item[1];//��������
			    if(type.equals("B-R"))
			    {
			    	start=item[4];
			    	end=item[20];
			    	Time=getMinute(start,end);
			    	R=item[16];
			    	O=item[5];
			    	D=item[21];
			    	ODKey=O+"-"+D;
			    	if(RList.containsKey(R))//����վ��R
			    	{
			    		//ȡ��keyR��Ӧ��ֵ
			    		HashMap<String,ArrayList<ODList>> newl=new HashMap<String,ArrayList<ODList>>();
			    		newl=RList.get(R);
			    		if(newl.containsKey(ODKey))//�жϼ�R��Ӧ��ֵ�����Ƿ���ODKey
			    		{
			    			ArrayList<ODList> tmp=new ArrayList<ODList>();
			    			tmp=newl.get(ODKey);//ȡ����ӦList
			    			ODList it=new ODList(O,D,Time);
				    		tmp.add(it);//�����µ�Ԫ��
				    		newl.put(ODKey, tmp);//�Ż�
			    		}
			    		else
			    		{   
			    			ArrayList<ODList> tmp=new ArrayList<ODList>();
				    		ODList it=new ODList(O,D,Time);
				    		tmp.add(it);
			    			newl.put(ODKey, tmp);
			    		}
			    		RList.put(R, newl);//����
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
			    	if(RList.containsKey(R))//����վ��R
			    	{
			    		//ȡ��keyR��Ӧ��ֵ
			    		HashMap<String,ArrayList<ODList>> newl=new HashMap<String,ArrayList<ODList>>();
			    		newl=RList.get(R);
			    		if(newl.containsKey(ODKey))//�жϼ�R��Ӧ��ֵ�����Ƿ���ODKey
			    		{
			    			ArrayList<ODList> tmp=new ArrayList<ODList>();
			    			tmp=newl.get(ODKey);//ȡ����ӦList
			    			ODList it=new ODList(O,D,Time);
				    		tmp.add(it);//�����µ�Ԫ��
				    		newl.put(ODKey, tmp);//�Ż�
			    		}
			    		else
			    		{   
			    			ArrayList<ODList> tmp=new ArrayList<ODList>();
				    		ODList it=new ODList(O,D,Time);
				    		tmp.add(it);
			    			newl.put(ODKey, tmp);
			    		}
			    		RList.put(R, newl);//����
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
			    
			}//while����
			//����RList,Ҫ������ÿ��վ���HashMap<String,ODlist>��ÿ��OD-String��Ӧ��ODList����������ѡ�������Ǹ�HashMapֵ��Ȼ��Ը�ֵ��ODListԪ����ȡ��λ��ʱ����Ϊ��R��O,D,T
			Map<String,HashMap<String,ArrayList<ODList>>> map=RList;
			Iterator<?> iter=map.entrySet().iterator();
			int max=-10000;//����Ƚϱ���
			String key=null;//��¼ÿ��R����O-DKey
			String []keylist=new String[100000000];//��¼��R��Ӧ������O-Dkey��˳��
			int p=0;
			while(iter.hasNext())
			{   
				max=-10000;
				Map.Entry<String,HashMap<String,ArrayList<ODList>>> entry=(Map.Entry)iter.next();
				String r=entry.getKey();//ȡ��վ��R
				HashMap<String,ArrayList<ODList>> t=new HashMap<String,ArrayList<ODList>>();
               	t=entry.getValue();//ȡ��R��Ӧ�� HashMap,�ҳ�����������ODList
               	//����ͬ���ķ�����ȡ�õ�ֵHashMap<String,ArrayList<ODList>���б����ҳ���ǰR��Ӧ������O-D
               	Map<String,ArrayList<ODList>> mm=t;
               	Iterator<?> itr=mm.entrySet().iterator();
				while(itr.hasNext())
				{
					Map.Entry<String,ArrayList<ODList>> en=(Map.Entry)itr.next();
					String re=en.getKey();//O-D
					int num=en.getValue().size();//��O-D��Ӧ��ODList��Ԫ�ص�����
					if(num>max)//�ҳ���ǰR�¶�Ӧ������O-D
					{
						max=num;
						key=re;
					}
					
				}
				keylist[p++]=key;
			}
			//����RList��ÿ��R�������Ǹ�O-DKey��Ӧ��List�б������ʱ�����λ��ֵ��������Ȼ�����ӵ�ǰ10%��ʼ����90%�����ݵ�t��ƽ��ֵ,ͬʱд���ļ�
			int q=0;//keylist˳�����
			int sum=0;
			int w=0;
			try{
					BufferedWriter bw;
					bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(WfilePath), "UTF-8"));//�����ָ�ʽ
					bw.write((char)(65279)+"����վ��"+","+"�ϳ���-�³���"+","+"ʱ��t(min)"+"\r\n");
					Map<String,HashMap<String,ArrayList<ODList>>> Remap=RList;
					Iterator<?> Reiter=map.entrySet().iterator();
					while(Reiter.hasNext())//����
					{  
						Map.Entry<String,HashMap<String,ArrayList<ODList>>> entry=(Map.Entry)Reiter.next();
						String r=entry.getKey();//ȡ��վ��R
						HashMap<String,ArrayList<ODList>> t=new HashMap<String,ArrayList<ODList>>();
		               	t=entry.getValue();//ȡ��R��Ӧ�� HashMap
		               	ArrayList<ODList> ta=new ArrayList<ODList>();
		               	ta=t.get(keylist[q]);//ȡ����Ӧ����������ODList
		                Collections.sort(ta, new Comparator<Object>() { //����
		                    @Override
		                    public int compare(Object o1, Object o2) {
		                    	ODList s1=(ODList)o1;
		                    	ODList s2=(ODList)o2;
		                    	 if (s1.getT() > s2.getT())
		                           return 1;
		                        return -1;
		                    }
		                  });
		                //����ta,���t�ľ�ֵ
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
