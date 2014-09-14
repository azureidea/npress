package nc.liat6.npress.bean;

/**
 * 用户
 * 
 * @author 6tail
 * 
 */
public class User{

  /** ID */
  private long id;
  /** 昵称 */
  private String name;
  /** 密码 */
  private String password;
  /** 用户名 */
  private String account;
  /** 是否管理员，1管理员，0非管理员 */
  private int admin;

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

  public String getPassword(){
    return password;
  }

  public void setPassword(String password){
    this.password = password;
  }

  public String getAccount(){
    return account;
  }

  public void setAccount(String account){
    this.account = account;
  }

  public int getAdmin(){
    return admin;
  }

  public void setAdmin(int admin){
    this.admin = admin;
  }
}
