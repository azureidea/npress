package nc.liat6.npress.bean;

/**
 * ����
 * 
 * @author 6tail
 * 
 */
public class Cat{

	/** ID */
	private long id;
	/** ���� */
	private String name;
	/** ������ */
	private int count;

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count = count;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

}
