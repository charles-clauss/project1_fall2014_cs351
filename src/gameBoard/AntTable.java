package gameBoard;

import java.awt.BorderLayout;
import javax.swing.JFrame;
// import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controller.AntController;

/**
 * Class that helps visualize our ant's information. Not working correctly.
 */
public class AntTable extends JFrame
{
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  private AntTableModel model = new AntTableModel();

  private JTable table;

  public AntTable(AntController ac)
  {
    super();
    model.getAntList(ac);
    setTitle("Ants!");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    table = new JTable(model);
    getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    // JPanel buttons = new JPanel();
    // /buttons.add(new JButton(new AddAction()));
    // buttons.add(new JButton(new RemoveAction()));
    // getContentPane().add(buttons, BorderLayout.SOUTH);
    pack();
  }

  // public static void main(String[] args) {
  // new AntTable().setVisible(true);
  // }

}