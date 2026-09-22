package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        int newRow = row;
        int newCol = col;

        while (newRow < 8) {
            newRow += 1;
            ChessPosition newPosition = new ChessPosition(newRow, col);
            if (addMoves(board, myPosition, possibleMoves, newPosition)) {
                break;
            }
        }

        newRow = row;
        while (newRow > 1) {
            newRow -= 1;
            ChessPosition newPosition = new ChessPosition(newRow, col);
            if (addMoves(board, myPosition, possibleMoves, newPosition)) {
                break;
            }
        }

        while (newCol < 8) {
            newCol += 1;
            ChessPosition newPosition = new ChessPosition(row, newCol);
            if (addMoves(board, myPosition, possibleMoves, newPosition)) {
                break;
            }
        }

        newCol = col;
        while (newCol > 1) {
            newCol -= 1;
            ChessPosition newPosition = new ChessPosition(row, newCol);
            if (addMoves(board, myPosition, possibleMoves, newPosition)) {
                break;
            }
        }

        return possibleMoves;
    }

    private boolean addMoves(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> possibleMoves, ChessPosition newPosition) {
        if (board.getPiece(newPosition) == null) {
            possibleMoves.add(new ChessMove(myPosition, newPosition, null));
        } else if (board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
            possibleMoves.add(new ChessMove(myPosition, newPosition, null));
            return true;
        } else {
            return true;
        }
        return false;
    }
}
