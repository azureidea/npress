package nc.liat6.npress.bean;

/**
 * 分类
 *
 * @author 6tail
 *
 */
public class Cat{

  /** ID */
  private long id;
  /** 名称 */
  private String name;
  /** 文章数 */
  private int count;
  /** 类型，1显示，0隐藏 */
  private int type;

  public int getType(){
    return type;
  }

  public void setType(int type){
    this.type = type;
  }

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
