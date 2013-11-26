/*
 * QAiiiTMView.java
 */

package qaiiitm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.Task;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.lang.Object;
import info.ephyra.*;
import info.ephyra.querygeneration.Query;
import info.ephyra.questionanalysis.AnalyzedQuestion;
import info.ephyra.questionanalysis.QuestionInterpretation;
import info.ephyra.search.Result;
import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import speechOn.Microphone;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.IconUIResource;
import org.alicebot.ab.AB.*;
import org.alicebot.ab.Chat;
import jortho.com.inet.jortho.SpellChecker;
import speechOn.GoogleResponse;
import speechOn.Recognizer;
import voce.SpeechInterface;

/**
 * The application's main frame.
 */
public class QAiiiTMView extends FrameView {

    SpeechInterface syn = new SpeechInterface();   // Speech Interface Object for Synthesizing the Voice Output
    SpeechInterface rec = new SpeechInterface();   // Speech Interface Object for Listening the Input
    String currentRootDirectoryPath;               //   Finds out the current directory where the project build is stored
    Microphone mic = new Microphone(Type.WAVE);    // Microphone Object for Google Speech API (Online)
    public static boolean disableLoad = false;     // It is used to toggle the load Button
    public static boolean disableListen = false;   // It is used to toggle listening Mode
    public static boolean[] modes = {false,false,false,false,false}; // {chat,news,think,note,setings};

    AB.Main abhi = null;        // AIML Bot that parses commands


    public QAiiiTMView(SingleFrameApplication app) {
        super(app);
        File currentJavaJarFile = new File(QAiiiTMView.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String currentJavaJarFilePath = currentJavaJarFile.getAbsolutePath();
        currentRootDirectoryPath = currentJavaJarFilePath.replace(currentJavaJarFile.getName(), "");
        initComponents();
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }


    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = QAiiiTMApp.getApplication().getMainFrame();
            aboutBox = new QAiiiTMAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        QAiiiTMApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        chatButton = new javax.swing.JLabel();
        loadAllButton = new javax.swing.JLabel();
        speakButton = new javax.swing.JLabel();
        newsButton = new javax.swing.JLabel();
        settingsButton = new javax.swing.JLabel();
        thinkButton = new javax.swing.JLabel();
        noteButton = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        speakStop = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        answerPatternTextBox = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        answerTypeTextBox = new javax.swing.JTextArea();
        solveButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        queryStringTextBox = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        newsTextBox = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        interpretationsTextBox = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        answerTextBox = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        questionTextBox = new javax.swing.JTextArea();
        loadButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        chatQuestion = new javax.swing.JTextField();
        chatResult = new javax.swing.JTextField();
        processButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qaiiitm.QAiiiTMApp.class).getContext().getResourceMap(QAiiiTMView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mainPanel.setForeground(resourceMap.getColor("mainPanel.foreground")); // NOI18N
        mainPanel.setName("mainPanel"); // NOI18N

        jToolBar1.setBackground(resourceMap.getColor("abhisharToollbar.background")); // NOI18N
        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);
        jToolBar1.setName("abhisharToollbar"); // NOI18N

        jLabel1.setIcon(resourceMap.getIcon("logo.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setName("jLabel1"); // NOI18N
        jToolBar1.add(jLabel1);

        jLayeredPane3.setBackground(resourceMap.getColor("jLayeredPane3.background")); // NOI18N
        jLayeredPane3.setName("jLayeredPane3"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(180, 20, 610, 58);
        jLayeredPane3.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar1.add(jLayeredPane3);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jToolBar2.setBackground(resourceMap.getColor("jToolBar2.background")); // NOI18N
        jToolBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar2.setRollover(true);
        jToolBar2.setMinimumSize(new java.awt.Dimension(120, 128));
        jToolBar2.setName("jToolBar2"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton1);

        jLayeredPane2.setName("jLayeredPane2"); // NOI18N

        chatButton.setIcon(resourceMap.getIcon("chatButton.icon")); // NOI18N
        chatButton.setText(resourceMap.getString("chatButton.text")); // NOI18N
        chatButton.setToolTipText(resourceMap.getString("chatButton.toolTipText")); // NOI18N
        chatButton.setEnabled(false);
        chatButton.setName("chatButton"); // NOI18N
        chatButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatButtonMouseClicked(evt);
            }
        });
        chatButton.setBounds(0, 10, 128, 100);
        jLayeredPane2.add(chatButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        loadAllButton.setIcon(resourceMap.getIcon("loadAllButton.icon")); // NOI18N
        loadAllButton.setText(resourceMap.getString("loadAllButton.text")); // NOI18N
        loadAllButton.setToolTipText(resourceMap.getString("loadAllButton.toolTipText")); // NOI18N
        loadAllButton.setName("loadAllButton"); // NOI18N
        loadAllButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadAllButtonMouseClicked(evt);
            }
        });
        loadAllButton.setBounds(410, 10, 128, 100);
        jLayeredPane2.add(loadAllButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        speakButton.setIcon(resourceMap.getIcon("speakButton.icon")); // NOI18N
        speakButton.setText(resourceMap.getString("speakButton.text")); // NOI18N
        speakButton.setToolTipText(resourceMap.getString("speakButton.toolTipText")); // NOI18N
        speakButton.setEnabled(false);
        speakButton.setName("speakButton"); // NOI18N
        speakButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                speakButtonMouseClicked(evt);
            }
        });
        speakButton.setBounds(540, 5, 128, 110);
        jLayeredPane2.add(speakButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        newsButton.setIcon(resourceMap.getIcon("newsButton.icon")); // NOI18N
        newsButton.setText(resourceMap.getString("newsButton.text")); // NOI18N
        newsButton.setToolTipText(resourceMap.getString("newsButton.toolTipText")); // NOI18N
        newsButton.setEnabled(false);
        newsButton.setName("newsButton"); // NOI18N
        newsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newsButtonMouseClicked(evt);
            }
        });
        newsButton.setBounds(150, 10, 128, 100);
        jLayeredPane2.add(newsButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        settingsButton.setIcon(resourceMap.getIcon("settingsButton.icon")); // NOI18N
        settingsButton.setText(resourceMap.getString("settingsButton.text")); // NOI18N
        settingsButton.setToolTipText(resourceMap.getString("settingsButton.toolTipText")); // NOI18N
        settingsButton.setEnabled(false);
        settingsButton.setName("settingsButton"); // NOI18N
        settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsButtonMouseClicked(evt);
            }
        });
        settingsButton.setBounds(790, 10, 128, 100);
        jLayeredPane2.add(settingsButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        thinkButton.setIcon(resourceMap.getIcon("thinkButton.icon")); // NOI18N
        thinkButton.setText(resourceMap.getString("thinkButton.text")); // NOI18N
        thinkButton.setToolTipText(resourceMap.getString("thinkButton.toolTipText")); // NOI18N
        thinkButton.setEnabled(false);
        thinkButton.setName("thinkButton"); // NOI18N
        thinkButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thinkButtonMouseClicked(evt);
            }
        });
        thinkButton.setBounds(290, 10, 128, 100);
        jLayeredPane2.add(thinkButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        noteButton.setIcon(resourceMap.getIcon("noteButton.icon")); // NOI18N
        noteButton.setText(resourceMap.getString("noteButton.text")); // NOI18N
        noteButton.setToolTipText(resourceMap.getString("noteButton.toolTipText")); // NOI18N
        noteButton.setEnabled(false);
        noteButton.setName("noteButton"); // NOI18N
        noteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noteButtonMouseClicked(evt);
            }
        });
        noteButton.setBounds(680, 10, 110, 100);
        jLayeredPane2.add(noteButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar2.add(jLayeredPane2);

        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(qaiiitm.QAiiiTMApp.class).getContext().getActionMap(QAiiiTMView.class, this);
        speakStop.setAction(actionMap.get("listen")); // NOI18N
        speakStop.setText(resourceMap.getString("speakStop.text")); // NOI18N
        speakStop.setName("speakStop"); // NOI18N
        speakStop.setBounds(360, 450, 116, 39);
        jLayeredPane1.add(speakStop, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        answerPatternTextBox.setColumns(20);
        answerPatternTextBox.setLineWrap(true);
        answerPatternTextBox.setRows(5);
        answerPatternTextBox.setText(resourceMap.getString("answerPatternTextBox.text")); // NOI18N
        answerPatternTextBox.setName("answerPatternTextBox"); // NOI18N
        jScrollPane7.setViewportView(answerPatternTextBox);

        jScrollPane7.setBounds(340, 340, 360, -1);
        jLayeredPane1.add(jScrollPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        answerTypeTextBox.setColumns(20);
        answerTypeTextBox.setLineWrap(true);
        answerTypeTextBox.setRows(5);
        answerTypeTextBox.setText(resourceMap.getString("answerTypeTextBox.text")); // NOI18N
        answerTypeTextBox.setName("answerTypeTextBox"); // NOI18N
        jScrollPane6.setViewportView(answerTypeTextBox);

        jScrollPane6.setBounds(20, 340, 311, -1);
        jLayeredPane1.add(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        solveButton.setAction(actionMap.get("solveQuestion")); // NOI18N
        solveButton.setText(resourceMap.getString("solveButton.text")); // NOI18N
        solveButton.setName("solveButton"); // NOI18N
        solveButton.setBounds(550, 450, 123, 40);
        jLayeredPane1.add(solveButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        queryStringTextBox.setColumns(20);
        queryStringTextBox.setLineWrap(true);
        queryStringTextBox.setRows(5);
        queryStringTextBox.setText(resourceMap.getString("queryStringTextBox.text")); // NOI18N
        queryStringTextBox.setName("queryStringTextBox"); // NOI18N
        jScrollPane5.setViewportView(queryStringTextBox);

        jScrollPane5.setBounds(340, 230, 360, 100);
        jLayeredPane1.add(jScrollPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        newsTextBox.setColumns(20);
        newsTextBox.setLineWrap(true);
        newsTextBox.setRows(5);
        newsTextBox.setText(resourceMap.getString("newsTextBox.text")); // NOI18N
        newsTextBox.setName("newsTextBox"); // NOI18N
        jScrollPane1.setViewportView(newsTextBox);

        jScrollPane1.setBounds(20, 20, 311, 203);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        interpretationsTextBox.setColumns(20);
        interpretationsTextBox.setLineWrap(true);
        interpretationsTextBox.setRows(5);
        interpretationsTextBox.setText(resourceMap.getString("interpretationsTextBox.text")); // NOI18N
        interpretationsTextBox.setName("interpretationsTextBox"); // NOI18N
        jScrollPane3.setViewportView(interpretationsTextBox);

        jScrollPane3.setBounds(340, 120, 360, 100);
        jLayeredPane1.add(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        answerTextBox.setColumns(20);
        answerTextBox.setLineWrap(true);
        answerTextBox.setRows(5);
        answerTextBox.setText(resourceMap.getString("answerTextBox.text")); // NOI18N
        answerTextBox.setName("answerTextBox"); // NOI18N
        jScrollPane2.setViewportView(answerTextBox);

        jScrollPane2.setBounds(340, 20, 360, 90);
        jLayeredPane1.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        questionTextBox.setColumns(20);
        questionTextBox.setLineWrap(true);
        questionTextBox.setRows(5);
        questionTextBox.setText(resourceMap.getString("questionTextBox.text")); // NOI18N
        questionTextBox.setName("questionTextBox"); // NOI18N
        jScrollPane4.setViewportView(questionTextBox);

        jScrollPane4.setBounds(20, 230, 311, -1);
        jLayeredPane1.add(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        loadButton.setAction(actionMap.get("loadAll")); // NOI18N
        loadButton.setText(resourceMap.getString("loadButton.text")); // NOI18N
        loadButton.setName("loadButton"); // NOI18N
        loadButton.setBounds(150, 450, 123, 40);
        jLayeredPane1.add(loadButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setIcon(resourceMap.getIcon("jLabel4.icon")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        chatQuestion.setFont(resourceMap.getFont("chatResult.font")); // NOI18N
        chatQuestion.setText(resourceMap.getString("chatQuestion.text")); // NOI18N
        chatQuestion.setName("chatQuestion"); // NOI18N

        chatResult.setEditable(false);
        chatResult.setFont(resourceMap.getFont("chatResult.font")); // NOI18N
        chatResult.setText(resourceMap.getString("chatResult.text")); // NOI18N
        chatResult.setName("chatResult"); // NOI18N

        processButton.setAction(actionMap.get("textProcessing")); // NOI18N
        processButton.setText(resourceMap.getString("processButton.text")); // NOI18N
        processButton.setName("processButton"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(processButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chatQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                            .addComponent(chatResult, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addContainerGap(56, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(processButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(chatQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chatResult, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N
        menuBar.setOpaque(false);

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 773, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents


    SpeakCalculator speakCalc = null;
    
    private void loadAllButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadAllButtonMouseClicked

        if(!disableLoad)
        {
            try {
                String imageName = "res/icons/reload.png";
                ImageIcon icon = new ImageIcon(imageName);
                icon.getImage().flush();
                loadAllButton.setIcon( icon );
                loadAllButton.revalidate();
                loadAllButton.repaint();
            } catch (Exception ex) {
                Logger.getLogger(QAiiiTMView.class.getName()).log(Level.SEVERE, null, ex);
            }


            /*OE = new OpenEphyra("");
             *
             */

            System.setProperty("usr.dir", currentRootDirectoryPath);
            //rec.init("lib", false, true,"lib/gram/custom", "beggar");
            syn.init("lib", true, false, "", "");
            //rec.setRecognizerEnabled(false);
            
            
            String abhi_args[] ={"bot=abhishar","action=chat","trace=false"};
            String news_args[] ={"bot=news","action=chat","trace=false"};
            String think_args[] ={"bot=think","action=chat","trace=false"};
            String notes_args[] ={"bot=notes","action=chat","trace=false"};
            String train_args[] ={"bot=trainer","action=chat","trace=false"};


            abhi.load(abhi_args,0);
            //abhi.processChat("Hello");
            abhi.load(news_args,1);
            abhi.load(think_args,2);
            abhi.load(notes_args,3);
            abhi.load(train_args,4);

            chatButton.setEnabled(true);
            newsButton.setEnabled(true);
            speakButton.setEnabled(true);
            noteButton.setEnabled(true);
            settingsButton.setEnabled(true);
            thinkButton.setEnabled(true);
            thinkButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            thinkButton.revalidate();
            thinkButton.repaint();
            SchedulerDisplay SD = new SchedulerDisplay();
            SD.setLocationRelativeTo(jLabel4);
            SD.setVisible(true);
            SpellChecker.registerDictionaries( null, "en" ); // Spell Checking API
            SpellChecker.register( chatQuestion );
            loadAllButton.setEnabled(false);
            processButton.setEnabled(true);
            disableLoad = true;
            modes[2] = true;
        }

    }//GEN-LAST:event_loadAllButtonMouseClicked

    private void speakButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_speakButtonMouseClicked
 /*
        if(!disableListen)
        {
            rec.setRecognizerEnabled(true);
            try {
                String imageName = "res/icons/stopmicrophone.png";
                ImageIcon icon = new ImageIcon(imageName);
                icon.getImage().flush();
                speakButton.setIcon( icon );
                speakButton.revalidate();
                speakButton.repaint();
            } catch (Exception ex) {
                Logger.getLogger(QAiiiTMView.class.getName()).log(Level.SEVERE, null, ex);
            }
            disableListen = !disableListen;
        }
        else
        {
            rec.setRecognizerEnabled(false);
            try {a
                String imageName = "res/icons/microphone.png";
                ImageIcon icon = new ImageIcon(imageName);
                icon.getImage().flush();
                speakButton.setIcon( icon );
                speakButton.revalidate();
                speakButton.repaint();
            } catch (Exception ex) {
                Logger.getLogger(QAiiiTMView.class.getName()).log(Level.SEVERE, null, ex);
            }
            disableListen = !disableListen;

        }


        Thread T=new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                {
                    if(rec.isRecognizerEnabled())
                    {
                        try
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e)
			{
			}

			while (rec.getRecognizerQueueSize() > 0)
			{
				String s = rec.popRecognizedString();

				// Check if the string contains 'quit'.
				if (s.equals("quit"))
				{
                                    rec.setRecognizerEnabled(false);
                                    try {
                                        String imageName = "res/icons/microphone.png";
                                        ImageIcon icon = new ImageIcon(imageName);
                                        icon.getImage().flush();
                                        speakButton.setIcon( icon );
                                        speakButton.revalidate();
                                        speakButton.repaint();
                                    } catch (Exception ex) {
                                        Logger.getLogger(QAiiiTMView.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    disableListen = !disableListen;
				}

				System.out.println("You said: " + s);
                                int pick=0;
                                for(int i=0;i<5;i++)if(modes[i]==true){pick=i;break;}
                                try {
                                    processTask(s, pick);
                                } catch (Exception ex) {
                                    Logger.getLogger(QAiiiTMView.class.getName()).log(Level.SEVERE, null, ex);
                                }
			}
		     }

                }

            }
        } );
        T.start();
  *
  */

         
        try
        {
            String s = googleListen();
            System.out.println(s);
            int pick=0;
            for(int i=0;i<5;i++)if(modes[i]==true){pick=i;break;}
            processTask(s, pick);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_speakButtonMouseClicked


    private void noteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noteButtonMouseClicked
        // TODO add your handling code here:
        for(int i=0;i<5;i++)modes[i]=false;
        modes[3]=true;
        thinkButton.setBorder(BorderFactory.createEmptyBorder());
        chatButton.setBorder(BorderFactory.createEmptyBorder());
        newsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        noteButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_noteButtonMouseClicked

    private void settingsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButtonMouseClicked
        // TODO add your handling code here:
        for(int i=0;i<5;i++)modes[i]=false;
        modes[4]=true;
        thinkButton.setBorder(BorderFactory.createEmptyBorder());
        chatButton.setBorder(BorderFactory.createEmptyBorder());
        newsButton.setBorder(BorderFactory.createEmptyBorder());
        noteButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_settingsButtonMouseClicked

    private void chatButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatButtonMouseClicked

        for(int i=0;i<5;i++)modes[i]=false;
        modes[0]=true;
        thinkButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        newsButton.setBorder(BorderFactory.createEmptyBorder());
        noteButton.setBorder(BorderFactory.createEmptyBorder());
        chatButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        // TODO add your handling code here:
    }//GEN-LAST:event_chatButtonMouseClicked

    private void newsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newsButtonMouseClicked
        for(int i=0;i<5;i++)modes[i]=false;
        modes[1]=true;
        thinkButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        chatButton.setBorder(BorderFactory.createEmptyBorder());
        noteButton.setBorder(BorderFactory.createEmptyBorder());
        newsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        // TODO add your handling code here:
    }//GEN-LAST:event_newsButtonMouseClicked

    private void thinkButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thinkButtonMouseClicked
        for(int i=0;i<5;i++)modes[i]=false;
        modes[2]=true;
        newsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        chatButton.setBorder(BorderFactory.createEmptyBorder());
        noteButton.setBorder(BorderFactory.createEmptyBorder());
        thinkButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        // TODO add your handling code here:
    }//GEN-LAST:event_thinkButtonMouseClicked
    OpenEphyra OE = null;

    @Action
    public void synthesis() {
        syn.synthesize(answerTextBox.getText());
    }

    public String googleListen()
    {
        GoogleResponse gr = null;
        try
        {
            mic.captureAudioToFile("abc.wav");
            int i=0;
            long start = System.nanoTime();
            while(true)
            {
                long time = System.nanoTime()-start;
                if(time/1000000000>2)break;
                System.out.println(time/1000000000);
            }
            mic.close();
            Thread.sleep(500);
            Recognizer recog = new Recognizer();
            gr = recog.getRecognizedDataForWave("abc.wav");
            chatQuestion.setText(gr.getResponse());
            System.out.println(gr.getResponse());
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }

        return(gr.getResponse());
    }
    @Action
    public String listen() {


        rec.setRecognizerEnabled(true);
        newsTextBox.setText("");
        String s = "";
        s = rec.popRecognizedString();
        int x=0;
        while(s.equals(""))
        {
            newsTextBox.repaint();
            x++;
            if(x>=10000000)break;
            s = rec.popRecognizedString();
            while(rec.getRecognizerQueueSize() > 0)
            {
                s = rec.popRecognizedString();
            }
        }
        rec.setRecognizerEnabled(false);
        return s;
    }

    public void processTask(String task,int type) throws Exception
    {
        //-Xmx1024M -Xms512M -Djava.library.path=lib/ -Djava.security.policy=applet.policy
        switch(type)
        {
            case 0: //  chat-mode
            {
                String chatRes = abhi.processChat(task,0);
                chatResult.setText(chatRes);
                //ResultDisplay RD = new ResultDisplay(chatRes);
                //RD.setVisible(true);
                syn.synthesize(chatRes);
                break;
            }
            case 1: //  news-mode
            {
                String newsRes = abhi.processChat(task,1);
                NewsProcessor newsProc = new NewsProcessor();
                newsRes = newsProc.rawRequest(task);
                //NewsDisplay ND= new NewsDisplay();
                //ND.setVisible(true);
                chatResult.setText(newsRes);
                break;
            }
            case 2: //  think-mode
            {
                
                String thinkRes = abhi.processChat(task,2);
                chatResult.setText(thinkRes);
                if(thinkRes.equals("I have no answer for that."))
                       thinkRes = task;
                ThinkProcessor TP = new ThinkProcessor();
                TP.load();
                TP.process(thinkRes);
                break;
            }
            case 3: //  note-mode
            {
                String noteRes = abhi.processChat(task,3);
                NotesProcessor NP = new NotesProcessor();
                NP.process(noteRes);
                String desired = NP.result;
                chatResult.setText(desired);
                break;
            }
            case 4: //  settings-mode
            {
                String trainRes = abhi.processChat(task,4);
                chatResult.setText(trainRes);
                break;
            }
            default:
            {
                break;
            }
         };

    }

    
    @Action
    public void solveQuestion() {
        AnalyzedQuestion aq = OE.analyzer(questionTextBox.getText());
        String[] answerTypes = aq.getAnswerTypes();
        String pr = "";
        for(int i=0;i<answerTypes.length;i++)
        {
            pr+=answerTypes[i]+"\n";
        }
        answerTextBox.setText(pr);
        QuestionInterpretation[] interpret = aq.getInterpretations();
        pr = "";
        for(int i=0;i<interpret.length;i++)
        {
            pr+=interpret[i].toString()+"\n";
        }
        interpretationsTextBox.setText(pr);

        Query[] queries = OE.queryStrings(aq);
        pr = "";
        for(int i=0;i<queries.length;i++)
        {
            pr+=queries[i].getQueryString()+"\n";
        }
        queryStringTextBox.setText(pr);
        answerTextBox.setText(OE.questionAnalyse(newsTextBox.getText(), questionTextBox.getText()));
        Result[] rs = OE.answerType(newsTextBox.getText(), questionTextBox.getText());
        pr="";
        for(int i=queries.length;i<rs.length;i++)
        {
            pr+=rs[i].getAnswer()+"\n";
        }
        answerTypeTextBox.setText(pr);
        rs = OE.answerPattern(newsTextBox.getText(), questionTextBox.getText());
        pr="";
        for(int i=queries.length;i<rs.length;i++)
        {
            pr+=rs[i].getAnswer()+"\n";
        }
        answerPatternTextBox.setText(pr);
    }
    public static int micOnOff = 0;
    @Action
    public void netSpeech() {
        micOnOff++;
        speakStop.setText("Speak");
        if(micOnOff%2==1)
        {
                speakStop.setText("Stop");
                try
                {
                    mic.captureAudioToFile("abc.wav");
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
        }
        else
        {
                speakStop.setText("Speak");
                try
                {
                    mic.close();
                    Thread.sleep(500);
                    Recognizer recog = new Recognizer();
                    GoogleResponse gr = recog.getRecognizedDataForWave("abc.wav");
                    //System.out.println(gr.getResponse());
                    newsTextBox.setText(gr.getResponse());
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
        }

    }

    @Action
    public void textProcessing() throws Exception{


            int pick=0;
            for(int i=0;i<5;i++)if(modes[i]==true){pick=i;break;}
            processTask(chatQuestion.getText(), pick);
            
    }

    @Action
    public Task backListen() {
        return new BackListenTask(getApplication());
    }

    private class BackListenTask extends org.jdesktop.application.Task<Object, Void> {
        BackListenTask(org.jdesktop.application.Application app) {
            // Runs on the EDT.  Copy GUI state that
            // doInBackground() depends on from parameters
            // to BackListenTask fields, here.
            super(app);
        }
        @Override protected Object doInBackground() {
            // Your Task's code here.  This method runs
            // on a background thread, so don't reference
            // the Swing GUI from here.
            while(true)
            {
                System.out.println("hello");
                if(chatQuestion.getText().equals("exit")==true)break;
            }
            return null;  // return your result
        }
        @Override protected void succeeded(Object result) {
            // Runs on the EDT.  Update the GUI based on
            // the result computed by doInBackground().
        }
    }

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea answerPatternTextBox;
    private javax.swing.JTextArea answerTextBox;
    private javax.swing.JTextArea answerTypeTextBox;
    private javax.swing.JLabel chatButton;
    private javax.swing.JTextField chatQuestion;
    private javax.swing.JTextField chatResult;
    private javax.swing.JTextArea interpretationsTextBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel loadAllButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel newsButton;
    private javax.swing.JTextArea newsTextBox;
    private javax.swing.JLabel noteButton;
    private javax.swing.JButton processButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextArea queryStringTextBox;
    private javax.swing.JTextArea questionTextBox;
    private javax.swing.JLabel settingsButton;
    private javax.swing.JButton solveButton;
    private javax.swing.JLabel speakButton;
    private javax.swing.JButton speakStop;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel thinkButton;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
