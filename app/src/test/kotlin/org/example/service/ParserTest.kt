import org.example.model.*
import org.example.service.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {

    @Test
    fun `test parsing multiple operations expression`() {
        val tokens = listOf(
            Token(TokenType.NUMBER, "5"),
            Token(TokenType.PLUS),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.MULTIPLY),
            Token(TokenType.NUMBER, "2")
        )

        val parser = Parser(tokens)
        val ast = parser.expr()

        val expectedAst = BinaryOpNode(
            NumberNode(5f),
            Token(TokenType.PLUS),
            BinaryOpNode(NumberNode(3f), Token(TokenType.MULTIPLY), NumberNode(2f))
        )

        assertEquals(expectedAst, ast)
    }

    @Test
    fun `test parsing single operation expression`() {
        val tokens = listOf(
            Token(TokenType.NUMBER, "5"),
            Token(TokenType.PLUS),
            Token(TokenType.NUMBER, "3")
        )

        val parser = Parser(tokens)
        val ast = parser.expr()

        val expectedAst = BinaryOpNode(
            NumberNode(5f),
            Token(TokenType.PLUS),
            NumberNode(3f)
        )

        assertEquals(expectedAst, ast)
    }

    @Test
    fun `test parsing complex expression with parentheses`() {
        val tokens = listOf(
            Token(TokenType.LPAREN),
            Token(TokenType.NUMBER, "5"),
            Token(TokenType.PLUS),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.RPAREN),
            Token(TokenType.MULTIPLY),
            Token(TokenType.NUMBER, "2")
        )

        val parser = Parser(tokens)
        val ast = parser.expr()

        val expectedAst = BinaryOpNode(
            BinaryOpNode(NumberNode(5f), Token(TokenType.PLUS), NumberNode(3f)),
            Token(TokenType.MULTIPLY),
            NumberNode(2f)
        )

        assertEquals(expectedAst, ast)
    }

    @Test
    fun `test parsing complex expression with multiple parentheses`() {
        val tokens = listOf(
            Token(TokenType.LPAREN),
            Token(TokenType.NUMBER, "5"),
            Token(TokenType.PLUS),
            Token(TokenType.LPAREN),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.DIVIDE),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.RPAREN),
            Token(TokenType.RPAREN),
            Token(TokenType.MULTIPLY),
            Token(TokenType.NUMBER, "2")
        )

        val parser = Parser(tokens)
        val ast = parser.expr()

        val expectedAst = BinaryOpNode(
            BinaryOpNode(
                NumberNode(5f),
                Token(TokenType.PLUS),
                BinaryOpNode(NumberNode(3f), Token(TokenType.DIVIDE), NumberNode(3f))),
            Token(TokenType.MULTIPLY),
            NumberNode(2f)
        )

        assertEquals(expectedAst, ast)
    }
}
