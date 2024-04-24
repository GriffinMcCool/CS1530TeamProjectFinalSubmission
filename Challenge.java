import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Challenge extends JPanel{

    String title;
    String description;
    String objective;
    int difficulty;
    String language;
    JButton createChallengeButton;

    
    // Method to create a button for creating challenges
    
        public Challenge(String title, String description, String objective, int difficulty, String language) {
            this.title = title;
            this.description = description;
            this.objective = objective;
            this.difficulty = difficulty;
            this.language = language;
            
        }
        public JPanel createChallengePanel(Challenge challenge) {
            JPanel challengePanel = new JPanel();
            setLayout(new FlowLayout());
            // Populate the challengePanel with challenge details
            JLabel titleLabel = new JLabel("Title: " + challenge.getTitle());
            JLabel descriptionLabel = new JLabel("Description: " + challenge.getDescription());
            JLabel objectiveLabel = new JLabel("Objective: " + challenge.getObjective());
            JLabel difficultyLabel = new JLabel("Difficulty: " + challenge.getDifficulty());
            JLabel languageLabel = new JLabel("Language: " + challenge.getLanguage());
    
            challengePanel.add(titleLabel);
            challengePanel.add(descriptionLabel);
            challengePanel.add(objectiveLabel);
            challengePanel.add(difficultyLabel);
            challengePanel.add(languageLabel);
    
            return challengePanel;
        }
        public String getTitle() {
            return title;
        }
    
        public String getDescription() {
            return description;
        }
    
        public String getObjective() {
            return objective;
        }
    
        public int getDifficulty() {
            return difficulty;
        }
    
        public String getLanguage() {
            return language;
        }
    
        

}
