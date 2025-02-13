package org.example.processor

import org.example.model.*
import org.example.service.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

enum class ProcessingStrategy {
    SINGLE_THREADED, FIXED_THREAD_POOL
}

class TaskProcessor(strategy: ProcessingStrategy = ProcessingStrategy.FIXED_THREAD_POOL) {
    private val N_THREADS = 4
    private val executor = when (strategy) {
        ProcessingStrategy.FIXED_THREAD_POOL -> Executors.newFixedThreadPool(N_THREADS)
        ProcessingStrategy.SINGLE_THREADED -> Executors.newSingleThreadExecutor()
    }

    fun submit(task: Task): TaskProcessor {
        executor.submit { process(task) }
        return this
    }

    fun submit(tasks: Collection<Task>): TaskProcessor {
        tasks.forEach { executor.execute { process(it) } } // Submit a batch of tasks
        return this
    }

    private fun process(task: Task) {
        //println(Thread.currentThread().name)
        repeat(100) {
            val tokens = Lexer(task.expression).tokenize()
            val result = Interpreter().eval(Parser(tokens).expr())
        }
        //val tokens = Lexer(task.expression).tokenize()
        //val result = Interpreter().eval(Parser(tokens).expr())
        //println("Processed: '${task.expression}' = $result")
    }

    fun shutdown() {
        executor.shutdown()
        executor.awaitTermination(60, TimeUnit.SECONDS) // wait 20s for all tasks to complete
    }
}