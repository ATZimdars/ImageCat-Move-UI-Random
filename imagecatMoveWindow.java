import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.*;

public class imagecatMoveWindow
{
	public static int after1 = 0;
	public static boolean canWork = false;
	//Json File Location	
	public static File jsonIn;
//Index File Location	
	public static File indexIn;
	public static JLabel one = new JLabel("[ JSON ]");
	public static JLabel two = new JLabel("[ INDEX ]");
	public static JTextField three = new JTextField("");
	public static JTextField four = new JTextField("");
	public static JButton done = new JButton("Start");
	public static JLabel after = new JLabel("[----------]");
	public static JFrame base = new JFrame("Imagecat Prowl");
	public static JPanel bottom = new JPanel();
	public static JPanel top = new JPanel();
	public static JPanel middle = new JPanel();
	public static JLabel info = new JLabel("*check boxes for master .txt for multiple transfers");
	public static void main(String[]args) throws IOException
	{	
		base.setPreferredSize(new Dimension (400,125));
		base.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});	
		base.setLayout(new GridLayout(3,1));
		top.setLayout(new GridLayout(2,2));
		middle.setLayout(new GridLayout(2,1));
		bottom.setLayout(new GridLayout(1,1));
		base.getContentPane().add(top);
		top.add(one);
		top.add(three);
		top.add(two);
		top.add(four);
		base.getContentPane().add(middle);
		middle.add(done);
		middle.add(after);
		base.getContentPane().add(bottom);
		bottom.add(info);
		one.setHorizontalAlignment(JLabel.CENTER);
		one.setForeground(Color.BLUE);
		two.setHorizontalAlignment(JLabel.CENTER);
		two.setForeground(Color.RED);
		three.setHorizontalAlignment(JTextField.CENTER);
		four.setHorizontalAlignment(JTextField.CENTER);
		done.setHorizontalAlignment(JButton.CENTER);
		after.setHorizontalAlignment(JLabel.CENTER);
		base.pack();
		base.setVisible(true);
		done.addActionListener(new ActionListener()
		{	
			public void actionPerformed(ActionEvent e) 
			{
				canWork = true;
				while(canWork == true)
				{
					jsonIn = new File(setJSON());
					indexIn = new File(setIndex());
					try
					{
						runner(jsonIn, indexIn);
					}
					catch (IOException ex)
					{
						System.out.println(ex);
					}
				}
			}
		});
	}	
			
	public static void runner(File jsonIn1, File indexIn1) throws IOException
	{
		if (checkJson(jsonIn1) == 1 && checkIndex(indexIn1) == 1)
		{
			copyFile(jsonIn1, indexIn1);
			changeStatus(1);
			canWork = false;
		}
		else
		{
			changeStatus(2);
			canWork = false;
		}
	}
	public static void copyFile(File json,File index) throws IOException
	{
		InputStream input = null;
		OutputStream output = null;
		try 
		{
			input = new FileInputStream(json);
			output = new FileOutputStream(index,true);
			byte[] buf = new byte[1024];
			int length;
			while ((length = input.read(buf)) > 0)
			{
				output.write(buf,0, length);
			}
		} 
		finally 
		{
			input.close();
			output.close();
		}
	}
	public static int checkJson(File jsonCheck)
	{
		if (jsonCheck.isFile() == true)
		{
			return 1;
		}
		else
		{
			return 2;
		}
		
	}
	public static int checkIndex(File indexCheck)
	{
		if (indexCheck.isFile() == true)
		{
			
			return 1;
		}
		else
		{
			return 2;
		}
	}
	public static String setJSON()
	{
		String jsonLoc = three.getText();
		return jsonLoc;
	}
	public static String setIndex()
	{
		String indexLoc = four.getText();
		return indexLoc;
	}
	public static void changeStatus(int option)
	{
		if(option == 1)
		{
			after.setText("[ Done ]");
			after.setForeground(Color.GREEN);
			base.repaint();
		}
		if(option == 2)
		{
			after.setText("----! Error !----");
			after.setForeground(Color.RED);
			base.repaint();
		}
	}

}

