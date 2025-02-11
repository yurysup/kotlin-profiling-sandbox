import kotlin.test.Test
import kotlin.test.assertEquals
import org.example.model.Token
import org.example.model.TokenType
import org.example.service.Lexer

class LexerTest {

    @Test
    fun `test tokenize no space`() {
        val lexer = Lexer("5+3*2")
        val tokens = lexer.tokenize()

        assertEquals(
            listOf(
                Token(TokenType.NUMBER, "5"),
                Token(TokenType.PLUS),
                Token(TokenType.NUMBER, "3"),
                Token(TokenType.MULTIPLY),
                Token(TokenType.NUMBER, "2")
            ),
            tokens
        )
    }

    @Test
    fun `test tokenize spaces are stripped`() {
        val lexer = Lexer("   5 + 3 * 2  ")
        val tokens = lexer.tokenize()

        assertEquals(
            listOf(
                Token(TokenType.NUMBER, "5"),
                Token(TokenType.PLUS),
                Token(TokenType.NUMBER, "3"),
                Token(TokenType.MULTIPLY),
                Token(TokenType.NUMBER, "2")
            ),
            tokens
        )
    }
}
