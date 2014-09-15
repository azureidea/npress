package nc.liat6.npress.bean;

/**
 * 友情链接
 * 
 * @author 6tail
 * 
 */
public class Link{

  /** ID */
  private long id;
  /** 名称 */
  private String name;
  /** URL */
  private String url;
  /** 序号 */
  private int index;

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

  public String getUrl(){
    return url;
  }

  public void setUrl(String url){
    this.url = url;
  }

  public int getIndex(){
    return index;
  }

  public void setIndex(int index){
    this.index = index;
  }
}