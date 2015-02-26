package hackathon;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class Hackathon {
public static void main(String args[])
{
        System.out.println("Oracle Connect START.");
        Connection conn = null;
        String url = "jdbc:oracle:thin:@Shashi-PC:1521:XE";
        String driver = "oracle.jdbc.OracleDriver";
        String userName = "scott";
        String password = "tiger";
        ResultSet rs = null;
        try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url,userName,password);
 
        Statement stmt = conn.createStatement();
         rs =stmt.executeQuery("select doc from lob_table");
         Blob lob = null;
         while (rs.next()) {
         lob=rs.getBlob("doc");
}
 
        InputStream in1 = lob.getBinaryStream();
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        OutputStream outputStream1 = new FileOutputStream("blobImage.png");
 
        int bufferSize = 1024;
        int length = (int) lob.length();
 
        byte[] buffer = new byte[bufferSize];
 
        while((length = in1.read(buffer)) != -1)
        {
         out1.write(buffer,0,length);
        }
        out1.writeTo(outputStream1);
        in1.close();
        conn.close();
 
         Process p1 =Runtime.getRuntime().exec("mspaint blobImage.png");
 
        }catch (Exception e) {
            e.printStackTrace();
            }
 
}
}