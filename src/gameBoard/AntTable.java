package gameBoard;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.Ant;
import controller.BasicAnt;

public class AntTable extends JFrame {
  private AntTableModel model = new AntTableModel();

  private JTable table;

  public AntTable() {
      super();

      setTitle("Ants!");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      table = new JTable(model);

      getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

      JPanel buttons = new JPanel();

      ///buttons.add(new JButton(new AddAction()));
      //buttons.add(new JButton(new RemoveAction()));

      getContentPane().add(buttons, BorderLayout.SOUTH);

      pack();
  }

  public static void main(String[] args) {
      new AntTable().setVisible(true);
  }

  private class AddAction extends AbstractAction {
      private AddAction() {
          super("Add");
      }

      public void actionPerformed(ActionEvent e) {
        Ant ant = new BasicAnt(null);
          model.addAnt(ant);
      }
  }

  
}