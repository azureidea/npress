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
  /** 插件 */
  public static final int TYPE_PLUGIN = 6;
  /** 模块位置：顶部 */
  public static final int POS_TOP = 0;
  /** 模块位置：底部 */
  public static final int POS_BOTTOM = 1;
  /** 模块位置：中间底部 */
  public static final int POS_CENTER_BOTTOM = 2;
  /** 模块位置：右边底部 */
  public static final int POS_RIGHT_BOTTOM = 3;
  /** 模块位置：中间顶部 */
  public static final int POS_CENTER_TOP = 4;
  /** 模块位置：右边顶部 */
  public static final int POS_RIGHT_TOP = 5;
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
  /** 位置，0顶部，1底部，2文章详情底部，3右侧边栏底部，4文章详情顶部，5右侧边栏顶部 */
  private int pos;

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

  public int getPos(){
    return pos;
  }

  public void setPos(int pos){
    this.pos = pos;
  }
}
