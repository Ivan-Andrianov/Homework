import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PracticalTaskWindow extends JPanel {

    static JTextArea values;

    public PracticalTaskWindow(Diagram diagram, MouseAdapter mouseAdapter){
        BoxLayout bl = new BoxLayout(this,BoxLayout.X_AXIS);
        this.setLayout(bl);
        this.setBorder(new LineBorder(new Color(41, 105, 2),4));

        SpringLayout sl = new SpringLayout();


        JPanel data = new JPanel(sl);
        data.setMaximumSize(new Dimension(495,1080));
        data.setMinimumSize(new Dimension(495,1080));
        data.setPreferredSize(new Dimension(495,1080));
        data.setBackground(new Color(118, 184, 77));


        values = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(values);
        scrollPane.getHorizontalScrollBar().setBackground(new Color(41, 105, 2));
        scrollPane.setPreferredSize(new Dimension(440,400));
        scrollPane.setBorder(new LineBorder(new Color(41, 105, 2),3));
        values.setBackground(new Color(205, 247, 178));
        values.setEditable(false);
        values.setFont(new Font(null,0,20));
        data.add(scrollPane);
        sl.putConstraint(SpringLayout.NORTH,scrollPane,200,SpringLayout.NORTH,data);
        sl.putConstraint(SpringLayout.WEST,scrollPane,22,SpringLayout.WEST,data);



        JPanel buttons = new JPanel(new GridLayout(4,1,70,0));
        buttons.setOpaque(false);
        JButton generateData = new MenuButton("Генерация данных",mouseAdapter);
        SetScopeListener scopeListener = new SetScopeListener();
        JButton plusScope = new MenuButton("Увеличить масштаб",scopeListener);
        JButton minusScope = new MenuButton("Уменьшить масштаб",scopeListener);
        buttons.add(generateData);
        buttons.add(plusScope);
        buttons.add(minusScope);
        data.add(buttons);
        sl.putConstraint(SpringLayout.WEST,buttons,73,SpringLayout.WEST,data);
        sl.putConstraint(SpringLayout.NORTH,buttons,630,SpringLayout.NORTH,data);


        JPanel separator = new JPanel();
        separator.setBackground(new Color(41, 105, 2));
        separator.setMaximumSize(new Dimension(5,3000));
        separator.setMinimumSize(new Dimension(5,3000));
        separator.setPreferredSize(new Dimension(5,3000));


        JPanel diagramContainer = new JPanel(null);
        diagramContainer.setMaximumSize(new Dimension(1420,3000));
        diagramContainer.setMinimumSize(new Dimension(1420,3000));
        diagramContainer.setPreferredSize(new Dimension(1420,3000));
        diagramContainer.setBackground(Color.blue);
        diagramContainer.setLayout(null);
        diagramContainer.setBackground(new Color(205, 247, 178));




        diagramContainer.add(diagram);



        this.add(data);
        this.add(separator);
        this.add(diagramContainer);

    }

    public static class MyMouseListener1 extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            FirstPracticalTask object = new FirstPracticalTask();
            values.setText(String.format("Среднее арифметическое: %.4f; \n"+
                    "Дисперсия: %.4f; \n"+
                    "Стандартное отклонение: %.4f; \n"+
                    "Среднее арифметическое - Стандартное отклонение: %.4f; \n"+
                    "Среднее арифметическое + Стандартное отклонение: %.4f; \n" +
                    "Данные: "+object.data+"\n",object.averageValue,object.variance,object.standardDeviation,object.averageValueMinusStandardDeviation,object.averageValuePlusStandardDeviation));
            Diagram.diagram.repaint();
        }
    }

    public static class MyMouseListener2 extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            SecondPracticalTask object = new SecondPracticalTask();
            values.setText(String.format("Среднее арифметическое: %.4f; \n"+
                    "Дисперсия: %.4f; \n"+
                    "Стандартное отклонение: %.4f; \n"+
                    "Среднее арифметическое - Стандартное отклонение: %.4f; \n"+
                    "Среднее арифметическое + Стандартное отклонение: %.4f; \n" +
                    "Данные о зарплате: "+object.salary+"\n",object.averageValue,object.variance,object.standardDeviation,object.averageValueMinusStandardDeviation,object.averageValuePlusStandardDeviation));
            Diagram.diagram.repaint();
        }
    }

    private class MenuButton extends JButton{
        public MenuButton(String text,MouseAdapter mouseAdapter){
            this.setPreferredSize(new Dimension(349,60));
            this.setFocusPainted(false);
            this.setBorder(new LineBorder(new Color(41, 105, 2),3));
            this.setBackground(new Color(205, 247, 178));
            this.addMouseListener(mouseAdapter);
            this.setText(text);
        }
    }

    private class SetScopeListener extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Увеличить масштаб")){
                if (Diagram.diagram.measure<=190) Diagram.diagram.measure += 5;
            }else if (button.getText().equals("Уменьшить масштаб")){
                if (Diagram.diagram.measure>5) Diagram.diagram.measure-=5;
            }
            Diagram.diagram.repaint();
        }

    }
}
