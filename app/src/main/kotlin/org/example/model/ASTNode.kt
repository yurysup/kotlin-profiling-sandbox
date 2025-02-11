package org.example.model

sealed class ASTNode

data class NumberNode(val value: Float) : ASTNode()

data class BinaryOpNode(
    val left: ASTNode,
    val op: Token,
    val right: ASTNode
) : ASTNode()
