package ma.jakhlani.carsdemo

import java.lang.Exception

/***
 * we need this Observer between MainActivityViewModel and Repository
 * it's like MutableLiveData the difference is :
 * MuatableLiveData requires Context whereas this doesn't
 * if we want to listen form a no Context class in the future.
 */
abstract class RepositoryObserver<T>{


    fun setValue(result:T){
        onSuccess(result)
    }

    fun handException(exception: Exception){
        onFailure(exception)
    }

    abstract fun onSuccess(result:T)
    abstract fun onFailure(exception: Exception)
}