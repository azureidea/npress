package nc.liat6.npress.bean;

/**
 * ÎÄÕÂ
 * 
 * @author 6tail
 * 
 */
public class Article{

	/** ID */
	private long id;
	/** ±êÌâ */
	private String title;
	private String content;
	private String description;
	private String day;
	private String time;

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDay(){
		return day;
	}

	public void setDay(String day){
		this.day = day;
	}

	public String getTime(){
		return time;
	}

	public void setTime(String time){
		this.time = time;
	}

}
