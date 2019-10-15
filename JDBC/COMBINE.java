package JDBC;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import java.util.HashMap;
import java.util.Map;


import com.hp.hpl.jena.util.FileManager;

import java.sql.Connection;



public class COMBINE {
	public static int main(String args,int ID,String table) throws Exception 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载数据库驱动成功");
			String url="jdbc:mysql://localhost:3306/test";
			String user="root";
			String pw="123456798";
			Connection conn=DriverManager.getConnection(url,user,pw);
			//System.out.println("数据库连接成功");
			//String sql = "insert into test (N,W,P,C,T) values(?,?,?,?,?)";
			//PreparedStatement ps = conn.prepareStatement(sql);

			
		
		
		Map dbs1=new HashMap();
		//String inputFileName="/Users/cuichen/Downloads/d2rq-0.8.1/test3.nt";
		String inputFileName="/Users/cuichen/Downloads/d2rq-0.8.1/"+args;
		//String inputFileName="/Users/cuichen/Downloads/毕设/apple.owl";
		Model model=ModelFactory.createDefaultModel();
		InputStream in=FileManager.get().open(inputFileName);
		if(in==null) {
			throw new IllegalArgumentException("File not found");
		}
		model.read(in,"","N3");
		//model.read(in, "RDF/XML");
		String queryString="PREFIX :<http://www.kgdemo.com#>"
		+"SELECT * WHERE {?sub ?b ?obj}";
		Query query=QueryFactory.create(queryString);
		QueryExecution qe=QueryExecutionFactory.create(query,model);
		ResultSet results=qe.execSelect();
		//ResultSetFormatter.out(System.out, results, query);
		int a=0;
		String RES=null;
		String[]KEY=new String[20];
		String[]VALUE=new String[20];
		while(results.hasNext()) {
			
			QuerySolution next = results.next();
			String resource = next.getResource("sub").toString();
			//resource=resource.split("transportation/")[1];
			resource=resource.split(table)[1];
			String p = next.get("b").toString();
			if(a==0)
				{
				RES=resource;
				p=p.split("/vocab/")[1];
				String b = next.get("obj").toString();
				//dbs1.put(p,b);
				KEY[a]=p;
				VALUE[a]=b;
				a++;
				}
			else
			{
				if(RES.equals(resource)&&results.hasNext()==true)
				{
					p=p.split("/vocab/")[1];
					String b = next.get("obj").toString();
					KEY[a]=p;
					VALUE[a]=b;
					a++;
				}
				else 
				{
					ID++;
					RES=resource;
					String sql1 = "insert into FINAL (ID) values(?)";
					PreparedStatement ps1 = conn.prepareStatement(sql1);
					ps1.setInt(1, ID);
					ps1.executeUpdate();
					for(int i=0;i<a;i++)
						{
						
						String sql=null;
						if(KEY[i].equals("LLocation"))
						sql = "update FINAL set LLocation= ? where ID=?";
						else if(KEY[i].equals("Area_destroy"))
							sql = "update FINAL set Area_destroy= ? where ID=?";
						else if(KEY[i].equals("Flood_level"))
							sql = "update FINAL set Flood_level= ? where ID=?";
						else if(KEY[i].equals("Rainfall"))
							sql = "update FINAL set Rainfall= ? where ID=?";
						else if(KEY[i].equals("Affected_population"))
							sql = "update FINAL set Affected_population= ? where ID=?";
						else if(KEY[i].equals("D_number"))
							sql = "update FINAL set D_number= ? where ID=?";
						else if(KEY[i].equals("Dis_number"))
							sql = "update FINAL set Dis_number= ? where ID=?";
						else if(KEY[i].equals("Railway_rupt"))
							sql = "update FINAL set Railway_rupt= ? where ID=?";
						else if(KEY[i].equals("Highway_rupt"))
							sql = "update FINAL set Highway_rupt= ? where ID=?";
						else if(KEY[i].equals("Port_rupt"))
							sql = "update FINAL set Port_rupt= ? where ID=?";
						else if(KEY[i].equals("Cor_name"))
							sql = "update FINAL set Cor_name= ? where ID=?";
						else if(KEY[i].equals("Tel"))
							sql = "update FINAL set Tel= ? where ID=?";
						else if(KEY[i].equals("Financial"))
							sql = "update FINAL set Financial= ? where ID=?";
						else if(KEY[i].equals("DATE"))
							sql = "update FINAL set DATE= ? where ID=?";
						//declare @KKK STring;
						PreparedStatement ps = conn.prepareStatement(sql);

						//ps.setString(1,KEY[i]);
						System.out.print(KEY[i]+VALUE[i]+'\n');
						ps.setString(1,VALUE[i]);
						ps.setInt(2, ID);
						ps.executeUpdate();
						}
					if(results.hasNext())
					{
						a=0;
						p=p.split("/vocab/")[1];
						String b = next.get("obj").toString();
						KEY[a]=p;
						VALUE[a]=b;	
						//System.out.println(resource+'\t'+p+'\t'+b);
						a++;
						
					}
					
				}
			}
			
		}
		//ResultSetFormatter.out(System.out,results,query);
		qe.close();	
		
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
		return ID;
	}
}

