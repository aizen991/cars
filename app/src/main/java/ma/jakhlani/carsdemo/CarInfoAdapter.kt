package ma.jakhlani.carsdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


/***
 * this class receives List of car from MainactivityViewModel and create a viewHolder for
 * each car.
 * @see onCreateViewHolder
 * when viewHolders are ready its used by
 * @see onCreateViewHolder which fill in each one by correspond data
 *
 */

class CarInfoAdapter : RecyclerView.Adapter<CarInfoAdapter.CarInfoViewHolder>() {

    /***
     * this list of car received from MainActivity View Model by default initialised with
     * new arrayList because the adapter will attached to recyclerView after the response of view model
     * else
     * @see getItemCount will
     * @throws NullPointerException
     */
    var carList: List<Car> = ArrayList()

    /***
     * not thing special here a simple View Holder class
     * @param itemView
     */
    class CarInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carId: TextView = itemView.findViewById(R.id.car_id)
        val carModel: TextView = itemView.findViewById(R.id.car_model)
        val carConstractionYear: TextView = itemView.findViewById(R.id.constraction_year)
        val isUsed: TextView = itemView.findViewById(R.id.is_used)
        val carBrand : TextView= itemView.findViewById(R.id.car_brand)
        val carPhoto:ImageView = itemView.findViewById(R.id.car_photo)

    }


    /***
     * the first fun called after adapter attached to recyclerView for each car
     * in carList create a view Holder object
     * @return CarInfoViewHolder which used by
     * @see onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_view_holder, parent,false)
        return CarInfoViewHolder(view)
    }

    /***
     * fill in viewHolder with correspond data
     * @param holder return of
     * @see onCreateViewHolder
     * @param position current item position.
     */
    override fun onBindViewHolder(holder: CarInfoViewHolder, position: Int) {
        val car = carList[position]
        holder.carId.text = car.id.toString()
        holder.carBrand.text = car.brand
        holder.carModel.text = car.model
        holder.carConstractionYear.text=car.constractionYear
        holder.isUsed.text=car.isUsed.toString()
        Glide.with(holder.itemView).load(car.imageUrl)
            .placeholder(R.drawable.ic_baseline_broken_image_24).into(holder.carPhoto)
    }


    /***
     * @return carlist size
     */
    override fun getItemCount(): Int {
      return carList.size
    }
}