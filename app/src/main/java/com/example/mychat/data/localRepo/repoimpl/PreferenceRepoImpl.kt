package com.example.mychat.data.localRepo.repoimpl

import com.example.mychat.data.DB.PreferencesDataStore
import com.example.mychat.data.localRepo.repo.PreferenceRepo
import com.example.mychat.presentation.common.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PreferenceRepoImpl @Inject constructor(
    private  val dataStore: PreferencesDataStore
) : PreferenceRepo {
    override suspend fun getLoginState(): Flow<Boolean> {
        return dataStore.getLoginState() // Collect the first emitted value

        }


    override suspend fun saveLoginState(isLogin: Boolean): Flow<ResultState<String>> =
        flow {
            emit(ResultState.IsLoading)
            try {
               dataStore.saveLoginState(isLogin)
                emit(ResultState.Success(data ="Success"))
            }catch (e:Exception){
                emit(ResultState.Error(error = e.localizedMessage))
            }
        }

}


