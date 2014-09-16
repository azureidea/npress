package nc.liat6.npress.bean;

/**
 * 文章
 * 
 * @author 6tail
 * 
 */
public class Article{

  /** ID */
  private long id;
  /** 标题 */
  private String title;
  /** 内容 */
  private String content;
  /** 简要描述 */
  private String description;
  /** 发布日期 */
  private String day;
  /** 发布时间 */
  private String time;
  /** 图片路径 */
  private String pic;

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

  public String getPic(){
    return pic;
  }

  public void setPic(String pic){
    this.pic = pic;
  }
}
