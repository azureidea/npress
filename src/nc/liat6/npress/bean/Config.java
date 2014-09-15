package nc.liat6.npress.bean;

/**
 * 配置
 * 
 * @author 6tail
 * 
 */
public class Config{

  /** 键 */
  private String key;
  /** 值 */
  private String value;
  /** 名称 */
  private String name;
  /** 描述 */
  private String description;

  public String getKey(){
    return key;
  }

  public void setKey(String key){
    this.key = key;
  }

  public String getValue(){
    return value;
  }

  public void setValue(String value){
    this.value = value;
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getDescription(){
    return description;
  }

  public void setDescription(String description){
    this.description = description;
  }
}
