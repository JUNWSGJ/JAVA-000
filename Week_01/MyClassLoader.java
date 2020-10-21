import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader{


  @Override
  protected Class<?> findClass(String name) {
    File file = new File(name);
    InputStream in = null;
    ByteArrayOutputStream out = null;
    int byteCount = 0;
    try {
      in = new FileInputStream(file);
      out = new ByteArrayOutputStream(4096);

      byte[] buffer = new byte[4096];
      int bytesRead;
      for(; (bytesRead = in.read(buffer)) != -1; byteCount += bytesRead) {
        out.write(buffer, 0, bytesRead);
      }
      out.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if(in!=null){
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if(out!=null) {
        try {
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    byte[] bytes = out.toByteArray();
    byte[] newBytes = new byte[byteCount];
    for(int i =0; i<bytes.length; i++){
      newBytes[i] = (byte) (255-bytes[i]);
    }
    return defineClass("Hello", newBytes, 0, newBytes.length);
  }

  public static void main(String[] args) {
    MyClassLoader classLoader = new MyClassLoader();
    try{
      Class clazz = classLoader.findClass("Hello.xlass");
      Constructor con = clazz.getDeclaredConstructor();
      Method method = clazz.getDeclaredMethod("hello");
      method.invoke(con.newInstance());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }



}