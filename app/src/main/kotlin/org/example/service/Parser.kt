package org.example.service

import org.example.model.*

class Parser(private val tokens: List<Token>) {
    private var pos = 0

    private fun currentToken(): Token = tokens.getOrElse(pos) { Token(TokenType.END) }

    private fun eat(type: TokenType): Token {
        val token = currentToken()
        if (token.type == type) {
            pos++
            return token
        } else {
            throw IllegalArgumentException("Unexpected token: $token, expected $type")
        }
    }

    // Parses a single number or a parentheses expression
    private fun factor(): ASTNode {
        val token = currentToken()
        return when (token.type) {
            TokenType.NUMBER -> {
                NumberNode(eat(TokenType.NUMBER).value.toFloat())
            }
            TokenType.LPAREN -> {
                eat(TokenType.LPAREN)
                val node = expr()
                eat(TokenType.RPAREN)
                node
            }
            else -> throw IllegalArgumentException("Unexpected token in factor: $token")
        }
    }

    // Parses multiplication and division
    private fun term(): ASTNode {
        var node = factor()
        while (currentToken().type in listOf(TokenType.MULTIPLY, TokenType.DIVIDE)) {
            val op = eat(currentToken().type)
            node = BinaryOpNode(node, op, factor())
        }
        return node
    }

    // Parses addition and subtraction
    fun expr(): ASTNode {
        var node = term()
        while (currentToken().type in listOf(TokenType.PLUS, TokenType.MINUS)) {
            val op = eat(currentToken().type)
            node = BinaryOpNode(node, op, term())
        }
        return node
    }
}
