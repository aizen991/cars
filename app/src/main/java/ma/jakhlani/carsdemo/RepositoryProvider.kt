package ma.jakhlani.carsdemo


/***
 * singleton object for performance and dependency injection and encapsulation
 */
object RepositoryProvider {

    val repository: IRepository = Repository()
}