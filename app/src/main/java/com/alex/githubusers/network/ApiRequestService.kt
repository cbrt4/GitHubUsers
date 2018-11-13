package com.alex.githubusers.network

import com.alex.githubusers.model.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequestService {

    @GET(USERS)
    fun loadUsers(
        @Query(SINCE) since: Int,
        @Query(PER_PAGE) perPage: Int
    ): Observable<List<UserEntity>>
}