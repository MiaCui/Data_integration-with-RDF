package JDBC;

import java.io.InputStream;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import com.hp.hpl.jena.util.FileManager;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.File;
import java.io.*;
public class JDBCtest{
	static Connection conn= null;
	//static Statement st = null;
	static ResultSet rs= null;
	static PreparedStatement st=null;
	String a="1 2 3 4 5 6 7 8";
	
	public static void main(String[]args) throws Exception
	{
		/*String[] a=new String[6];
		int count=0;
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","123456798");
		String query="select * from FINAL ";
		st=conn.prepareStatement(query);
		rs=st.executeQuery();
		if(rs!=null) {
			ResultSetMetaData data=rs.getMetaData();
			while(rs.next()) {
				for(int i=1;i<=data.getColumnCount();i++)
				{System.out.print(data.getColumnName(i));
				count=count+1;}
			}
			
			}
		String command1 = "/bin/sh "+"/Users/cuichen/Downloads/aa.sh"+" root"+" 123456798";
		Process process =null;
		process=Runtime.getRuntime().exec(command1);
	    process.waitFor();*/
		File file = new File("/Users/cuichen/Downloads/d2rq-0.8.1/aa.nt");  
        File CR = new File("/Users/cuichen/Downloads/d2rq-0.8.1/GG.nt");
        
		String str1="<http://www.w3.org/2000/01/rdf-schema#label>";
		String str2="<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>";
        String str3="http://www.w3.org/2001/XMLSchema";
		CR.createNewFile();
		FileWriter writer=null;
	//	writer=new FileWriter(file,true);
        BufferedReader reader = null;  
        writer=new FileWriter(CR,true);
		try {
		    reader=new BufferedReader(new FileReader(file));
		    String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
            	if(tempString.startsWith("<file:///Users/cuichen/Downloads/d2rq-0.8.1/aa.nt"))
            	{
            		if (tempString.split(" ")[1].contains(str1))
            		continue;
            		else if (tempString.split(" ")[1].contains(str2))
            		continue;
            		else if(tempString.split(" ")[2].contains(str3))
            		{
            			System.out.print("AAAAA");
            			tempString=tempString.replace("^^<http://www.w3.org/2001/XMLSchema#integer>", "");
            			//writer.append(tempString);
            			writer.write(tempString+"\n");
            			//writer.write("AAAAA");
            			writer.flush();
          
            		}
            	}
            	else continue;
                // 显示行号  
                System.out.println(tempString);  
                //line++;  
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  }}}
		
		
		
		
