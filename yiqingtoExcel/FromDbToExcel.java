package yqToexcel;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



public class FromDbToExcel {
	ChartPanel frame1;
	public  FromDbToExcel(){
		CategoryDataset dataset = getDataSet();//����õ����ݴ��ݸ�CategoryDataset��Ķ���
       JFreeChart chart = ChartFactory.createBarChart3D(
      		                 "����ͳ�Ʊ�", // ͼ�����
                           "ͳ����Ŀ", // Ŀ¼�����ʾ��ǩ
                           "����", // ��ֵ�����ʾ��ǩ
                           dataset, // ���ݼ�
                           PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                           true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                           false,  // �Ƿ����ɹ���
                           false  // �Ƿ�����URL����
                           );
       CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
       CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�
        domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
        domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
        ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
         chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
         chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
         
         frame1=new ChartPanel(chart,true);  //����Ҳ������chartFrame,����ֱ������һ��������Frame
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private CategoryDataset getDataSet() {
		// TODO �Զ����ɵķ������
		int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;
        int count7=0;
        List<Yiq> list= YiqService.getAllByDb();
        for (int i = 0; i < list.size(); i++)
        {
        	Label wuhan_i= new Label(4, i+1, list.get(i).getWuhan()+"");
        	Label hubei_i= new Label(5, i+1, list.get(i).getHubei()+"");
        	Label wuhancontact_i= new Label(6, i+1, list.get(i).getWuhancontact()+"");
        	Label hubeicontact_i= new Label(7, i+1, list.get(i).getHubeicontact()+"");
        	Label back_i= new Label(8, i+1, list.get(i).getBack()+"");
        	Label confirm_i= new Label(9, i+1, list.get(i).getConfirm()+"");
        	Label state_i= new Label(10, i+1, list.get(i).getState()+"");
        	
        	if(wuhan_i.equals("��"))
        		count1++;
        	if(hubei_i.equals("��"))
        		count2++;
        	if(wuhancontact_i.equals("��"))
        		count3++;
        	if(hubeicontact_i.equals("��"))
        		count4++;
        	if(back_i.equals("��"))
        		count5++;
        	if(confirm_i.equals("��"))
        		count6++;
        	if(state_i.equals("�����"))
        		count7++;
        }
        count1=100;count2=342;count3=45;count4=65;count5=0;count6=120;count7=78;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(count1, "�Ƿ����人", "�Ƿ����人");
        dataset.addValue(count2, "�Ƿ�����������人��", "�Ƿ�����������人��");
        dataset.addValue(count3, "�Ƿ����人�Ӵ�ʷ", "�Ƿ����人�Ӵ�ʷ");
        dataset.addValue(count4, "�Ƿ��к����Ӵ�ʷ", "�Ƿ��к����Ӵ�ʷ");
        dataset.addValue(count5, "�Ƿ�У", "�Ƿ�У");
        dataset.addValue(count6, "�Ƿ������Ʋ�֢", "�Ƿ������Ʋ�֢");
        dataset.addValue(count7, "�Ƿ���ȷ�ϱ���Ⱦ", "�Ƿ���ȷ�ϱ���Ⱦ");
        return dataset;
		
	}
	
	public ChartPanel getChartPanel(){
		   return frame1;
	}

	public static void main(String[] args) {
		try {
			WritableWorkbook wwb = null;
			// ������д���Excel������
            String fileName = "F://test1//yiqing.xls";
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
          //��fileNameΪ�ļ���������һ��Workbook
            wwb = Workbook.createWorkbook(file);
            
         // ����������
            WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
            
          //��ѯ���ݿ������е�����
            List<Yiq> list= YiqService.getAllByDb();
            //Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
            Label Id= new Label(0, 0, "ѧ��");//��ʾ��
            Label name= new Label(1, 0, "����");
            Label college= new Label(2, 0, "�༶");
            Label class1= new Label(3, 0, "�༶");
            Label wuhan= new Label(4, 0, "�人��");
            Label hubei= new Label(5, 0, "������");
            Label wuhancontact= new Label(6, 0, "�人�Ӵ�ʷ");
            Label hubeicontact= new Label(7, 0, "�����Ӵ�ʷ");
            Label back= new Label(8, 0, "�Ƿ�У");
            Label confirm= new Label(9, 0, "����");
            Label state= new Label(10, 0, "ȷ��");
            
            ws.addCell(Id);
            ws.addCell(name);
            ws.addCell(college);
            ws.addCell(class1);
            ws.addCell(wuhan);
            ws.addCell(hubei);
            ws.addCell(wuhancontact);
            ws.addCell(hubeicontact);
            ws.addCell(back);
            ws.addCell(confirm);
            ws.addCell(state);
            
            
            
            for (int i = 0; i < list.size(); i++)
            {
            	
            	Label Id_i= new Label(0, i+1, list.get(i).getId()+"");
            	Label Name_i= new Label(1, i+1, list.get(i).getName1()+"");
            	Label college_i= new Label(2, i+1, list.get(i).getCollege()+"");
            	Label class1_i= new Label(3, i+1, list.get(i).getClass1()+"");
            	Label wuhan_i= new Label(4, i+1, list.get(i).getWuhan()+"");
            	Label hubei_i= new Label(5, i+1, list.get(i).getHubei()+"");
            	Label wuhancontact_i= new Label(6, i+1, list.get(i).getWuhancontact()+"");
            	Label hubeicontact_i= new Label(7, i+1, list.get(i).getHubeicontact()+"");
            	Label back_i= new Label(8, i+1, list.get(i).getBack()+"");
            	Label confirm_i= new Label(9, i+1, list.get(i).getConfirm()+"");
            	Label state_i= new Label(10, i+1, list.get(i).getState()+"");
            	
            	
            	
            	ws.addCell(Id_i);
            	ws.addCell(Name_i);
            	ws.addCell(college_i);
            	ws.addCell(class1_i);
            	ws.addCell(wuhan_i);
            	ws.addCell(hubei_i);
            	ws.addCell(wuhancontact_i);
            	ws.addCell(hubeicontact_i);
            	ws.addCell(back_i);
            	ws.addCell(confirm_i);
            	ws.addCell(state_i);
            }
          //д���ĵ�
            wwb.write();
           // �ر�Excel����������
            System.out.println("���ݵ����ɹ�!");
            wwb.close();
		}catch (Exception e) {
			 // TODO Auto-generated catch block
            e.printStackTrace();
		}
		JFrame frame=new JFrame("����ͳ�Ʊ�");
		frame.setLayout(new GridLayout(2,2,10,10));
		frame.add(new FromDbToExcel().getChartPanel());   //�������ͼ
		frame.setBounds(0, 0, 900, 800);
		frame.setVisible(true);
	}

}
