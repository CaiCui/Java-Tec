package CalcuSubODByday;

public class Ssubitem {
	private int O =0;
	private int D =0;
	private int OD =0;
	private int DO =0;

	public Ssubitem(){
		
		this.O=0;
		this.D=0;
		this.OD=0;
		this.DO=0;
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
}
