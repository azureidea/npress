package nc.liat6.npress.exception;

/**
 * 需要登录
 * 
 * @author 6tail
 *
 */
public class UserLoginException extends RuntimeException{
  private static final long serialVersionUID = 1671662042385870496L;

  @Override
  public String getMessage(){
    return "请重新登录！";
  }
}