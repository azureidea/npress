package nc.liat6.npress.bean;

/**
 * 模块
 * 
 * @author 6tail
 * 
 */
public class Module{

  /** 本窗口打开的本站URL */
  public static final int TYPE_IN_SELF_URL = 0;
  /** 新窗口打开的本站URL */
  public static final int TYPE_IN_BLANK_URL = 1;
  /** 本窗口打开的外站URL */
  public static final int TYPE_OUT_SELF_URL = 2;
  /** 新窗口打开的外站URL */
  public static final int TYPE_OUT_BLANK_URL = 3;
  /** 本窗口打开的页面 */
  public static final int TYPE_SELF_PAGE = 4;
  /** 新窗口打开的页面 */
  public static final int TYPE_BLANK_PAGE = 5;
  /** ID */
  private long id;
  /** 名称 */
  private String name;
  /** 类型 */
  private int type;
  /** URL */
  private String url;
  /** 是否是默认首页，1是，0否 */
  private int home;
  /** 内容 */
  private String content;
  /** 序号 */
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
