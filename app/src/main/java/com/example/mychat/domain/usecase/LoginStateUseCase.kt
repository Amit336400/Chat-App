package com.example.mychat.domain.usecase

import com.example.mychat.data.localRepo.repo.PreferenceRepo
import com.example.mychat.presentation.common.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoginStateUseCase @Inject constructor(
    private val preferenceRepo: PreferenceRepo
) {
   suspend fun saveLoginState(isLogin : Boolean) : Flow<ResultState<String>> {
       return preferenceRepo.saveLoginState(isLogin)
    }

    suspend fun getLoginState() : Boolean {
        return false
        //var tg = false;
//      preferenceRepo.getLoginState().collect{
//           when(it){
//               true -> {
//                   tg = it;
//               }
//               false -> {
//                  tg = it
//               }
//           }
//       }
//        return tg;

    }

}