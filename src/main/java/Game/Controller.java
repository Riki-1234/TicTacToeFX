package Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    private final TicTacToe m_game = new TicTacToe();
    private boolean m_roundEnded = false;

    @FXML
    private Button m_button1, m_button2, m_button3, m_button4, m_button5, m_button6, m_button7, m_button8, m_button9;

    @FXML
    private Button m_buttonNextRound, m_buttonResetScore;

    @FXML
    private TextField m_infoText, m_textScoreX, m_textScoreO;

    private int getButtonID(Button button) {
        char id_char = (button.getId()).charAt(8);
        return Integer.parseInt(Character.toString(id_char));
    }

    private void clearButtons() {
        Button[] buttons = new Button[]{m_button1, m_button2, m_button3, m_button4, m_button5, m_button6, m_button7, m_button8, m_button9};

        for(int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
    }

    private void resetScore() {
        m_game.resetScore();

        m_textScoreX.setText("0");
        m_textScoreO.setText("0");
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        Button button = (Button)event.getSource();

        if(button == m_buttonNextRound && m_roundEnded) {
            if(m_game.getScore(TicTacToe.Player.X).equals("3") || m_game.getScore(TicTacToe.Player.O).equals("3")) {
                resetScore();
            }

            m_game.resetGame();
            m_game.setPlayer(TicTacToe.Player.X);
            clearButtons();

            m_infoText.setText("PLAYER X'S TURN");
            m_roundEnded = false;

            return;
        }
        else if(button == m_buttonResetScore) {
            resetScore();

            return;
        }
        else if(m_roundEnded) {
            return;
        }

        int index = getButtonID(button) - 1;
        if(m_game.isFree(index)) {
            m_game.setPlayerChar(index);
            button.setText(m_game.getPlayerChar());

            m_game.switchPlayer();
            m_infoText.setText("PLAYER " + m_game.getPlayerChar() + "'S TURN");
        }
        else {
            m_infoText.setText("PLAYER " + m_game.getPlayerChar() + "'S TURN | INVALID MOVE");
        }

        if(m_game.hasWon(TicTacToe.Player.X)) {
            m_game.increaseScore(TicTacToe.Player.X);
            m_textScoreX.setText(m_game.getScore(TicTacToe.Player.X));
            m_infoText.setText("PLAYER X HAS WON");

            m_roundEnded = true;
        }
        else if(m_game.hasWon(TicTacToe.Player.O)) {
            m_game.increaseScore(TicTacToe.Player.O);
            m_textScoreO.setText(m_game.getScore(TicTacToe.Player.O));
            m_infoText.setText("PLAYER O HAS WON");

            m_roundEnded = true;
        }
        else if(m_game.isFull()) {
            m_infoText.setText("IT'S A DRAW");
            m_roundEnded = true;
        }
    }
}
