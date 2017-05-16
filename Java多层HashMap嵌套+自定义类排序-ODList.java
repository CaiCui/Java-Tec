package CalcuTimeMax;

public class ODList {
    
	private String O=null;
	private String D=null;
	private int t=0;
	public ODList()
	{
		
	}
	public ODList(String O,String D,int t)
	{
		this.O=O;
		this.D=D;
		this.t=t;
	}
	public int getT()
	{
		return this.t;
	}
	@Override
	public String toString() {
		return O+","+ D+ "," + t;
	}
}
