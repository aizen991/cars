package ma.jakhlani.carsdemo


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class MainActivity : AppCompatActivity(), View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {



    private var currentPage: Int = 1
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var pageTextView: TextView
    private lateinit var swipeRefreshLayout:SwipeRefreshLayout
    private val adapter = CarInfoAdapter()

    /***
     * the first function called in our Application
     * we initialize  our variable that require context
     * context is ready after super.onCreate function called
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout = findViewById(R.id.refresh)
        swipeRefreshLayout.setOnRefreshListener(this)
        setUpActionBar()
        setUpRecyclerView()
        setUpViewModel()
    }


    /***
     * after view have been created onStart Called
     * send request to server
     */
    override fun onStart() {
        super.onStart()
        mainActivityViewModel.getCars(currentPage)
    }


    /***
     * set our custom action bar view which contain navigation button
     * ,current page number , disable title ,and enable display show Custom
     */
    @SuppressLint("InflateParams")
    private fun setUpActionBar() {
        val actionBarCustomView =
            LayoutInflater.from(this).inflate(R.layout.action_bar_costum_view, null, false)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.customView = actionBarCustomView
        supportActionBar?.setDisplayShowTitleEnabled(false)
        pageTextView = actionBarCustomView.findViewById(R.id.page)
        val previous = actionBarCustomView.findViewById<ImageView>(R.id.previous)
        val next = actionBarCustomView.findViewById<ImageView>(R.id.next)
        next.setOnClickListener(this)
        previous.setOnClickListener(this)

    }

    /***
     * get recyclerView reference,set layoutManager and our adapter
     */

    private fun setUpRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }


    /***
     * initialize MainActivityViewModel and add a MutableLiveData Observer
     */
    private fun setUpViewModel() {
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.carsMutableLiveData.observe(this, {
            swipeRefreshLayout.isRefreshing =false;
            if (it!=null)
                adapter.carList = it
            adapter.notifyDataSetChanged()
        })
    }


    /***
     * MainActivity implements OnClick listener so we override onClick fun
     * we called setOnClickListener(this) for each view
     * @see setUpActionBar
     */
    override fun onClick(v: View?) {
        if (v?.id == R.id.previous && currentPage > 1) {
            currentPage--
        } else if (v?.id == R.id.next && currentPage < 3)
            currentPage++
        pageTextView.text = currentPage.toString()
        swipeRefreshLayout.isRefreshing=true
        mainActivityViewModel.getCars(currentPage)

    }

    /***
     * MainActivity implements OnRefreshListener this function called when user
     * swipe down and when user changes page
     */

    override fun onRefresh() {
        mainActivityViewModel.getCars(currentPage)
    }

}