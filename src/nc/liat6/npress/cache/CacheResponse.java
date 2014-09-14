package nc.liat6.npress.cache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 缓存输出
 * 
 * @author 6tail
 * 
 */
public class CacheResponse extends HttpServletResponseWrapper{

  private WrapperPrintWriter writer;
  private ByteArrayOutputStream output;

  public CacheResponse(HttpServletResponse httpServletResponse){
    super(httpServletResponse);
    output = new ByteArrayOutputStream();
    writer = new WrapperPrintWriter(output);
  }

  public void finalize() throws Throwable{
    super.finalize();
    output.close();
    writer.close();
  }

  public String getContent(){
    writer.flush();
    return writer.getByteArrayOutputStream().toString();
  }

  public PrintWriter getWriter() throws IOException{
    return writer;
  }

  public void close() throws IOException{
    writer.close();
  }
  private class WrapperPrintWriter extends PrintWriter{

    private ByteArrayOutputStream output;

    public WrapperPrintWriter(ByteArrayOutputStream output){
      super(output);
      this.output = output;
    }

    public ByteArrayOutputStream getByteArrayOutputStream(){
      return output;
    }
  }
}
