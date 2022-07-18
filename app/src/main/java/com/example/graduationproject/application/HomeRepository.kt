package com.example.graduationproject.application

import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.home.model.LikePojo
import com.example.graduationproject.presentation.home.model.PostsPojo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response

class HomeRepository(private val api: ServiceAPI) {

    suspend fun getAllPosts():Flow<Response<List<PostsPojo>> > = flow {
        var responseOfAllPosta:Response<List<PostsPojo>> = api.getAllPosts()
        emit(responseOfAllPosta)
    }.flowOn(Dispatchers.IO)
        /*withContext(Dispatchers.IO) {
            var responseOfAllPosta:Response<List<PostsPojo>> = api.getAllPosts()
            return@withContext responseOfAllPosta

        }*/

    suspend fun addLike(id:String) :Response<LikePojo> =
        withContext(Dispatchers.IO) {
           var response =   api.addLike(id)
            return@withContext response
        }

    suspend fun deletePost(id:String): Response<String> =
        withContext(Dispatchers.IO) {
            var responseOfDelete:Response<String> = api.deletePost(id)
            return@withContext responseOfDelete
        }

    suspend fun getProfileInfo() =
        withContext(Dispatchers.IO) {
            api.getProfileInfo()
        }



}