package JDBC;
import JDBC.COMBINE;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import com.hp.hpl.jena.util.FileManager;


public class GUI {
	static Connection conn= null;
	//static Statement st = null;
	static ResultSet rs= null;
	static PreparedStatement st=null;
	public static void  process(String a,String b) throws Exception{
		String dir="/Users/cuichen/Downloads/d2rq-0.8.1/";
		File file = new File(dir+a);  
        File CR = new File(dir+b);
        
		String str1="<http://www.w3.org/2000/01/rdf-schema#label>";
		String str2="<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>";
        String str3="http://www.w3.org/2001/XMLSchema";
		CR.createNewFile();
		FileWriter writer=null;
	//	writer=new FileWriter(file,true);
        BufferedReader reader = null;  
        writer=new FileWriter(CR,false);
		try {
		    reader=new BufferedReader(new FileReader(file));
		    String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
            	if(tempString.startsWith("<file:///Users/cuichen/Downloads/d2rq-0.8.1/"+a))
            	{
            		if (tempString.split(" ")[1].contains(str1))
            		continue;
            		else if (tempString.split(" ")[1].contains(str2))
            		continue;
            		else if(tempString.split(" ")[2].contains(str3))
            		{
            			//System.out.print("AAAAA");
            			tempString=tempString.replace("^^<http://www.w3.org/2001/XMLSchema#integer>", "");
            			//writer.append(tempString);
            			writer.write(tempString+"\n");
            			//writer.write("AAAAA");
            			writer.flush();
          
            		}
            		else
            		{
            			writer.write(tempString+"\n");
            			//writer.write("AAAAA");
            			writer.flush();
            		}
            	}
            	else continue;
                // 显示行号  
              //  System.out.println(tempString);  
                //line++;  }
            			
            			//System.out.print("AAAAA");
            			//tempString=tempString.replace("^^<http://www.w3.org/2001/XMLSchema#integer>", "");
            			
            			//writer.append(tempString);
            			//writer.write(tempString+"\n");
            			//writer.write("AAAAA");
            			//writer.flush();
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
            }  }
		
	}
	 public static void main(String[] args)throws Exception {
		 
		 Map dbs=new HashMap();
		 JFrame FL=new JFrame();
		 FL.setLayout(null);
		 //FL.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		 JPanel p1=new JPanel();
		 JPanel p2=new JPanel();
		 JLabel L=new JLabel("FLOOD DATA INTEGRATION SYSTEM");
		// FL.add(L,BorderLayout.NORTH);
		 GridLayout G=new GridLayout(4,6);
		 GridLayout G2=new GridLayout(2,3);
		 //p1.setLayout(G);
		 //p2.setLayout(G2);
		 JLabel[] T=new JLabel[3];
		 JLabel[] DB=new JLabel[3];
		 JLabel[] U=new JLabel[3];
		 JLabel[] P=new JLabel[3];
		 JLabel[] Y=new JLabel[2];
		 JTextField[] TT=new JTextField[3];
		 JTextField[] DBT=new JTextField[3];
		 JTextField[] UU=new JTextField[3];
		 JPasswordField[] PP=new JPasswordField[3];
		 JButton[] B=new JButton[3];
		 JTextField[] REC=new JTextField[3];
		 JTextField[] RE=new JTextField[2];
		 JPanel[]PA=new JPanel[3];
		 GridLayout LA=new GridLayout(5,2);
		 String[] str1 = new String[20];
		 String[] str2 = new String[20];
		 for (int i=0;i<3;i++)
		 {
			DB[i]=new JLabel("Database"+(i+1));
			TT[i]=new JTextField(10);
			DB[i].setBounds(20+i*300,40, 120, 30);
			TT[i].setBounds(120+i*300,40, 120, 30);
			FL.add(DB[i]);
			FL.add(TT[i]);
			T[i]=new JLabel("Table"+(i+1));
			DBT[i]=new JTextField(10);
			T[i].setBounds(20+i*300,100, 120, 30);
			DBT[i].setBounds(120+i*300,100, 120, 30);
			FL.add(T[i]);
			FL.add(DBT[i]);
			U[i]=new JLabel("UserName"+(i+1));
			UU[i]=new JTextField(10);
			U[i].setBounds(20+i*300,160, 120, 30);
			UU[i].setBounds(120+i*300,160, 120, 30);
			FL.add(U[i]);
			FL.add(UU[i]);
			P[i]=new JLabel("PassWord"+(i+1));
			PP[i]=new JPasswordField("");
			P[i].setBounds(20+i*300,220, 120, 30);
			PP[i].setBounds(120+i*300,220, 120, 30);
			FL.add(P[i]);
			FL.add(PP[i]);
			B[i]=new JButton("提交");
			B[i].setBounds(100+i*300, 280, 100, 30);
			FL.add(B[i]);
			PA[i]=new JPanel();
			PA[i].setBounds(20+i*300, 340, 300, 200);
			PA[i].setLayout(LA);
			if(i!=2)
			{
				Y[i]=new JLabel("映射规律"+(i+1));
				Y[i].setBounds(20+i*300, 560, 120, 30);
				FL.add(Y[i]);
				RE[i]=new JTextField(20);
				RE[i].setBounds(120+i*300,560,120,30);
				FL.add(RE[i]);
			}
			
			FL.add(PA[i]);
		 }
		 JButton combine=new JButton("数据预处理");
		 combine.setBounds(400,600,100,30);
		 FL.add(combine);
	 
		for(int i=0;i<3;i++) {
			int t=i;
		 B[i].addActionListener(new ActionListener() {
			// ArrayList<String> str = new ArrayList<String> ();
				@Override
				public void actionPerformed(ActionEvent e){
					String database=TT[t].getText();
					String Table=DBT[t].getText();
					String user=UU[t].getText();
					char[]pwd=PP[t].getPassword();
					String PWD=String.valueOf(pwd);
					try {
						Class.forName("com.mysql.jdbc.Driver");
						//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+TT?useSSL=false",user,PWD);
						conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database+"?useSSL=false",user,PWD);
						System.out.println("success");
						//st=(Statement)conn.createStatement();
						String query="select * from "+Table+" limit 1";
						st=conn.prepareStatement(query);
						rs=st.executeQuery();
						if(rs!=null) {
							ResultSetMetaData data=rs.getMetaData();
							while(rs.next()) {
								for(int i=1;i<=data.getColumnCount();i++)
								{
									if(t==0)
										str1[i-1]=data.getColumnName(i);
									if(t==1)
										str2[i-1]=data.getColumnName(i);
									if(t==2)
										{
										dbs.put(i, data.getColumnName(i));}
									
									JLabel ne=new JLabel(i+" "+data.getColumnName(i));
									PA[t].add(ne);
									}
							}
							
							}
						//System.out.print(Table);
						//RE[t].setText(str);
						PA[t].revalidate();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			 });}
		 combine.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String[]da= {TT[0].getText(),TT[1].getText()};
				String[]Table= {DBT[0].getText(),DBT[1].getText()};
				String[]us= {UU[0].getText(),UU[1].getText()};
				String[]pw=new String[2];
				//String[]array2=null;
				//String[]array1=null;
				String[]YY= {RE[0].getText(),RE[1].getText()};
				for (int i=0;i<2;i++)
				{
				char[]pwd=PP[i].getPassword();
				pw[i]=String.valueOf(pwd);}
				String[]array1=YY[0].split(" ");
				String[]array2=YY[1].split(" ");
				/*for (int i=0;i<2;i++)
				{
					if(i==1)
					array[i]=YY[i].split(" ");
					for(int j=0;j<array.length;j++)
					{
						if(i==0)
							{
							//dbs[i]=new HashMap();
							//dbs[i].put(Integer.parseInt(array[j]), str1[j]);
							System.out.print(array[j]);
							//System.out.print(Integer.parseInt(array[j]));
							}
						if(i==1)
							{
							dbs[i]=new HashMap();
							dbs[i].put(Integer.parseInt(array[j]), str2[j]);
							System.out.print(array[j]);
							//System.out.print(j);
							}
					}
				}*/
				String command1 = "/bin/sh "+"/Users/cuichen/Downloads/aa.sh"+" "+us[0]+" "+pw[0]+" "+da[0]+" "+us[1]+" "+pw[1]+" "+da[1];
				Process process =null;
				try {
					process=Runtime.getRuntime().exec(command1);
					process.waitFor();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					process("aa.nt","cc.nt");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					process("bb.nt","dd.nt");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		        try {
		        	String dir="/Users/cuichen/Downloads/d2rq-0.8.1/";
					File file = new File(dir+"cc.nt");  
			        File CR = new File(dir+"ee.nt");
					CR.createNewFile();
					FileWriter writer=null;
					//	writer=new FileWriter(file,true);
				    BufferedReader reader = null;  
				    writer=new FileWriter(CR,false);
				    reader=new BufferedReader(new FileReader(file));
				    String tempString = null;  
		            int line = 1; 
		            // 一次读入一行，直到读入null为文件结束  
		            while ((tempString = reader.readLine()) != null) {
		            	for(int i=0;i<array1.length;i++)
		            	{
		            		if(array1[i].equals("0")){
		            			if(tempString.contains(str1[i]))
		            				{System.out.print(i);continue;}
		            		}
		            		else {
		            			if(tempString.contains(str1[i]))
		            			{
		            				String cover=dbs.get(Integer.parseInt(array1[i])).toString();
		            				
		            				tempString=tempString.replace(Table[0]+"_"+str1[i], cover);
		            				writer.write(tempString+"\n");
		                			//writer.write("AAAAA");
		                			writer.flush();
		            			}
		            		}
		            		
		            	}
		            } 
		           // String dir="/Users/cuichen/Downloads/d2rq-0.8.1/";
					File file1 = new File(dir+"dd.nt");  
			        File CR1 = new File(dir+"ff.nt");
					CR.createNewFile();
					FileWriter writer1=null;
					//	writer=new FileWriter(file,true);
				    BufferedReader reader1 = null;  
				    writer1=new FileWriter(CR1,false);
				    reader1=new BufferedReader(new FileReader(file1));
				    String tempString1 = null;  
		            int line1 = 1; 
		            // 一次读入一行，直到读入null为文件结束  
		            while ((tempString1 = reader1.readLine()) != null) {
		            	for(int i=0;i<array2.length;i++)
		            	{
		            		if(array2[i].equals("0")){
		            			if(tempString1.contains(str2[i]))
		            				{System.out.print(i);continue;}
		            		}
		            		else {
		            			if(tempString1.contains(str2[i]))
		            			{
		            				String cover=dbs.get(Integer.parseInt(array2[i])).toString();
		            				
		            				tempString1=tempString1.replace(Table[1]+"_"+str2[i], cover);
		            				writer1.write(tempString1+"\n");
		                			//writer.write("AAAAA");
		                			writer1.flush();
		            			}
		            		}
		            		
		            	}
		            } 
		            try {
			        	int line2=0;
						line2=COMBINE.main("ee.nt",0,Table[0]+"/");
						line2=COMBINE.main("ff.nt", line2,Table[1]+"/");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		        
		       
		      
		        }
				
		        
		         
		       
			
			 
		 });
		 
		// FL.add(p1);
		 FL.setBounds(100, 200, 1000, 800);
		 //FL.add(p2);
		 FL.setVisible(true);
		 FL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
	        
	    }
	
}
	

