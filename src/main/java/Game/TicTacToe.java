package Game;

public class TicTacToe {
    private final char[] m_board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final int m_boardSize = 9;
    private final char[] m_playerChars = {'X', 'O'};
    private char m_current = 'X';
    private int m_scoreX = 0, m_scoreO = 0;
    public enum Player { X, O }

    public boolean isFree(int index) {
        return m_board[index] != 'X' && m_board[index] != 'O';
    }

    public boolean isFull() {
        for(int i = 0; i < m_boardSize; i++) {
            if(Character.isDigit(m_board[i])) {
                return false;
            }
        }
        return true;
    }

    public void setPlayerChar(int index) {
        m_board[index] = m_current;
    }

    public void increaseScore(Player player) {
        if(player == Player.X) {
            m_scoreX++;
        }
        else {
            m_scoreO++;
        }
    }

    public void resetScore() {
        m_scoreX = 0;
        m_scoreO = 0;
    }

    public String getScore(Player player) {
        return (player == Player.X) ? Integer.toString(m_scoreX) : Integer.toString(m_scoreO);
    }

    public void switchPlayer() {
        m_current = (m_current == 'X') ? 'O' : 'X';
    }

    public void setPlayer(Player player) {
        m_current = (player == Player.X) ? 'X' : 'O';
    }

    public String getPlayerChar() {
        return Character.toString(m_current);
    }

    public boolean checkLine(int firstIndex, int secondIndex, int thirdIndex , Player player) {
        char playerChar = m_playerChars[player.ordinal()];

        return m_board[firstIndex] == playerChar && m_board[secondIndex] == playerChar && m_board[thirdIndex] == playerChar;
    }

    public boolean hasWon(Player player) {
        for(int i = 0; i < m_boardSize; i += 3) {
            if(checkLine(i, i + 1, i + 2, player)) {
                return true;
            }
        }

        for(int i = 0; i < m_boardSize / 3; i++) {
            if(checkLine(i, i + 3, i + 6, player)) {
                return true;
            }
        }

        return checkLine(0, 4, 8, player) || checkLine(2, 4, 6, player);
    }

    public void resetGame() {
        for(int i = 1; i < m_boardSize; i++) {
            m_board[i - 1] = (char) (i + '0');
        }
    }
}
