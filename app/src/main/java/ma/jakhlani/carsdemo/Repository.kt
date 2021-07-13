package ma.jakhlani.carsdemo


import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class Repository : IRepository {

    /***
     * our api link
     */
    private val url: String = "http://demo1585915.mockable.io/api/v1/cars?page="


    /***
     * we establish connection and get json data inside a Thread
     * if we don't use a separate Thread the function will
     * @throws android.os.NetworkOnMainThreadException
     * we use Handler and Looper.getMainLoop() to set data on Main thread
     * because this action update User interface (only the main thread can update user interface)
     * else
     * @throws android.view.ViewRootImpl.CalledFromWrongThreadException
     */
    override fun getCars(observer: RepositoryObserver<List<Car>>, page: Int) {
        Thread {
            try {

                val connection = URL(url + page).openConnection() as HttpURLConnection
                val data = connection.inputStream.bufferedReader().readText()
                val carList = jsonStringToArrayOfObject(data)

                Handler(Looper.getMainLooper()).post {
                    observer.setValue(carList)
                }

            } catch (e: Exception) {
                Handler(Looper.getMainLooper()).post {
                    observer.handException(e)
                }

            }

        }.start()
    }


    /***
     * deserialize json to List<Car>
     */
    private fun jsonStringToArrayOfObject(jsonString: String): List<Car> {

        val carList = ArrayList<Car>()
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("data")

        for (jsonIndex in 0 until jsonArray.length()) {
            val stringCar: String = jsonArray.getJSONObject(jsonIndex).toString()
            val car = Gson().fromJson(stringCar, Car::class.java)
            carList.add(car)
        }

        return carList
    }
}
