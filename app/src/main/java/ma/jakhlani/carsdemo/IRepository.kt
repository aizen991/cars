package ma.jakhlani.carsdemo

import androidx.annotation.NonNull


/***
 * this is a contract between Repository and  MainActivityViewModel.
 * MainActivityViewModel contain instance of Repository which implements
 * this Interface encapsulated inside reference of type IRepository
 * so all application's components can use any class
 * implements this Interface as repository without changing application architecture in the future.
 *
 */
interface IRepository {
    fun getCars(@NonNull observer: RepositoryObserver<List<Car>>,page:Int)
}