package com.example.schachjpc


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.saveable

class ChessGame( savedStateHandle: SavedStateHandle): ViewModel() {
    private val board: Array<Array<ChessPiece?>> = Array(8) { arrayOfNulls(8) }
    private val currentPlayer: Player? = null
    private val playerList: List<Player> = emptyList()


    var bauer by savedStateHandle.saveable { mutableStateOf(FigurPosition(0,0)) }


    fun movePiece(from: FigurPosition, to: FigurPosition): Boolean {
        val piece = board[from.x][from.y]
        bauer = to


        return true
    }

}

enum class ChessPieceType( var figur:String, ){
    PAWN("Bauer"),
    ROOK("Turm"),
    KNIGHT("Springer"),
    BISHOP("Pferd"),
    QUEEN("Dame"),
    KING("König");

    fun move(): MovePosition {
        return when (this) {
            PAWN -> MovePosition(0, 1)
            ROOK -> MovePosition(1, 2)
            KNIGHT -> MovePosition(2, 1)
            BISHOP -> MovePosition(1, 1)
            QUEEN -> MovePosition(1, 1)
            KING -> MovePosition(1, 1)
        }
    }
}

enum class ChessPieceColor {
    WHITE, BLACK
}

data class ChessPiece(
    val type: ChessPieceType,
    val color: ChessPieceColor,
    var position: FigurPosition,
    var viewModel: ChessGame
)

data class FigurPosition(
    var x: Int,
    var y: Int
)

data class MovePosition(
    var x: Int,
    var y: Int

)

data class Player(
    val color: ChessPieceColor,
    var name:String
)