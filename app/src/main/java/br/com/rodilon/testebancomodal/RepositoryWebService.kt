package br.com.rodilon.testebancomodal

class RepositoryWebService {
    val api: Api = WebService().retrofitAuth.create(Api::class.java)

    companion object {
        private var INSTANCE: RepositoryWebService? = null

        fun getInstance(): RepositoryWebService {
            return INSTANCE ?: RepositoryWebService()
        }
    }
}