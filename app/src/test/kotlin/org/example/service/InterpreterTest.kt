import org.example.model.*
import org.example.service.*
import kotlin.test.Test
import kotlin.test.assertEquals

class InterpreterTest {

    @Test
    fun `test single number`() {
        val interpreter = Interpreter()
        val ast = NumberNode(42f)

        val result = interpreter.eval(ast)

        assertEquals(42f, result)
    }

    @Test
    fun `test simple addition`() {
        val interpreter = Interpreter()
        val ast = BinaryOpNode(NumberNode(5f), Token(TokenType.PLUS), NumberNode(3f))

        val result = interpreter.eval(ast)

        assertEquals(8f, result)
    }

    @Test
    fun `test multiplication and precedence`() {
        val interpreter = Interpreter()
        val ast = BinaryOpNode(
            NumberNode(5f),
            Token(TokenType.PLUS),
            BinaryOpNode(NumberNode(3f), Token(TokenType.MULTIPLY), NumberNode(2f))
        )

        val result = interpreter.eval(ast)

        assertEquals(11f, result) // 5 + (3 * 2) = 11
    }

    @Test
    fun `test parentheses changing precedence`() {
        val interpreter = Interpreter()
        val ast = BinaryOpNode(
            BinaryOpNode(NumberNode(5f), Token(TokenType.PLUS), NumberNode(3f)),
            Token(TokenType.MULTIPLY),
            NumberNode(2f)
        )

        val result = interpreter.eval(ast)

        assertEquals(16f, result) // (5 + 3) * 2 = 16
    }

    @Test
    fun `test division`() {
        val interpreter = Interpreter()
        val ast = BinaryOpNode(NumberNode(10f), Token(TokenType.DIVIDE), NumberNode(2f))

        val result = interpreter.eval(ast)

        assertEquals(5f, result) // 10 / 2 = 5
    }
}
