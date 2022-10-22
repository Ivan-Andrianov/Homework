import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.function.Function;

public class Diagram extends JComponent {

    public int xPerUnit;
    public static Diagram diagram;
    static Color[] colors = new Color[]{Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.cyan,Color.BLUE,Color.PINK};
    private int index = 0;
    int measure;
    private String xAxisName;
    private String yAxisName;
    List<Function<Double, Double>> functions;

    public Diagram(int measure, List<Function<Double, Double>> functions,String xAxisName,String yAxisName){
        diagram = this;
        this.functions = functions;
        this.measure = measure;
        this.setBorder(new LineBorder(new Color(41, 105, 2)));
        this.setBounds(50,50,1320,920);
        this.xAxisName = xAxisName;
        this.yAxisName = yAxisName;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1320,920);
        g.setColor(Color.black);

        g.fillRect(20,20,5,880);
        g.fillRect(20,895,1280,5);

        g.setColor(Color.RED);
        g.drawString("0",6,910);


        for (int i=895-(measure+2);i>measure+20;i-=(measure+2)){
            g.setColor(Color.LIGHT_GRAY);
            if (measure<30){
                if ((895-i)/(measure+2)==1 || (895-i)/(measure+2)%5==0){
                    g.fillRect(18,i,9,2);
                    g.setColor(Color.RED);
                    g.drawString(String.format("%3s",(895-i)/(measure+2)),0,i-2);
                }
                continue;
            }

            g.fillRect(18,i,9,2);
            g.setColor(Color.RED);
            g.drawString(String.format("%3s",(895-i)/(measure+2)),0,i-2);
        }
        System.out.println(measure);
        g.setColor(Color.LIGHT_GRAY);

        for (int i=27+measure;i<1300-measure;i+=(measure+2)){
            g.setColor(Color.LIGHT_GRAY);
            if (measure<30){
                if ((i-25)/(measure+2)==1 || (i-25)/(measure+2)%5==0){
                    g.fillRect(i-2,893,2,9);
                    g.setColor(Color.RED);
                    g.drawString(String.format("%3s",(i-25)/(measure+2)),i-7,913);
                }
                continue;
            }
            g.fillRect(i-2,893,2,9);
            g.setColor(Color.RED);
            g.drawString(String.format("%3s",(i-25)/(measure+2)),i-7,913);
        }

        g.drawString(xAxisName,26,20);

        if (FirstPracticalTask.task!=null) paintGraphics1(g);
        if (SecondPracticalTask.task!=null) paintGraphics2(g);
    }

    public void paintGraphics1(Graphics g){
        for (Function<Double, Double> function : functions) {
            g.setColor(colors[index++]);
            for (int x = 1; x <= 1278; x++) {
                g.fillOval(x + 18, 894-(int) (Math.round(measure * function.apply((double) x))), 5, 5);
            }
        }
        index = 0;
    }

    public void paintGraphics2(Graphics g){
        for (int i=0;i<3;i++){
            g.setColor(colors[index]);
            for (int x=1;x<=1278;x++){
                g.fillOval(x+18,894 - (int) (Math.round((measure+2) * functions.get(i).apply((double) x))),5,5);
            }
            index+=1;
        }
        g.setColor(Color.BLUE);
        for (int x=25;x<=1278;x+=measure+2){
            if (x==25){
                continue;
            }
            if ((x-25)/(measure+2)>=149) break;
            g.drawLine(x-measure-2,894 - (int) Math.round((measure+2)*functions.get(3).apply((double) (((x-25)/(measure+2))-1))/1000),x,894 - (int) Math.round((measure+2)*functions.get(3).apply((double) ((x-25)/(measure+2)))/1000));
            g.fillOval(x-measure-5,894 - (int) Math.round((measure+2)*functions.get(3).apply((double) (((x-25)/(measure+2))-1))/1000),6,6);
        }
        index = 0;
    }
}