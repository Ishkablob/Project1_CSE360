package project1;

import java.io.*;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

public class Toolbar extends JPanel implements ActionListener{
	//JFileChooser chooser = new JFileChooser();
	private JButton select;
	private JButton format;
	private JButton save;
	private String line;
	private String temp;
	JFileChooser chooser = new JFileChooser();
	private String counter ="";
	private FormPanel pannel;
	private FormPanel lp;
	private FormPanel blankLines;
    int count;
    int lines;
	//BufferedReader output = null;
	BufferedReader input = null;
	PrintWriter output = null;


	
	File file = null;
	
	private StringListener textListener; 
	
	public Toolbar(){
		
		setBorder(BorderFactory.createEtchedBorder());
		
		select = new JButton("Select file");
		format = new JButton("Format");
		save = new JButton ("save file");
		
		select.addActionListener(this);
		format.addActionListener(this);
		save.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(select);
		add(format);
		add(save);
		
		
	}
	
	public void setStringListener(StringListener listener){
		
		this.textListener = listener;
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		JButton clicked = (JButton)e.getSource();
		if (clicked == select){
			
			if(textListener != null){
				
				
				// open a file
				int fileVal = chooser.showOpenDialog(this);
				
				if(fileVal == JFileChooser.APPROVE_OPTION)  {   
					file = chooser.getSelectedFile();    
				}

			
				// display the contant on the file 
				try {
					input = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}
				
				try {
					line = input.readLine();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				while(line != null){
				textListener.textEmitted1(line + "\n");
				//System.out.println(line + "\n");
				temp = line;
				  try {
					line = input.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				//final JFileChooser fc = new JFileChooser();
				
				//fc.showOpenDialog(this);
				
				//textListener.textEmitted("input file\n");
				
			}
		}
		if (clicked == format){
			
			if(textListener != null){
				
				
				
				
				//System.out.println(line);
				if ( temp != null ){
					try {
						output = new PrintWriter(new FileWriter("result.txt"));
						
						
						
						//output = new BufferedReader(new FileReader("result.txt"));
					} catch (FileNotFoundException e2) {
						
						e2.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					// counting how many words in the file
					try(Scanner sc = new Scanner(new FileInputStream(file))){    
					    count=0;
					    
					    while(sc.hasNext()){
					    	
					        sc.next();
					        
					        count++;
					    }
					   
					//System.out.println("Number of words: " + count);
					    // Display number of words in the app
					pannel.updateWordProcessedField (String.valueOf(count));
					
					FileReader fr = new FileReader(file);
	    		    LineNumberReader linenumber = new LineNumberReader(fr);


	    	            while (linenumber.readLine() != null){
	    	        	lines++;
	    	            }

	    	            //System.out.println("Total number of lines : " + lines);
	    	            
	    	            lp.updateLinesProcessedField(String.valueOf(lines));
	    	            
	    	            linenumber.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						input = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						line = input.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					String tempText = line;
					System.out.println(tempText);

					////////
					
					
					
					
				///////// count how many blank line 
					
					//final BufferedReader br = new BufferedReader(new FileReader(file));
					//String line;
					int empty = 0;
					try {
						while ((line = input.readLine()) != null) {
						  if (line.trim().isEmpty()) {
						    empty++;
						  }
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
    	            blankLines.updateBlankLineField(String.valueOf(empty));

					//System.out.println(empty);
					
					//System.out.println(tempText);

					
					///////////////////
					
					
					//////// print the text formatted after deleting the blank lines.
					try {
						input = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {

						e1.printStackTrace();
					}
					
					try {
						line = input.readLine();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					while(line != null){
						if (!line.isEmpty())
						   {
						      output.println(line);
						      
						      System.out.println(line);

						      textListener.textEmitted(line+"\n");
						     
						   }

					  try {
							line = input.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
				/////////////////////////////////////////////////////
					
				 

					
					
					
					
					
				}
				
				
				
			}
			
		}
		if (clicked == save){
			if(textListener != null){
				
			textListener.textEmitted("File Save\n");
				
			}
		}
		
	}

	public void setWord(String counter) {
	    this.counter = String.valueOf(count);
	}
	
	public void setPanel (FormPanel panel) {
		pannel = panel;
	}
	public void setLine (FormPanel lp){
		
		this.lp = lp;
		
	}
	public void setBlankline (FormPanel blankLines){
		
		this.blankLines = blankLines;
		
		
	}
}
