import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    static MainFrame frame;
    public MainFrame(){
        super("Ivan Andrianov");
        Container contentPane =  this.getContentPane();
        BoxLayout bl = new BoxLayout(contentPane,BoxLayout.X_AXIS);
        contentPane.setBackground(new Color(32, 140, 75));
        contentPane.setLayout(bl);
        this.add(Box.createHorizontalStrut(100));

        SpringLayout sl = new SpringLayout();
        JPanel main = new JPanel();
        main.setLayout(sl);

        JTextField field = new JTextField("Обработка данных");
        main.add(field);
        sl.putConstraint(SpringLayout.WEST,field,70,SpringLayout.WEST,contentPane);
        field.setForeground(new Color(32, 140, 75));
        field.setBorder(null);
        field.setEditable(false);
        field.setFont(new Font(null,0,50));
        contentPane.add(main);

        JPanel buttonsContainer = new JPanel(new GridLayout(6,1,0,4));

        MenuButton button1 = new MenuButton("Практическое задание 1");
        button1.addMouseListener(new MenuMouseListener());
        MenuButton button2 = new MenuButton("Практическое задание 2");
        button2.addMouseListener(new MenuMouseListener());
        MenuButton button3 = new MenuButton("Практическое задание 3");
        MenuButton button4 = new MenuButton("Практическое задание 4");
        MenuButton button5 = new MenuButton("Практическое задание 5");
        MenuButton button6 = new MenuButton("Практическое задание 6");

        buttonsContainer.add(button1);
        buttonsContainer.add(button2);
        buttonsContainer.add(button3);
        buttonsContainer.add(button4);
        buttonsContainer.add(button5);
        buttonsContainer.add(button6);


        main.add(buttonsContainer);
        sl.putConstraint(SpringLayout.WEST,buttonsContainer,150,SpringLayout.WEST,contentPane);
        sl.putConstraint(SpringLayout.NORTH,buttonsContainer,200,SpringLayout.NORTH,contentPane);
        this.add(Box.createHorizontalStrut(100));
        buttonsContainer.setPreferredSize(new Dimension(300,270));


        setLocation(560,140);
        setSize(800,800);
        setResizable(false);
        setVisible(true);
        frame = this;
    }

    private class MenuButton extends JButton{
        public MenuButton(String text){
            super(text);
            setFocusPainted(false);
            setBackground(new Color(211, 222, 215));
            setBorder(new LineBorder(new Color(40, 201, 105),3));
            setForeground(Color.BLACK);
            setFocusPainted(false);
        }
    }
    private class MenuMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            button.setBorder(new LineBorder(Color.YELLOW, 3));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            frame.setResizable(true);
            frame.setLocation(0, 0);
            frame.setExtendedState(MAXIMIZED_BOTH);
            if (((JButton) e.getSource()).getText().equals("Практическое задание 1")) {
                frame.setContentPane(new PracticalTaskWindow(new Diagram(170,FirstPracticalTask.functions,"Y","X"),new PracticalTaskWindow.MyMouseListener1()));
            }else if ((((JButton) e.getSource()).getText().equals("Практическое задание 2"))){
                frame.setContentPane(new PracticalTaskWindow(new Diagram(5,SecondPracticalTask.functions,"Y","X"),new PracticalTaskWindow.MyMouseListener2()));
            }
            frame.repaint();
        }
    }
}
