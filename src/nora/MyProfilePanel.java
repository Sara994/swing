/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nora;

import java.util.Date;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class MyProfilePanel extends javax.swing.JPanel {
    private TutorialSelectionChangeListener tutorialSelectionChangeListener; 
    private QuestionSelectionChangeListener questionSelectionChangeListener; 
    private AnswerSelectionChangeListener answerSelectionChangeListener; 
    
    private List<Tutorial> allTutorials;
    private List<Question> allQuestions;
    private List<Answer> allAnswers;
    /**
     * Creates new form MyProfilePanel
     */
    public MyProfilePanel() {
        initComponents();
        fillAllTables();
        setSelectionListeners();
    }
    public void fillAllTables(){
        fillAnswersTable();
        fillQuestionsTable();
        fillTutorialsTable();
    }
    
    private void setSelectionListeners(){
        table2Tutorials.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting())
                    return;
                
                int selectedIndex = ((DefaultListSelectionModel) e.getSource()).getAnchorSelectionIndex();
                if(selectedIndex < 0)
                    return;
                Tutorial selectedTutorial = allTutorials.get(selectedIndex);
                if(tutorialSelectionChangeListener != null){
                    tutorialSelectionChangeListener.selectionChanged(selectedTutorial);
                }
            }
        });
        
        tableQuestions.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting())
                    return;
                
                int selectedIndex = ((DefaultListSelectionModel) e.getSource()).getAnchorSelectionIndex();
                if(selectedIndex < 0)
                    return;
                Question selectedQuestion = allQuestions.get(selectedIndex);
                
                System.err.println("Heloo sdf sdf s" + selectedQuestion.getAnswers().size());
                if(questionSelectionChangeListener != null){
                    questionSelectionChangeListener.selectionChanged(selectedQuestion);
                }
            }
        });
        
        tableAnswers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting())
                    return;
                
                int selectedIndex = ((DefaultListSelectionModel) e.getSource()).getAnchorSelectionIndex();
                if(selectedIndex < 0)
                    return;
                Answer selectedAnswer = allAnswers.get(selectedIndex);
                if(answerSelectionChangeListener != null){
                    answerSelectionChangeListener.selectionChanged(selectedAnswer);
                }
            }
        });
        
    }
    
    public void fillTutorialsTable(){
        int COLUMN_COUNT = 5;
        DB db = new DB();
        allTutorials = db.getAllMyTutorials();
        Object[][] rows = new Object[allTutorials.size()][COLUMN_COUNT];
        
        for(int i =0;i< allTutorials.size();i++){
            Object[] row = new Object[COLUMN_COUNT];
            row[0] = allTutorials.get(i).getId();
            row[1] = allTutorials.get(i).getTitle();
            row[2] = db.getUsername(allTutorials.get(i).getUser_id());
            row[3] = allTutorials.get(i).getLanguage();
            row[4] = new Date(allTutorials.get(i).getCreatedAt().getTime());
            rows[i] = row;
        }
        
        String[] titles = {"Id","Title","User","Language","Date"};
        
        table2Tutorials.setModel(new javax.swing.table.DefaultTableModel(
            rows,
            titles
        ));
        table2Tutorials.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public void fillQuestionsTable(){
        int COLUMN_COUNT = 5;
        DB db = new DB();
        allQuestions = db.getAllMyQuestions();
        Object[][] rows = new Object[allQuestions.size()][COLUMN_COUNT];
        
        for(int i =0;i< allQuestions.size();i++){
            Object[] row = new Object[COLUMN_COUNT];
            row[0] = allQuestions.get(i).getId();
            row[1] = allQuestions.get(i).getTitle();
            row[2] = db.getUsername(allQuestions.get(i).getUserId());
            row[3] = db.getAllAnswersForQuestion(allQuestions.get(i).getId()).size();
            row[4] = new Date(allQuestions.get(i).getCreatedAt().getTime());
            
            rows[i] = row;
        }
        
        String[] titles = {"Id","Title","User","Number Of Answers","Date"};
        
        tableQuestions.setModel(new javax.swing.table.DefaultTableModel(
            rows,
            titles
        ));
        tableQuestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public void fillAnswersTable(){
        int COLUMN_COUNT = 3;
        DB db = new DB();
        allAnswers = db.getAllMyAnswers();
        Object[][] rows = new Object[allAnswers.size()][COLUMN_COUNT];
        
        for(int i =0;i< allAnswers.size();i++){
            Object[] row = new Object[COLUMN_COUNT];
            row[0] = allAnswers.get(i).getId();
            row[1] = db.getQuestionForAnswer(allAnswers.get(i)).getTitle();
            row[2] = new Date(allAnswers.get(i).getCreatedAt().getTime());
            rows[i] = row;
        }
        
        String[] titles = {"Id","Question Title","Date"};
        
        tableAnswers.setModel(new javax.swing.table.DefaultTableModel(
            rows,
            titles
        ));
        tableAnswers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table2Tutorials = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableQuestions = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAnswers = new javax.swing.JTable();

        table2Tutorials.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table2Tutorials);

        tableQuestions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableQuestions);

        jLabel1.setText("My Tutorials");

        jLabel2.setText("My Questions");

        jLabel3.setText("My Answers");

        tableAnswers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableAnswers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable table2Tutorials;
    private javax.swing.JTable tableAnswers;
    private javax.swing.JTable tableQuestions;
    // End of variables declaration//GEN-END:variables
    
    public void setTutorialSelectionChangeListener(TutorialSelectionChangeListener listener){
        this.tutorialSelectionChangeListener = listener;
    }

    public void setQuestionSelectionChangeListener(QuestionSelectionChangeListener questionSelectionChangeListener) {
        this.questionSelectionChangeListener = questionSelectionChangeListener;
    }

    public void setAnswerSelectionChangeListener(AnswerSelectionChangeListener answerSelectionChangeListener) {
        this.answerSelectionChangeListener = answerSelectionChangeListener;
    }
    
    public interface TutorialSelectionChangeListener{
        public void selectionChanged(Tutorial t);
    }
    public interface AnswerSelectionChangeListener{
        public void selectionChanged(Answer t);
    }
    public interface QuestionSelectionChangeListener{
        public void selectionChanged(Question t);
    }
}
