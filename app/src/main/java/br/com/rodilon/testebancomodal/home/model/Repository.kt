package br.com.rodilon.testebancomodal.home.model

import com.squareup.moshi.Json

data class RepositoryItemsList(
    @field:Json(name = "items") val repositoryItemsList: List<Repository>
)

data class Repository(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "owner") val owner: Owner,
    @field:Json(name = "stargazers_count") val starts: Int
)

data class Owner(
    @field:Json(name = "avatar_url") val avatarUrl: String
)