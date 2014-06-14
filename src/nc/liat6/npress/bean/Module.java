package nc.liat6.npress.bean;

/**
 * ģ��
 * 
 * @author 6tail
 * 
 */
public class Module{

	/** �����ڴ򿪵ı�վURL */
	public static final int TYPE_IN_SELF_URL = 0;

	/** �´��ڴ򿪵ı�վURL */
	public static final int TYPE_IN_BLANK_URL = 1;

	/** �����ڴ򿪵���վURL */
	public static final int TYPE_OUT_SELF_URL = 2;

	/** �´��ڴ򿪵���վURL */
	public static final int TYPE_OUT_BLANK_URL = 3;

	/** �����ڴ򿪵�ҳ�� */
	public static final int TYPE_SELF_PAGE = 4;

	/** �´��ڴ򿪵�ҳ�� */
	public static final int TYPE_BLANK_PAGE = 5;

	/** ID */
	private long id;
	/** ���� */
	private String name;
	/** ���� */
	private int type;
	/** URL */
	private String url;
	/** �Ƿ���Ĭ����ҳ��1�ǣ�0�� */
	private int home;

	private String content;

	private int index;

	public int getIndex(){
		return index;
	}

	public void setIndex(int index){
		this.index = index;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public int getHome(){
		return home;
	}

	public void setHome(int home){
		this.home = home;
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

	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

}
