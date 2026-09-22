package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMovesCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        int newRow;
        int newNewRow;
        int start;
        int end;
        boolean validMove = false;

        if (board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE) {
            newRow = row + 1;
            newNewRow = row + 2;
            start = 2;
            end = 8;
            if (row < 8) {
                validMove = true;
            }
        } else {
            newRow = row - 1;
            newNewRow = row - 2;
            start = 7;
            end = 1;
            if (row > 1) {
                validMove = true;
            }
        }

        if (validMove) {
            ChessPosition newPosition = new ChessPosition(newRow, col);
            if (board.getPiece(newPosition) == null) {
                if (newRow == end) {
                    addPromotionPiece(possibleMoves, myPosition, newPosition);
                } else {
                    possibleMoves.add(new ChessMove(myPosition, newPosition, null));
                    if (row == start) {
                        ChessPosition newNewPosition = new ChessPosition(newNewRow, col);
                        if (board.getPiece(newNewPosition) ==  null) {
                            possibleMoves.add(new ChessMove(myPosition, newNewPosition, null));
                        }
                    }
                }
            }
            if (col < 8) {
                ChessPosition capturePosition = new ChessPosition(newRow, col + 1);
                addCaptureMove(board, myPosition, possibleMoves, newRow, end, capturePosition);
            }
            if (col > 1) {
                ChessPosition capturePosition = new ChessPosition(newRow, col - 1);
                addCaptureMove(board, myPosition, possibleMoves, newRow, end, capturePosition);
            }
        }

        return possibleMoves;
    }

    private void addCaptureMove(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> possibleMoves, int newRow, int end, ChessPosition capturePosition) {
        if (board.getPiece(capturePosition) != null) {
            if (board.getPiece(capturePosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                if (newRow == end) {
                    addPromotionPiece(possibleMoves, myPosition, capturePosition);
                } else {
                    possibleMoves.add(new ChessMove(myPosition, capturePosition, null));
                }
            }
        }
    }

    void addPromotionPiece(Collection<ChessMove> possibleMoves, ChessPosition myPosition, ChessPosition newPosition) {
        possibleMoves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.QUEEN));
        possibleMoves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.BISHOP));
        possibleMoves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.KNIGHT));
        possibleMoves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.ROOK));
    }
}
