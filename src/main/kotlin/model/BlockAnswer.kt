package model

data class BlockAnswer( val block: Block, val answerHistory: List<Map<Task, SolutionState>> = listOf() ) {

    fun addBlockAttempt() : BlockAnswer{
        val newMap =  HashMap<Task, SolutionState>()
        for( task in block.tasks ){
            newMap[task] = SolutionState.EMPTY
        }
        return this.copy( answerHistory = answerHistory.plus(newMap) )
    }

    fun changeAnswer( task: Task, newAnswer: SolutionState ) : BlockAnswer {
        val currentAnswers = answerHistory.last().toMutableMap()
        currentAnswers[task] = newAnswer
        val newAnswerHistory = answerHistory.toMutableList()
        newAnswerHistory.removeLast()
        newAnswerHistory.add( currentAnswers )
        return this.copy(answerHistory = newAnswerHistory)
    }
}