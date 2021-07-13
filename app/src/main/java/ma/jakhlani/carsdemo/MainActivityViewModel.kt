package ma.jakhlani.carsdemo

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.lang.Exception


/***
 * we use androidViewModel not viewModel because Toast needs context to Handle
 * network error
 */

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    /***
     * carsMutableLiveData contain a special setter which calls an abstract function onDataChanged
     * override in MainActivity to update Ui
     * when activity goes to background observer will removed
     */
    val carsMutableLiveData = MutableLiveData<List<Car>>()


    /***
     * repository calls onSuccess with received data or onFailure with Exception
     * in all cases we set value to carsMutableLive data to update Ui
     * @param page page number
     */

    fun getCars(page:Int){

        RepositoryProvider.repository.getCars(object : RepositoryObserver<List<Car>>() {

            override fun onSuccess(result: List<Car>) {
                carsMutableLiveData.value = result
            }

            override fun onFailure(exception: Exception) {
               Toast.makeText(getApplication(),exception.message,Toast.LENGTH_LONG).show()
                carsMutableLiveData.value=null
            }
        },page )
    }




}
