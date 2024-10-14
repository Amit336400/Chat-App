package com.example.mychat.domain.usecase

import javax.inject.Inject
/*
/**
 * Use case to save the login state.
 * It abstracts the repository layer and serves as the interaction point for the ViewModel.
 */
class SaveLoginStateUseCase1 @Inject constructor(
    private val repo: Repo
) {
    /**
     * Executes the use case by saving the login state.
     * @param isLoggedIn Boolean to determine if the user is logged in.
     */
    suspend operator fun invoke(isLoggedIn: Boolean) {
        repo.saveLoginState(isLoggedIn)
    }
}


 */
