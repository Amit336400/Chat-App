package com.example.mychat.domain.usecase

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
/*
/**
 * Use case to retrieve the login state from the repository.
 * @return Flow<Boolean> emitting the current login state.
 */
class GetLoginStateUseCase1 @Inject constructor(
    private val repo: Repo
) {
    /**
     * Executes the use case by fetching the login state.
     * @return Flow<Boolean> that emits the login state.
     */
    operator fun invoke(): Flow<Boolean> {
        return repo.getLoginState()
    }
}
*/