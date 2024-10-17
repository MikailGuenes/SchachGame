package com.example.schachjpc


import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import com.example.schachjpc.ui.theme.Beige
import com.example.schachjpc.ui.theme.Gray


val gameModel: ChessGame = ChessGame(SavedStateHandle())


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val board = initializeChessBoard()
            Schachbrett(board)
        }
    }
}






@Composable
fun Schachbrett(board: Array<ChessPiece?>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(8),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        items(board.size) { index ->
            val piece = board[index]
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(if ((index / 8 + index % 8) % 2 == 0) Beige else Gray),
                contentAlignment = Alignment.Center
            ) {
                if (piece != null) {
                    Image(
                        modifier = Modifier.clickable {
                            if(piece.type == ChessPieceType.PAWN){
                                piece.viewModel.movePiece(FigurPosition(1,0),FigurPosition(2,0))

                            }


                        },
                        painter = painterResource(getChessPieceSymbol(piece)),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

fun getChessPieceSymbol(piece: ChessPiece): Int {
    return when (piece.type) {
        ChessPieceType.PAWN -> if (piece.color == ChessPieceColor.WHITE)  R.drawable.bauer_schwarz else R.drawable.bauer_weis

        ChessPieceType.ROOK -> if (piece.color == ChessPieceColor.WHITE) R.drawable.schwarz_turm else R.drawable.weis_turm

        ChessPieceType.KNIGHT -> if (piece.color == ChessPieceColor.WHITE) R.drawable.schwarz_pferd else R.drawable.weis_pferd

        ChessPieceType.BISHOP -> if (piece.color == ChessPieceColor.WHITE) R.drawable.schwarz_laeufer else R.drawable.weis_laufer

        ChessPieceType.QUEEN -> if (piece.color == ChessPieceColor.WHITE)  R.drawable.schwarz_dame else R.drawable.weis_dame

        ChessPieceType.KING -> if (piece.color == ChessPieceColor.WHITE) R.drawable.schwarz_koenig else R.drawable.weis_koenig
    }
}






@Preview
@Composable
fun SchachbrettPreview() {
    val board = initializeChessBoard()
    Schachbrett(board)
}


fun initializeChessBoard(): Array<ChessPiece?> {
    val board = arrayOfNulls<ChessPiece>(64) // 8x8 Felder

    // Weiße Bauern
    for (i in 8..15) {
        board[i] = ChessPiece(ChessPieceType.PAWN, ChessPieceColor.WHITE, FigurPosition(i / 8, i % 8),gameModel)
    }

    // Schwarze Bauern
    for (i in 48..55) {
        board[i] = ChessPiece(ChessPieceType.PAWN, ChessPieceColor.BLACK, FigurPosition(i / 8, i % 8),gameModel)
    }

    // Weiße Hauptfiguren
    board[0] = ChessPiece(ChessPieceType.ROOK, ChessPieceColor.WHITE, FigurPosition(0, 0),gameModel)
    board[1] = ChessPiece(ChessPieceType.KNIGHT, ChessPieceColor.WHITE, FigurPosition(0, 1),gameModel)
    board[2] = ChessPiece(ChessPieceType.BISHOP, ChessPieceColor.WHITE, FigurPosition(0, 2),gameModel)
    board[3] = ChessPiece(ChessPieceType.QUEEN, ChessPieceColor.WHITE, FigurPosition(0, 3),gameModel)
    board[4] = ChessPiece(ChessPieceType.KING, ChessPieceColor.WHITE, FigurPosition(0, 4),gameModel)
    board[5] = ChessPiece(ChessPieceType.BISHOP, ChessPieceColor.WHITE, FigurPosition(0, 5),gameModel)
    board[6] = ChessPiece(ChessPieceType.KNIGHT, ChessPieceColor.WHITE, FigurPosition(0, 6),gameModel)
    board[7] = ChessPiece(ChessPieceType.ROOK, ChessPieceColor.WHITE, FigurPosition(0, 7),gameModel)

    // Schwarze Hauptfigure,gameModeln
    board[56] = ChessPiece(ChessPieceType.ROOK, ChessPieceColor.BLACK, FigurPosition(7, 0),gameModel)
    board[57] = ChessPiece(ChessPieceType.KNIGHT, ChessPieceColor.BLACK, FigurPosition(7, 1),gameModel)
    board[58] = ChessPiece(ChessPieceType.BISHOP, ChessPieceColor.BLACK, FigurPosition(7, 2),gameModel)
    board[59] = ChessPiece(ChessPieceType.QUEEN, ChessPieceColor.BLACK, FigurPosition(7, 3),gameModel)
    board[60] = ChessPiece(ChessPieceType.KING, ChessPieceColor.BLACK, FigurPosition(7, 4),gameModel)
    board[61] = ChessPiece(ChessPieceType.BISHOP, ChessPieceColor.BLACK, FigurPosition(7, 5),gameModel)
    board[62] = ChessPiece(ChessPieceType.KNIGHT, ChessPieceColor.BLACK, FigurPosition(7, 6),gameModel)
    board[63] = ChessPiece(ChessPieceType.ROOK, ChessPieceColor.BLACK, FigurPosition(7, 7),gameModel)

    return board
}


