package org.example.service

import org.example.model.*

class Interpreter {
    fun eval(node: ASTNode): Float {
        return when (node) {
            is NumberNode -> node.value
            is BinaryOpNode -> {
                val left = eval(node.left)
                val right = eval(node.right)
                when (node.op.type) {
                    TokenType.PLUS -> left + right
                    TokenType.MINUS -> left - right
                    TokenType.MULTIPLY -> left * right
                    TokenType.DIVIDE -> left / right
                    else -> throw IllegalArgumentException("Unknown operator: ${node.op}")
                }
            }
        }
    }
}
