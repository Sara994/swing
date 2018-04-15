package nora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class BoardPanel extends JPanel {
    GridBagLayout gridLayout;

    /**
     * Creates new form BoardPanel
     */
    public BoardPanel() {
        gridLayout = new GridBagLayout();
        
        setLayout(new GridBagLayout());
        
    }
    
    private void setupTitlePane(String heading, String text){
        GridBagConstraints h = new GridBagConstraints();
        h.gridx = 0;
        h.gridy = 0;
        h.ipady = 40;
        h.fill = GridBagConstraints.HORIZONTAL;
        JPanel headingPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(heading);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingPanel.add(titleLabel,BorderLayout.WEST);
        
        add(headingPanel,h);
        
        GridBagConstraints gbl = new GridBagConstraints();
        gbl.gridx = 0;
        gbl.gridy = 1;
        gbl.ipady = 40;
        gbl.fill = GridBagConstraints.HORIZONTAL;
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new LineBorder(Color.black));
        titlePanel.add(new JLabel(text));
        
        add(titlePanel,gbl);
        
    }
    
    private GridBagConstraints fullWidthConstraint(){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        return gridBagConstraints;
    }
    
    public void setTutorial(Tutorial tutorial){
        removeAll();
        setupTitlePane("Tutorial",tutorial.getTitle());
        
        
        GridBagConstraints c = new GridBagConstraints();
        JPanel contentPanel = new JPanel(new BorderLayout());
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;      //make this component tall
        c.weightx = 10;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 2;
        JTextArea textArea = new JTextArea(tutorial.getCode());
        textArea.setEditable(false);
        contentPanel.add(textArea);
        add(contentPanel,c);        
    }
    
    public void setQuestion(Question question){
        removeAll();
        setupTitlePane("Question",question.getQuestion());
        
        JPanel answersPanel = new JPanel();
        
        for(int i = 0; i < question.getAnswers().size();i++){
            Answer answer = question.getAnswers().get(i);
            JPanel qPanel = new JPanel(new BorderLayout());
            JTextArea textArea = new JTextArea(answer.getAnswer());
            qPanel.add(textArea);
            textArea.setEditable(false);
            textArea.setBackground(Color.GRAY);
            textArea.setForeground(Color.WHITE);
            
            answersPanel.add(qPanel,BorderLayout.CENTER);
        }
        
        
        GridBagConstraints c = new GridBagConstraints();
        //JPanel contentPanel = new JPanel(new BorderLayout());
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;
        c.weightx = 10;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        
        JScrollPane jScrollPane = new JScrollPane(answersPanel);
        
        add(jScrollPane,c);
               
    }
}