package CalcuSubODByday;

import java.util.HashMap;

public class Subitem {
	private int O =0;
	private int D =0;
	private int OD =0;
	private int DO =0;
	public HashMap<String,Ssubitem>L;//���ÿ��վ���ʱ��κͶ�Ӧ��O,D,OD,DO��ֵ,������Ӧ��5:00~23:00�Ķα��
	public Subitem(){
		L=new HashMap<String,Ssubitem>();
		//ע�⣬�����ʼ��L��putʱ��������һ���ģ�����ԭ��ÿ��L������ͬһ��new Ssubitem()����ʹ��������ĳ����һ��������ȫ���������ˡ�����
		L.put("05:00:00 06:00:00", new Ssubitem());
		L.put("06:00:00 07:00:00", new Ssubitem());
		L.put("07:00:00 08:00:00", new Ssubitem());
		L.put("08:00:00 09:00:00", new Ssubitem());
		L.put("09:00:00 10:00:00", new Ssubitem());
		L.put("10:00:00 11:00:00", new Ssubitem());
		L.put("11:00:00 12:00:00", new Ssubitem());
		L.put("12:00:00 13:00:00", new Ssubitem());
		L.put("13:00:00 14:00:00", new Ssubitem());
		L.put("14:00:00 15:00:00", new Ssubitem());
		L.put("15:00:00 16:00:00", new Ssubitem());
		L.put("16:00:00 17:00:00", new Ssubitem());
		L.put("17:00:00 18:00:00", new Ssubitem());
		L.put("18:00:00 19:00:00", new Ssubitem());
		L.put("19:00:00 20:00:00", new Ssubitem());
		L.put("20:00:00 21:00:00", new Ssubitem());
		L.put("21:00:00 22:00:00", new Ssubitem());
		L.put("22:00:00 23:00:00", new Ssubitem());
	}

	public void setO(int o)
	{
		this.O=o;
	}
	public void setD(int d)
	{
		this.D=d;
	}
	public int getO()
	{
		return this.O;
	}
	public int getD()
	{
		return this.D;
	}
	public void calOD()
	{
		this.OD=this.O-this.D;
	}
	public void calDO()
	{
		this.DO=this.D-this.O;
	}
	public  HashMap<String,Ssubitem> getHashMap()
	{
		return this.L;
	}
	public void putHashMap(HashMap<String,Ssubitem> l)
	{   
		this.L=l;
	}
	public void setHash(int i,Ssubitem ss)
	{   
		Ssubitem s=new Ssubitem();
		s=ss;
		String time="";
		switch(i){
		case 1:{time="05:00:00 06:00:00";break;}
		case 2:{time="06:00:00 07:00:00";break;}
		case 3:{time="07:00:00 08:00:00";break;}
		case 4:{time="08:00:00 09:00:00";break;}
		case 5:{time="09:00:00 10:00:00";break;}
		case 6:{time="10:00:00 11:00:00";break;}
		case 7:{time="11:00:00 12:00:00";break;}
		case 8:{time="12:00:00 13:00:00";break;}
		case 9:{time="13:00:00 14:00:00";break;}
		case 10:{time="14:00:00 15:00:00";break;}
		case 11:{time="15:00:00 16:00:00";break;}
		case 12:{time="16:00:00 17:00:00";break;}
		case 13:{time="17:00:00 18:00:00";break;}
		case 14:{time="18:00:00 19:00:00";break;}
		case 15:{time="19:00:00 20:00:00";break;}
		case 16:{time="20:00:00 21:00:00";break;}
		case 17:{time="21:00:00 22:00:00";break;}
		case 18:{time="22:00:00 23:00:00";break;}
		}
		this.L.put(time, s);
	}
	

}
