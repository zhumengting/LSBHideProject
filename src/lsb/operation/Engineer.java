package lsb.operation;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import lsb.UI.LSBHideUI;
import lsb.hide.HideMessage;
import lsb.hide.ReFindMessage;

public class Engineer implements ActionListener{
	    public LSBHideUI parent;
	    public JFileChooser chooser;
	    int number=0;
	    public String path="";
	    public String filename="";
	    public String filename2="";
	    private int numberToStore=0;
	    //initialize the Engineer
		public Engineer(LSBHideUI parent) {
			this.parent=parent;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
		}
		public void actionPerformed(ActionEvent e) {
			//read a picture from the computer
			if(e.getActionCommand().equals("Search")){
				int result = chooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
				filename = chooser.getSelectedFile().getPath();
				String imagename = filename.substring(filename.lastIndexOf("\\")+1);
				filename2="result/"+imagename;
				System.out.println(filename2);
				path=chooser.getSelectedFile().getAbsolutePath();
				try {
					Image image=ImageIO.read(new File(filename));
					parent.lblNewLabel.setIcon(new ImageIcon(image));
					parent.textField.setText("");
					parent.textField_1.setText("");
					parent.textField_2.setText("");
					parent.lblNewLabelR.setIcon(new ImageIcon());
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			//exit 
			}else if(e.getActionCommand().equals("Exit")){
				System.exit(0);
			//read the image data
			}else if(e.getActionCommand().equals("Read")){
				
				if(!filename.equals("")){
				//读取图片的类型
				File file = new File(filename);
				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
					fis.skip(2);
					int size=myReadInt(fis);
					// 得到图片的宽度和高度
					fis.skip(12);
					int with=myReadInt(fis);
					int hight=myReadInt(fis);
					System.out.println(size);
					System.out.println(with+"-"+hight);
					fis.skip(2);
					// 得到图片的类型 8---256灰度图；24---24真彩图
					int Type = myReadInt(fis);
					numberToStore=(size-54)/8/8-1;
//					fis.skip(24);再跳过24个字节到达图片的数据区
					//在第一格展示
					parent.textField.setText("可输入的最大字节数为："+Integer.toString(numberToStore));
					System.out.println(Type);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			//Insert image to it
			}else if(e.getActionCommand().equals("Insert")){
				HideMessage first = new HideMessage();
				first.setname(path,  filename2);
				int inputNum=parent.textField_1.getText().length();
				if(numberToStore==0) {
					JOptionPane.showMessageDialog(null, "你还没有读取图片信息哦~！", "【出错啦】", JOptionPane.ERROR_MESSAGE);
				}
				if(inputNum>numberToStore) {
					JOptionPane.showMessageDialog(null, "你输入的压缩数据已经超过最大值啦~！", "【出错啦】", JOptionPane.ERROR_MESSAGE);
					parent.textField_1.setText("");
				}else {
					String str=parent.textField_1.getText()+"#";					
					try {
						first.write(str);
						System.out.println("success!");
					} catch (Throwable e2) {
						System.out.println("error!");
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Image image=ImageIO.read(new File(filename2));
						parent.lblNewLabelR.setIcon(new ImageIcon(image));
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			//Save the picture
			}else if(e.getActionCommand().equals("Save")){
				int index = chooser.showSaveDialog(null);
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setDialogType(JFileChooser.SAVE_DIALOG);
                chooser.setMultiSelectionEnabled(false);
                chooser.setAcceptAllFileFilterUsed(false);
                if (index == JFileChooser.APPROVE_OPTION) {
                    String writePath = chooser.getSelectedFile()
                            .getAbsolutePath() + "\\";
                    //filename 现为原图，建议定义一个temp图片绝对路径便于复制
                    if(copyFile(filename2, writePath)== -1){//the original file is not exist
                        JOptionPane.showMessageDialog(null, "Original file is not exist", "警告", JOptionPane.ERROR_MESSAGE);
                    }
                }
			//read the hiding data out
			}else{
				String result;
				ReFindMessage FindMessage = new ReFindMessage();
				try {
					result=FindMessage.getHideMessage(path);
					parent.textField_2.setText("隐写的消息为:"+result);
					System.out.println("隐写的消息为:"+result);
				} catch (IOException e2) {
					System.out.println("error!");
					e2.printStackTrace();
				}
			}
		}
		
		//读取integer值
		 public int myReadInt(FileInputStream ips) throws IOException {  
		        int t1 = ips.read() & 0xff;  
		        int t2 = ips.read() & 0xff;  
		        int t3 = ips.read() & 0xff;  
		        int t4 = ips.read() & 0xff;  
		        int num = (t4 << 24) + (t3 << 16) + (t2 << 8) + t1;  
		        return num;  
		    }  
		 public int copyFile(String oldPath, String newPath) {
		        try {
		            int byteread = 0;
		            File oldfile = new File(oldPath);
		            if (oldfile.exists()) { // if the file exist
		                InputStream inStream = new FileInputStream(oldPath); // read the origin file
		                System.out.println(newPath);
		                if(isExist(newPath)){
		                    FileOutputStream fs = new FileOutputStream(newPath);
		                    byte[] buffer = new byte[1444];
		                    while ((byteread = inStream.read(buffer)) != -1) {
		                        fs.write(buffer, 0, byteread);
		                    }
		                    inStream.close();
		                    fs.close();
		                    return 0;
		                }else{
		                	inStream.close();
		                    return -2;
		                }
		            }
		            return -1;
		        } catch (Exception e) {
		            System.out.println("复制单个文件操作出错");
		            e.printStackTrace();
		            return -2;
		        }
		    }
		 
		    public static boolean isExist(String filePath) {
		        String paths[] = filePath.split("\\\\");
		        String dir = paths[0];
		        for (int i = 0; i < paths.length - 2; i++) {
		            try {
		                dir = dir + "/" + paths[i + 1];
		                File dirFile = new File(dir);
		                if (!dirFile.exists()) {
		                    dirFile.mkdir();
		                    System.out.println("创建目录为：" + dir);
		                }
		            } catch (Exception err) {
		                System.err.println("ELS - Chart : 文件夹创建发生异常");
		            }
		        }
		        File fp = new File(filePath);
		        if (!fp.exists()) {
		            return true; // the file is not exist
		        } else {
		            return false; // the file is exist
		        }
		    }
}
